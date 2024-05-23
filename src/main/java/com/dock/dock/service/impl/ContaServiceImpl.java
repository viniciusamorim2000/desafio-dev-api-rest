package com.dock.dock.service.impl;

import com.dock.dock.entity.ContaEntity;
import com.dock.dock.entity.PortadorEntity;
import com.dock.dock.repository.ContaRepository;
import com.dock.dock.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        contaEntity.setDataInicio(LocalDate.now());
        contaEntity.setDataFim(LocalDate.now());
        return contaRepository.save(contaEntity);
    }


    public static Integer criarNumeroConta() {
        Random rand = new Random();
        return rand.nextInt(900000) + 100000;
    }


}
