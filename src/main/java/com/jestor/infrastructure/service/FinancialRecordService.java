package com.jestor.infrastructure.service;

import com.jestor.domain.model.dto.*;
import com.jestor.domain.model.financialrecord.FinancialRecord;
import com.jestor.infrastructure.repository.FinancialRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialRecordService {

    @Autowired
    private FinancialRecordRepository repository;

    @Cacheable(value = "financial_records",key = "{#request.email,#request.type,#request.month}", condition = "#request.type=='E' or #request.type=='S'")
    public ResponseGetFinancialRecords getFinancialRecords(RequestGetFinancialRecords request) {
        List<FinancialRecord> financialRecords = repository.getFinancialRecords(request.getEmail(), request.getType(), request.getMonth());

        BigDecimal totalMes = financialRecords.stream().map(x -> x.getValue()).reduce(BigDecimal.ZERO, BigDecimal::add);

        return ResponseGetFinancialRecords.builder()
                .totalMes(totalMes == BigDecimal.ZERO ? BigDecimal.valueOf(0.00).setScale(2) : totalMes)
                .financialRecords(financialRecords.stream().map(FinancialRecordDTO::new).toList())
                .build();
    }

    @CacheEvict(value = "financial_records", key = "{#request.email,#request.type,#request.month}", condition = "#request.type=='E' or #request.type=='S'")
    public ResponseCreateFinancialRecords createFinancialRecord(RequestCreateFinancialRecords request) {
        repository.createFinancialRecord(request.getValue(), request.getDescription(), request.getDate(), request.getCategoryId(), request.getEmail());
        return new ResponseCreateFinancialRecords(request.getValue(), request.getDescription(), request.getDate(), request.getCategoryId(), request.getType());
    }
}