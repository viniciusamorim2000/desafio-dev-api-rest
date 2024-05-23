package com.dock.dock.service;

import com.dock.dock.domain.entity.PortadorEntity;

public interface PortadorService {

    PortadorEntity criarPortador(PortadorEntity portadorEntity);

    void removerPortador(String cpf);
}
