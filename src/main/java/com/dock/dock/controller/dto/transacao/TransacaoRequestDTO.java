package com.dock.dock.controller.dto.transacao;

import com.dock.dock.domain.entity.enums.TipoTransacao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransacaoRequestDTO {

    private TipoTransacao tipoTransacao;
    private BigDecimal valorTransacao;
    private Integer numeroConta;

}
