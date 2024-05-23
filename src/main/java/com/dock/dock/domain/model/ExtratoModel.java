package com.dock.dock.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExtratoModel {

    private Integer numeroConta;
    private BigDecimal saldoConta;
    private Integer idTransacao;
    private LocalDateTime dataHoraTransacao;
    private String tipo;
    private BigDecimal valorTransacao;

}
