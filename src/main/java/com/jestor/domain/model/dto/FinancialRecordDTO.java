package com.jestor.domain.model.dto;

import com.jestor.domain.model.financialrecord.Category;
import com.jestor.domain.model.financialrecord.FinancialRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialRecordDTO implements Serializable {

    private Long id;

    private BigDecimal value;

    private LocalDate date;

    private String description;

    private Category category;

    public FinancialRecordDTO(FinancialRecord entity) {
        this.id = entity.getId();
        this.value = entity.getValue();
        this.date = entity.getDate();
        this.description = entity.getDescription();
        this.category = entity.getCategory();
    }
}