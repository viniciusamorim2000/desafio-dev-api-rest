package com.dock.dock.service;

import com.dock.dock.entity.ContaEntity;

public interface ContaService {

    ContaEntity criarConta(String cpf);

    ContaEntity consultaConta(String cpf);

}
