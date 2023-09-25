package com.jestor.infrastructure.service;

import com.jestor.domain.model.dto.*;
import com.jestor.domain.model.financialrecord.FinancialRecord;
import com.jestor.infrastructure.repository.FinancialRecordRepository;
import com.jestor.infrastructure.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinancialRecordService {

    @Autowired
    private FinancialRecordRepository repository;

    @Cacheable(value = "financial_records", key = "{#request.email,#request.type,#request.month}", condition = "#request.type=='E' or #request.type=='S'")
    public ResponseGetFinancialRecords getFinancialRecords(RequestGetFinancialRecords request) {
        List<FinancialRecord> financialRecords = repository.getFinancialRecords(request.getEmail(), request.getType(), request.getMonth());

        BigDecimal totalMes = financialRecords.stream().map(x -> x.getValue()).reduce(BigDecimal.ZERO, BigDecimal::add);

        return ResponseGetFinancialRecords.builder()
                .totalMes(totalMes == BigDecimal.ZERO ? BigDecimal.valueOf(0.00).setScale(2) : totalMes)
                .financialRecords(financialRecords.stream().map(FinancialRecordDTO::new).toList())
                .build();
    }

    @CacheEvict(value = "financial_records", key = "{#request.email,#request.type,#request.month}")
    public ResponseCreateFinancialRecords createFinancialRecord(RequestCreateFinancialRecord request) {
        repository.createFinancialRecord(request.getValue(), request.getDescription(), request.getDate(), request.getCategoryId(), request.getEmail());
        return new ResponseCreateFinancialRecords(request.getValue(), request.getDescription(), request.getDate(), request.getCategoryId(), request.getType());
    }

    @Caching(evict = {
            @CacheEvict(value = "financial_records", key = "{#request.email,#request.type,1}"),
            @CacheEvict(value = "financial_records", key = "{#request.email,#request.type,2}"),
            @CacheEvict(value = "financial_records", key = "{#request.email,#request.type,3}"),
            @CacheEvict(value = "financial_records", key = "{#request.email,#request.type,4}"),
            @CacheEvict(value = "financial_records", key = "{#request.email,#request.type,5}"),
            @CacheEvict(value = "financial_records", key = "{#request.email,#request.type,6}"),
            @CacheEvict(value = "financial_records", key = "{#request.email,#request.type,7}"),
            @CacheEvict(value = "financial_records", key = "{#request.email,#request.type,8}"),
            @CacheEvict(value = "financial_records", key = "{#request.email,#request.type,9}"),
            @CacheEvict(value = "financial_records", key = "{#request.email,#request.type,10}"),
            @CacheEvict(value = "financial_records", key = "{#request.email,#request.type,11}"),
            @CacheEvict(value = "financial_records", key = "{#request.email,#request.type,12}"),
    })
    public ResponseCreateFinancialRecords updateFinancialRecord(Long id, RequestEditFinancialRecord request) {
        FinancialRecord entity = getEntityById(id);
        copyDtoToEntityForUpdate(request, entity);
        repository.save(entity);
        return new ResponseCreateFinancialRecords(request.getValue(), request.getDescription(), request.getDate(), null, request.getType());
    }

    @CacheEvict(value = "financial_records", key = "{#request.email,#request.type,#request.month}")
    public void deleteFinancialRecord(Long id, RequestGetFinancialRecords request) {
        repository.deleteById(id);
    }

    public FinancialRecord getEntityById(Long id) {
        Optional<FinancialRecord> result = repository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("FinancialRecord não encontrado."));
    }

    private void copyDtoToEntityForUpdate(RequestEditFinancialRecord dto, FinancialRecord entity) {
        entity.setValue(dto.getValue());
        entity.setDescription(dto.getDescription());

        List<String> date = List.of(dto.getDate().split("/"));
        String dateFormatted = date.get(2) + "-" + date.get(1) + "-" + date.get(0);
        entity.setDate(LocalDate.parse(dateFormatted));
    }
}