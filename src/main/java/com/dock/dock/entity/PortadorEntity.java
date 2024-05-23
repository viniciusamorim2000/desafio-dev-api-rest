package com.dock.dock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PORTADOR")
public class PortadorEntity {

	@Id
	@Column(name = "CPF", length = 11)
	private String cpf;

	@Column(name = "NOME", length = 100, nullable = false)
	private String nomeCompleto;
}