package com.jestor.domain.exception;

public class UserNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String mensagem) {
        super(mensagem);
    }

    public UserNotFoundException(Long userId) {
        this(String.format("Não existe um cadastro de usuário com código %d", userId));
    }

}
