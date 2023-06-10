package com.jestor.api.controller;

import com.jestor.domain.model.Usuario;
import com.jestor.domain.repository.UsuarioRepository;
import com.jestor.domain.service.CadastroUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroUsuarioService cadastroUsuario;

    @GetMapping("/{usuarioId}")
    public Usuario getUserById(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuario.searchOrFail(usuarioId);

        return usuario;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario createUser(@RequestBody @Valid Usuario usuario) {
        usuario = cadastroUsuario.save(usuario);

        return usuario;
    }

//    @PutMapping("/{usuarioId}")
//    public Usuario updateUser(@PathVariable Long usuarioId, @RequestBody @Valid Usuario usuario) {
//        Usuario usuarioAtual = cadastroUsuario.searchOrFail(usuarioId);
//        usuarioAtual = cadastroUsuario.save(usuarioAtual);
//
//        return usuario;
//    }

//    @Override
//    @PutMapping("/{usuarioId}/senha")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
//        cadastroUsuario.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
//    }

}
