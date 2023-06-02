package com.jestor.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Lancamento {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valor;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @Column(nullable = false, length = 50)
    private String descricao;

    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne(targetEntity = Categoria.class)
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private Categoria categoria;
}
