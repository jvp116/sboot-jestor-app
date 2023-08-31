package com.jestor.api.v1.controller;

import com.jestor.domain.model.dto.RequestCreateFinancialRecords;
import com.jestor.domain.model.dto.RequestGetFinancialRecords;
import com.jestor.domain.model.dto.ResponseGetFinancialRecords;
import com.jestor.infrastructure.service.FinancialRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/financial-record")
public class FinancialRecordController {

    private final FinancialRecordService service;

    @GetMapping
    public ResponseEntity<List<ResponseGetFinancialRecords>> getFinancialRecords(@RequestBody RequestGetFinancialRecords request) {
        List<ResponseGetFinancialRecords> list = service.getFinancialRecords(request);

        if (list.isEmpty()) return ResponseEntity.noContent().build();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createFinancialRecord(@RequestBody RequestCreateFinancialRecords request) {
        return new ResponseEntity<>(service.createFinancialRecord(request), HttpStatus.CREATED);
    }
}