package com.jestor.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "tb_financial_record")
public class FinancialRecord {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private Long id;

	@Column(nullable = false)
	private BigDecimal value;

	@Column(nullable = false, length = 8)
	private String date;

	@Column(nullable = false, length = 50)
	private String description;

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(nullable = false, referencedColumnName = "id")
	private User user;

	@ManyToOne(targetEntity = Category.class)
	@JoinColumn(nullable = false, referencedColumnName = "id")
	private Category category;
}
