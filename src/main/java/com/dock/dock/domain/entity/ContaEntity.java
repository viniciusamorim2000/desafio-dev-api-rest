package com.dock.dock.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

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

	@ManyToOne
	@JoinColumn(name = "CPF_PORTADOR", referencedColumnName = "CPF")
	private PortadorEntity portadorEntity;
}