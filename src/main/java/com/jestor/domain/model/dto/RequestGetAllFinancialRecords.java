package com.jestor.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestGetAllFinancialRecords {
    private String email;
    private Integer month;
    private Integer year;
}