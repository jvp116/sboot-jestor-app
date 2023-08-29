package com.jestor.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateFinancialRecords {
    private double value;
    private String description;
    private String date;
    private Integer categoryId;
    private String email;
}
