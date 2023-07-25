package com.jestor.infrastructure.service;

import com.jestor.domain.model.FinancialRecord;
import com.jestor.domain.model.dto.FinancialRecordDTO;
import com.jestor.domain.model.dto.UserDTO;
import com.jestor.domain.model.user.User;
import com.jestor.infrastructure.repository.FinancialRecordRepository;
import com.jestor.infrastructure.repository.UserRepository;
import com.jestor.infrastructure.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialRecordService {

    @Autowired
    private FinancialRecordRepository repository;

    @Autowired
    private UserRepository userRepository;

    public List<FinancialRecordDTO> findAllByUser(UserDTO userDTO) {
        User userFound = userRepository.findByEmail(userDTO.getEmail()).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));

        List<FinancialRecord> financialRecords = repository.findAllByUser(userFound);

        return financialRecords.stream().map(FinancialRecordDTO::new).toList();
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
