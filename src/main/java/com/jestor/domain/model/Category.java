package com.jestor.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "tb_category")
public class Category {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private Long id;

	@Column(nullable = false, length = 50)
	private String description;

	@Column(nullable = false, length = 50)
	private String icon;

	@Column(nullable = false, length = 10)
	private String color;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 1)
	private Type type;
}
