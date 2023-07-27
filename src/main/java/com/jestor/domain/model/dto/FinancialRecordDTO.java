package com.jestor.domain.model.dto;

import com.jestor.domain.model.Category;
import com.jestor.domain.model.FinancialRecord;
import com.jestor.domain.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialRecordDTO {

    private Long id;

    private BigDecimal value;

    private LocalDate date;

    private String description;

    private User user;

    private Category category;

    public FinancialRecordDTO(FinancialRecord entity) {
        this.id = entity.getId();
        this.value = entity.getValue();
        this.date = entity.getDate();
        this.description = entity.getDescription();
        this.user = entity.getUser();
        this.category = entity.getCategory();
    }
}