package com.jestor.domain.exception;

public class GroupNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public GroupNotFoundException(String mensagem) {
        super(mensagem);
    }

    public GroupNotFoundException(Long groupId) {
        this(String.format("Não existe um cadastro de grupo com código %d", groupId));
    }

}
