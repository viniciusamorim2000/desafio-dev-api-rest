package com.dock.dock.service.impl;

import com.dock.dock.domain.entity.ContaEntity;
import com.dock.dock.domain.entity.TransacaoEntity;
import com.dock.dock.domain.entity.enums.TipoTransacao;
import com.dock.dock.repository.TransacaoRepository;
import com.dock.dock.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaServiceImpl contaService;

    private static final BigDecimal LIMITE_DIARIO_SAQUE = new BigDecimal("2000");


    @Override
    public List<TransacaoEntity> consultarExtrato(Integer numeroConta, LocalDate dataInicio, LocalDate dataFim) {
        LocalDateTime inicioDoDia = dataInicio.atStartOfDay();
        LocalDateTime fimDoDia = dataFim.atTime(23, 59, 59);
        return transacaoRepository.findByDataHoraTransacaoBetweenAndNumeroConta(inicioDoDia, fimDoDia,
                buscarConta(numeroConta));
    }

    @Override
    public TransacaoEntity efetuarTransacao(TransacaoEntity transacaoEntity) {
        ContaEntity contaEntity = buscarConta(transacaoEntity.getNumeroConta().getNumero());
        switch (transacaoEntity.getTipoTransacao()) {
            case DEPOSITO:
                return depositar(contaEntity, transacaoEntity.getValorTransacao());
            case SAQUE:
                return sacar(contaEntity, transacaoEntity.getValorTransacao());
            default:
                throw new IllegalArgumentException("Tipo transação inválido.");
        }
    }


    private TransacaoEntity sacar(ContaEntity conta, BigDecimal valor) {
        verificarSaldo(conta, valor);
        verificarLimiteDiario(conta, valor);
        atualizarSaldoSaque(conta, valor);
        return criarTransacaoSaque(conta, valor);
    }

    private void verificarSaldo(ContaEntity conta, BigDecimal valor) {
        if (conta.getSaldo().compareTo(valor) <= 0) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
    }

    private void verificarLimiteDiario(ContaEntity conta, BigDecimal valor) {
        BigDecimal valorSacadoDia = verificaTransacoes(todasTransacoesPorContaDataTransacao(conta));
        if (LIMITE_DIARIO_SAQUE.compareTo(valorSacadoDia.add(valor)) <= 0) {
            throw new IllegalArgumentException("Limite diário excedido.");
        }
    }

    private void atualizarSaldoSaque(ContaEntity conta, BigDecimal valor) {
        conta.setSaldo(conta.getSaldo().subtract(valor));
        contaService.salvarConta(conta);
    }

    private TransacaoEntity criarTransacaoSaque(ContaEntity conta, BigDecimal valor) {
        TransacaoEntity transacaoEntity = new TransacaoEntity();
        transacaoEntity.setTipoTransacao(TipoTransacao.SAQUE);
        transacaoEntity.setValorTransacao(valor);
        transacaoEntity.setNumeroConta(conta);
        transacaoEntity.setDataHoraTransacao(LocalDateTime.now());
        return salvarTransacao(transacaoEntity);
    }

    private BigDecimal verificaTransacoes(List<TransacaoEntity> transacoes) {
        return transacoes.stream()
                .filter(tipoTransacao -> tipoTransacao.getTipoTransacao().equals(TipoTransacao.SAQUE))
                .filter(dataTransacao -> dataTransacao.getDataHoraTransacao().toLocalDate().equals(LocalDate.now()))
                .map(TransacaoEntity::getValorTransacao)
                .collect(Collectors.reducing(BigDecimal.ZERO, BigDecimal::add));

    }

    public List<TransacaoEntity> todasTransacoesPorContaDataTransacao(ContaEntity contaEntity) {
        return transacaoRepository.findByNumeroConta(contaEntity);
    }


    public TransacaoEntity depositar(ContaEntity conta, BigDecimal valor) {
        atualizarSaldoDeposito(conta, valor);
        return criarTransacaoDeposito(conta, valor);
    }

    private void atualizarSaldoDeposito(ContaEntity conta, BigDecimal valor) {
        conta.setSaldo(conta.getSaldo().add(valor));
        contaService.salvarConta(conta);
    }

    private TransacaoEntity criarTransacaoDeposito(ContaEntity conta, BigDecimal valor) {
        TransacaoEntity transacaoEntity = new TransacaoEntity();
        transacaoEntity.setTipoTransacao(TipoTransacao.DEPOSITO);
        transacaoEntity.setValorTransacao(valor);
        transacaoEntity.setNumeroConta(conta);
        transacaoEntity.setDataHoraTransacao(LocalDateTime.now());
        return salvarTransacao(transacaoEntity);
    }


    public TransacaoEntity salvarTransacao(TransacaoEntity transacaoEntity) {
        return transacaoRepository.save(transacaoEntity);
    }

    private ContaEntity buscarConta(Integer numeroConta) {
        return contaService.consultaContaPorNumeroConta(numeroConta);
    }


}
