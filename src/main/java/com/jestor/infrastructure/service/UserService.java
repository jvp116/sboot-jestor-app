package com.jestor.infrastructure.service;

import com.jestor.domain.model.dto.UserDTO;
import com.jestor.domain.model.user.User;
import com.jestor.infrastructure.repository.UserRepository;
import com.jestor.infrastructure.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserDTO updatePassword(UserDTO userDTO) {
        User userFound = getEntityByEmail(userDTO.getEmail());
        userFound.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return new UserDTO(repository.save(userFound));
    }

    @Transactional
    public void delete(String email) {
        repository.deleteByEmail(email);
    }

    private User getEntityByEmail(String email) {
        Optional<User> result = repository.findByEmail(email);
        return result.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
    }
}