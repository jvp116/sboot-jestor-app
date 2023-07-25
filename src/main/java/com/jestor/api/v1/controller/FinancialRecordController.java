package com.jestor.api.v1.controller;

import com.jestor.domain.model.dto.FinancialRecordDTO;
import com.jestor.domain.model.dto.UserDTO;
import com.jestor.infrastructure.service.FinancialRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/financial-record")
public class FinancialRecordController {

    private final FinancialRecordService service;

    @GetMapping
    public ResponseEntity getFinancialRecords(@RequestBody UserDTO dto) {
        List<FinancialRecordDTO> list = service.findAllByUser(dto);

        if (list.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody FinancialRecordDTO dto) {
        return ResponseEntity.ok(service.insert(dto));
    }

}
