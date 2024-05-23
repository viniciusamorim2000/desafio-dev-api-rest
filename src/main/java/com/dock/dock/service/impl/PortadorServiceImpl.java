package com.dock.dock.service.impl;

import com.dock.dock.domain.entity.PortadorEntity;
import com.dock.dock.repository.PortadorRepository;
import com.dock.dock.service.PortadorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PortadorServiceImpl implements PortadorService {

    @Autowired
    private PortadorRepository portadorRepository;

    @Override
    public PortadorEntity criarPortador(PortadorEntity portadorEntity) {
        validarCpf(portadorEntity.getCpf());
        if (verificaPortador(portadorEntity.getCpf()).isPresent()){
            throw new IllegalArgumentException("Portador com este CPF já existe");
        }
        return portadorRepository.save(portadorEntity);
    }

    private void validarCpf(String cpf) {
        if (!validaCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    private Optional<PortadorEntity> verificaPortador(String cpf){
        return portadorRepository.findByCpf(cpf);
    }

    @Override
    public void removerPortador(String cpf) {
        validarCpf(cpf);
        if (verificaPortador(cpf).isEmpty()){
            throw new IllegalArgumentException("Portador de CPF inexistente.");
        }
        Optional<PortadorEntity> portador = portadorRepository.findByCpf(cpf);
        portadorRepository.delete(portador.get());
    }

    public boolean validaCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || !cpf.chars().allMatch(Character::isDigit)) {
            return false;
        }

        int[] numeros = new int[11];
        for (int i = 0; i < 11; i++) {
            numeros[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += numeros[i] * (10 - i);
        }

        int primeiroDigitoVerificador = 11 - (soma % 11);
        if (primeiroDigitoVerificador > 9) primeiroDigitoVerificador = 0;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += numeros[i] * (11 - i);
        }

        int segundoDigitoVerificador = 11 - (soma % 11);
        if (segundoDigitoVerificador > 9) segundoDigitoVerificador = 0;

        return primeiroDigitoVerificador == numeros[9] && segundoDigitoVerificador == numeros[10];
    }

    public Optional<PortadorEntity> getPortadorByCpf(String cpf) {
        return portadorRepository.findByCpf(cpf);
    }
}
