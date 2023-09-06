package com.jestor.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetFinancialRecords implements Serializable {

    private BigDecimal totalMes;
    private List<FinancialRecordDTO> financialRecords;
}