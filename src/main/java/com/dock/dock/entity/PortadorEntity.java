package com.dock.dock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
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