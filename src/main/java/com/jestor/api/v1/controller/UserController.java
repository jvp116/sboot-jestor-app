package com.jestor.api.v1.controller;

import com.jestor.core.security.auth.RegisterRequest;
import com.jestor.domain.model.user.User;
import com.jestor.infrastructure.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService service;

    @PutMapping("/{usuarioId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> editPassword(@PathVariable Long usuarioId, @RequestBody @Valid String password) {
//        service.editPassword(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deleteUser(@PathVariable Long id) {
//        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
