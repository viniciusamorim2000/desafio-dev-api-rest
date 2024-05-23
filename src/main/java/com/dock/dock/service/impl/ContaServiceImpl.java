package com.dock.dock.service.impl;

import com.dock.dock.entity.ContaEntity;
import com.dock.dock.entity.PortadorEntity;
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
        if (portadorService.validaCPF(cpf)){
            if (portadorService.getPortadorByCpf(cpf).isEmpty()){
                throw new IllegalArgumentException("Portador de CPF inexistente.");
            }
            Integer numeroConta = criarNumeroConta();
            if (contaRepository.findByNumero(numeroConta).isPresent()){
                throw new IllegalArgumentException("Conta com este número já existe.");
            }
            Optional<PortadorEntity> portador = portadorService.getPortadorByCpf(cpf);
            ContaEntity contaEntity = new ContaEntity();
            contaEntity.setPortadorEntity(portador.get());
            contaEntity.setNumero(numeroConta);
            contaEntity.setAtiva(true);
            contaEntity.setSaldo(new BigDecimal(0));
            return contaRepository.save(contaEntity);
        } else {
            throw new IllegalArgumentException("Operação mal sucedida.");
        }
    }

    public static Integer criarNumeroConta() {
        Random rand = new Random();
        return rand.nextInt(900000) + 100000;
    }
}
