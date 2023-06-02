package com.jestor.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Categoria {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private Long id;

	@Column(nullable = false, length = 50)
	private String descricao;

	@Column(nullable = false, length = 50)
	private String icone;

	@Column(nullable = false, length = 10)
	private String cor;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 1)
	private Tipo tipo;
}
