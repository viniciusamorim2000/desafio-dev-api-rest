package com.dock.dock.service;

import com.dock.dock.controller.dto.PortadorRequestDTO;
import com.dock.dock.controller.dto.PortadorResponseDTO;
import com.dock.dock.entity.PortadorEntity;

public interface PortadorService {

    PortadorEntity criarPortador(PortadorEntity portadorEntity);

    void removerPortador(String cpf);
}
