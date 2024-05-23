package com.dock.dock.controller.dto.extrato;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExtratoResponseDTO {

    private Integer numeroConta;
    private BigDecimal saldoConta;
    private Integer idTransacao;
    private LocalDateTime dataHoraTransacao;
    private String tipo;
    private BigDecimal valorTransacao;
}
