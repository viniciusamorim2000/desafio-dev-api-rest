package com.dock.dock.service.impl;

import com.dock.dock.domain.entity.ContaEntity;
import com.dock.dock.domain.entity.PortadorEntity;
import com.dock.dock.repository.ContaRepository;
import com.dock.dock.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    private PortadorServiceImpl portadorService;

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public ContaEntity criarConta(String cpf) {
        validarCPF(cpf);
        PortadorEntity portador = obterPortador(cpf);
        Integer numeroConta = criarNumeroContaUnico();
        return criarNovaConta(portador, numeroConta);
    }

    @Override
    public ContaEntity consultaContaPorCpf(String cpf) {
        validarCPF(cpf);
        PortadorEntity portador = obterPortador(cpf);
        return contaRepository.findByPortadorEntity(portador)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Conta inexistente para o CPF: [%s].", cpf)));
    }

    @Override
    public ContaEntity alterarStatusDaConta(String cpf, Boolean statusConta) {
        ContaEntity contaEntity = consultaContaPorCpf(cpf);
        contaEntity.setAtiva(statusConta);
        return contaEntity;
    }


    public ContaEntity consultaContaPorNumeroConta(Integer numeroConta) {
        return contaRepository.findByNumero(numeroConta)
                .orElseThrow(() -> new IllegalArgumentException("Conta inexistente."));
    }

    private void validarCPF(String cpf) {
        if (!portadorService.validaCPF(cpf)) {
            throw new IllegalArgumentException("Operação mal sucedida.");
        }
    }

    private PortadorEntity obterPortador(String cpf) {
        Optional<PortadorEntity> portador = portadorService.getPortadorByCpf(cpf);
        if (portador.isEmpty()) {
            throw new IllegalArgumentException("Portador de CPF inexistente.");
        }
        return portador.get();
    }

    private Integer criarNumeroContaUnico() {
        Integer numeroConta = criarNumeroConta();
        if (contaRepository.findByNumero(numeroConta).isPresent()) {
            throw new IllegalArgumentException("Conta com este número já existe.");
        }
        return numeroConta;
    }

    private ContaEntity criarNovaConta(PortadorEntity portador, Integer numeroConta) {
        ContaEntity contaEntity = new ContaEntity();
        contaEntity.setPortadorEntity(portador);
        contaEntity.setNumero(numeroConta);
        contaEntity.setAtiva(true);
        contaEntity.setSaldo(new BigDecimal(0));
        contaEntity.setAgencia(0001);
        return contaRepository.save(contaEntity);
    }

    public ContaEntity salvarConta(ContaEntity contaEntity) {
        return contaRepository.save(contaEntity);
    }


    public static Integer criarNumeroConta() {
        Random rand = new Random();
        return rand.nextInt(900000) + 100000;
    }


}
