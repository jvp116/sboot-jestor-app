package com.jestor.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestEditFinancialRecord {
    private BigDecimal value;
    private String description;
    private String date;
    private Integer month;
    private String type;
    private String email;
}
