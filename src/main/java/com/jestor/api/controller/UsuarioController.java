package com.jestor.api.controller;

import com.jestor.domain.model.Usuario;
import com.jestor.domain.repository.UsuarioRepository;
import com.jestor.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroUsuarioService cadastroUsuario;

    @GetMapping("/{usuarioId}")
    public Usuario search(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuario.searchOrFail(usuarioId);

        return usuario;
    }

    @GetMapping("/foo")
    public String foo(BearerTokenAuthentication authentication) {
        return authentication.getTokenAttributes().get("sub") + " is the subject";
    }

    @GetMapping("/foo2")
    public String foo(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        return principal.getAttribute("sub") + " is the subject";
    }

    //    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Usuario add(@RequestBody @Valid Usuario usuario) {
//        Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
//        usuario = cadastroUsuario.salvar(usuario);
//
//        return usuarioModelAssembler.toModel(usuario);
//    }

//    @PutMapping("/{usuarioId}")
//    public UsuarioModel atualizar(@PathVariable Long usuarioId,
//                                  @RequestBody @Valid UsuarioInput usuarioInput) {
//        Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
//        usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
//        usuarioAtual = cadastroUsuario.salvar(usuarioAtual);
//
//        return usuarioModelAssembler.toModel(usuarioAtual);
//    }

//    @Override
//    @PutMapping("/{usuarioId}/senha")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
//        cadastroUsuario.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
//    }

}
