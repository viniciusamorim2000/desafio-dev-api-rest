package com.dock.dock.service;

import com.dock.dock.domain.entity.ContaEntity;

public interface ContaService {

    ContaEntity criarConta(String cpf);

    ContaEntity consultaContaPorCpf(String cpf);

    ContaEntity alterarStatusDaConta(String cpf, Boolean statusConta);
}
