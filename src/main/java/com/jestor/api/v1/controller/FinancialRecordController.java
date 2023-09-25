package com.jestor.api.v1.controller;

import com.jestor.domain.model.dto.*;
import com.jestor.domain.model.financialrecord.FinancialRecord;
import com.jestor.infrastructure.service.FinancialRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/financial-record")
public class FinancialRecordController {

    private final FinancialRecordService service;

    @GetMapping
    public ResponseEntity<ResponseGetFinancialRecords> getFinancialRecords(@RequestBody RequestGetFinancialRecords request) {
        ResponseGetFinancialRecords response = service.getFinancialRecords(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseCreateFinancialRecords> createFinancialRecord(@RequestBody RequestCreateFinancialRecord request) {
        return new ResponseEntity<>(service.createFinancialRecord(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCreateFinancialRecords> updateFinancialRecord(@PathVariable Long id, @RequestBody RequestEditFinancialRecord request) {
        ResponseCreateFinancialRecords response = service.updateFinancialRecord(id, request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RequestGetFinancialRecords> deleteFinancialRecord(@PathVariable Long id, @RequestBody RequestGetFinancialRecords request) {
        FinancialRecord financialRecord = service.getEntityById(id);
        request.setType(String.valueOf(financialRecord.getCategory().getType().getCode()));
        request.setMonth(financialRecord.getDate().getMonthValue());

        service.deleteFinancialRecord(id, request);
        return ResponseEntity.noContent().build();
    }
}