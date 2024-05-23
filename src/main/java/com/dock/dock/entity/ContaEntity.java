package com.dock.dock.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "CONTA")
public class ContaEntity {

	@Id
	@Column(name = "NUMERO")
	private Integer numero;

	@Column(name = "AGENCIA", nullable = false)
	private Integer agencia;

	@Column(name = "SALDO", nullable = false)
	private BigDecimal saldo;

	@Column(name = "CONTA_ATIVA")
	private Boolean ativa;

	@Column(name = "DATA_INICIO")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInicio;

	@Column(name = "DATA_FIM")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFim;

	@ManyToOne
	@JoinColumn(name = "CPF_PORTADOR", referencedColumnName = "CPF")
	private PortadorEntity portadorEntity;
}