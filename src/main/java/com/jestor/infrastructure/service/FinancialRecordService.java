package com.jestor.infrastructure.service;

import com.jestor.domain.model.dto.RequestCreateFinancialRecords;
import com.jestor.domain.model.dto.RequestGetFinancialRecords;
import com.jestor.domain.model.dto.ResponseGetFinancialRecords;
import com.jestor.domain.model.financialrecord.FinancialRecord;
import com.jestor.infrastructure.repository.FinancialRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialRecordService {

    @Autowired
    private FinancialRecordRepository repository;

    @Cacheable(value = "financial_records", key="{#request.email,#request.type}", condition="#request.type=='E' or #request.type=='S'")
    public List<ResponseGetFinancialRecords> getFinancialRecords(RequestGetFinancialRecords request) {
        List<FinancialRecord> financialRecords = repository.getFinancialRecords(request.getEmail(), request.getType());

        List<FinancialRecord> financialRecordList = new ArrayList<>();
        financialRecordList.addAll(financialRecords);


        financialRecordList.forEach(element -> {
            String month = String.valueOf(element.getDate().getMonth().getValue());

            if (!request.getMonth().equals(month)) {
                financialRecords.remove(element);
            }
        });

        return financialRecords.stream().map(ResponseGetFinancialRecords::new).toList();
    }

    public FinancialRecord createFinancialRecord(RequestCreateFinancialRecords request) {
        repository.createFinancialRecord(request.getValue(), request.getDescription(), request.getDate(), request.getCategoryId(), request.getEmail());
        return null;
    }
}