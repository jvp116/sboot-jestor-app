package com.jestor.domain.service;

import com.jestor.domain.exception.BusinessException;
import com.jestor.domain.exception.UserNotFoundException;
import com.jestor.domain.model.Usuario;
import com.jestor.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CadastroUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario save(Usuario usuario) {
        usuarioRepository.detach(usuario);

        Optional<Usuario> userFound = usuarioRepository.findByEmail(usuario.getEmail());

        if (userFound.isPresent() && !userFound.get().equals(usuario)) {
            throw new BusinessException(
                    String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void alterPassword(Long userId, String password, String newPassword) {
        Usuario usuario = searchOrFail(userId);

        if (usuario.passwordNotMatch(password)) {
            throw new BusinessException("Senha atual informada não coincide com a senha do usuário.");
        }

        usuario.setSenha(newPassword);
    }

    public Usuario searchOrFail(Long userId) {
        return usuarioRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}