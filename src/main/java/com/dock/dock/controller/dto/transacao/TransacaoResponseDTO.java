package com.dock.dock.controller.dto.transacao;

import com.dock.dock.domain.entity.enums.TipoTransacao;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransacaoResponseDTO {

    private Integer idTransacao;
    private Integer numeroConta;
    private BigDecimal valorTransacao;
    private LocalDateTime dataHoraTransacao;
    private TipoTransacao tipoTransacao;
}
