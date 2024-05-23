package com.dock.dock.service.impl;

import com.dock.dock.domain.entity.ContaEntity;
import com.dock.dock.domain.entity.TransacaoEntity;
import com.dock.dock.domain.entity.enums.TipoTransacao;
import com.dock.dock.domain.model.ExtratoModel;
import com.dock.dock.repository.TransacaoRepository;
import com.dock.dock.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaServiceImpl contaService;

    private static final BigDecimal LIMITE_DIARIO_SAQUE = new BigDecimal("2000");

    @Override
    public ExtratoModel consultarExtrato(String cpf, LocalDateTime dataIncio, LocalDateTime dataFim) {
        return null;
    }

    @Override
    public TransacaoEntity efetuarTransacao(TransacaoEntity transacaoEntity) {
        ContaEntity contaEntity = buscarConta(transacaoEntity.getNumeroConta().getNumero());
        if (transacaoEntity.getTipoTransacao().equals("DEPOSITO")){
            return depositar(contaEntity, transacaoEntity.getValorTransacao());
        } else {
            return sacar();
        }

    }

    private TransacaoEntity sacar() {
        return null;
    }


    public TransacaoEntity depositar(ContaEntity conta, BigDecimal valor) {
            conta.setSaldo(conta.getSaldo().add(valor));
            contaService.salvarConta(conta);
            TransacaoEntity transacaoEntity = new TransacaoEntity();
            transacaoEntity.setTipoTransacao(TipoTransacao.DEPOSITO);
            transacaoEntity.setValorTransacao(valor);
            transacaoEntity.setNumeroConta(conta);
            transacaoEntity.setDataHoraTransacao(LocalDateTime.now());
            return salvarTransacao(transacaoEntity);

    }

    public TransacaoEntity salvarTransacao(TransacaoEntity transacaoEntity){
        return transacaoRepository.save(transacaoEntity);
    }

    private ContaEntity buscarConta(Integer numeroConta) {
        return contaService.consultaContaPorNumeroConta(numeroConta);
    }


}
