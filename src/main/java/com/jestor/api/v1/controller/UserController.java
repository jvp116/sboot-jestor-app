package com.jestor.api.v1.controller;

import com.jestor.domain.model.dto.UserDTO;
import com.jestor.infrastructure.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService service;

    @PutMapping("/password")
    public ResponseEntity<UserDTO> updatePassword(@RequestBody UserDTO dto) {
        service.updatePassword(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUser(@RequestBody UserDTO dto) {
        service.delete(dto.getEmail());
        return ResponseEntity.noContent().build();
    }
}
