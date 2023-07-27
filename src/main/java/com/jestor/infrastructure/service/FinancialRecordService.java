package com.jestor.infrastructure.service;

import com.jestor.domain.model.FinancialRecord;
import com.jestor.domain.model.dto.FinancialRecordDTO;
import com.jestor.domain.model.dto.RequestGetFinancialRecords;
import com.jestor.domain.model.dto.ResponseGetFinancialRecords;
import com.jestor.domain.model.user.User;
import com.jestor.infrastructure.repository.FinancialRecordRepository;
import com.jestor.infrastructure.repository.UserRepository;
import com.jestor.infrastructure.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialRecordService {

    @Autowired
    private FinancialRecordRepository repository;

    @Autowired
    private UserRepository userRepository;

    public List<ResponseGetFinancialRecords> getFinancialRecords(RequestGetFinancialRecords request) {
        List<FinancialRecord> financialRecords = repository.getFinancialRecords(request.getEmail(), request.getType());

        List<FinancialRecord> financialRecordList = new ArrayList<>();
        financialRecordList.addAll(financialRecords);

        financialRecordList.forEach(element -> {
            if (element.getDate().getMonth().getValue() != request.getMonth()) {
                financialRecords.remove(element);
            }
        });

        return financialRecords.stream().map(ResponseGetFinancialRecords::new).toList();
    }

    public FinancialRecordDTO insert(FinancialRecordDTO dto) {
        FinancialRecord entity = new FinancialRecord();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new FinancialRecordDTO(entity);
    }

    private void copyDtoToEntity(FinancialRecordDTO dto, FinancialRecord entity) {
        entity.setValue(dto.getValue());
        entity.setDate(dto.getDate());
        entity.setDescription(dto.getDescription());
        entity.setUser(dto.getUser());
        entity.setCategory(dto.getCategory());
    }
}
