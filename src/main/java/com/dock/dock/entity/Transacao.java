package com.dock.dock.entity;

import com.dock.dock.entity.enums.TipoTransacao;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TRANSACAO")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRANSACAO")
    private Integer id;

    @Column(name = "DATA_HORA", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO", nullable = false)
    private TipoTransacao tipo;

    @Column(name = "VALOR_TRANSACAO", nullable = false)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "NUMERO_CONTA", referencedColumnName = "NUMERO")
    private ContaEntity contaEntity;
}
