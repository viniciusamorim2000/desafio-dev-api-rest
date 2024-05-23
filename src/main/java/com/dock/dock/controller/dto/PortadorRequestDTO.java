package com.dock.dock.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortadorRequestDTO {

    private String cpf;
    private String nomeCompleto;
}
