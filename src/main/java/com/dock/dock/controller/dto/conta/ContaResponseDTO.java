package com.dock.dock.controller.dto.conta;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContaResponseDTO {

    private Integer numero;
    private Integer agencia;
    private BigDecimal saldo;

}
