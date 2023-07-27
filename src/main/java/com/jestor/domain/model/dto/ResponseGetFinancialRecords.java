package com.jestor.domain.model.dto;

import com.jestor.domain.model.Category;
import com.jestor.domain.model.FinancialRecord;
import com.jestor.domain.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetFinancialRecords {

    private Long id;

    private BigDecimal value;

    private Date date;

    private String description;

    private Category category;

    public ResponseGetFinancialRecords(FinancialRecord entity) {
        this.id = entity.getId();
        this.value = entity.getValue();
        this.date = entity.getDate();
        this.description = entity.getDescription();
        this.category = entity.getCategory();
    }
}