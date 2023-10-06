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

    @Cacheable(value = "financial_records", key = "{#request.email,#request.type,#request.month,#request.year}", condition = "#request.type=='E' or #request.type=='S'")
    public List<FinancialRecordDTO> getFinancialRecords(RequestGetFinancialRecords request) {
        List<FinancialRecord> financialRecords = repository.getFinancialRecords(request.getEmail(), request.getType(), request.getMonth(), request.getYear());

        return financialRecords.stream().map(FinancialRecordDTO::new).toList();
    }

    public List<FinancialRecordDTO> getAllFinancialRecords(RequestGetAllFinancialRecords request) {
        List<FinancialRecord> financialRecords = repository.getAllFinancialRecords(request.getEmail(), request.getMonth(), request.getYear());

        return financialRecords.stream().map(FinancialRecordDTO::new).toList();
    }

    @CacheEvict(value = "financial_records", key = "{#request.email,#request.type,#request.month,#request.year}")
    public ResponseCreateFinancialRecords createFinancialRecord(RequestCreateFinancialRecord request) {
        repository.createFinancialRecord(request.getValue(), request.getDescription(), request.getDate(), request.getCategoryId(), request.getEmail());
        return new ResponseCreateFinancialRecords(request.getValue(), request.getDescription(), request.getDate(), request.getCategoryId(), request.getType());
    }

    public ResponseCreateFinancialRecords updateFinancialRecord(Long id, RequestEditFinancialRecord request) {
        FinancialRecord entity = getEntityById(id);
        copyDtoToEntityForUpdate(request, entity);
        repository.save(entity);
        return new ResponseCreateFinancialRecords(request.getValue(), request.getDescription(), request.getDate(), null, request.getType());
    }

    @CacheEvict(value = "financial_records", key = "{#request.email,#request.type,#request.month,#request.year}")
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