package com.jestor.domain.model;

import com.jestor.domain.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class FinancialRecord {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false, columnDefinition = "date")
    private Date date;

    @Column(nullable = false, length = 50)
    private String description;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private User user;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private Category category;
}