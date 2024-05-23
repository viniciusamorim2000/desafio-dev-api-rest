package com.dock.dock.domain.entity;

import com.dock.dock.domain.entity.enums.TipoTransacao;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TRANSACAO")
public class TransacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRANSACAO")
    private Integer idTransacao;

    @Column(name = "DATA_HORA", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHoraTransacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO", nullable = false)
    private TipoTransacao tipoTransacao;

    @Column(name = "VALOR_TRANSACAO", nullable = false)
    private BigDecimal valorTransacao;

    @ManyToOne
    @JoinColumn(name = "NUMERO_CONTA", referencedColumnName = "NUMERO")
    private ContaEntity numeroConta;
}
