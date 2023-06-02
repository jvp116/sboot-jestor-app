package com.jestor.domain.exception;

public class PermissionNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public PermissionNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public PermissionNotFoundException(Long permissionId) {
		this(String.format("Não existe um cadastro de permissão com código %d", permissionId));
	}
	
}
