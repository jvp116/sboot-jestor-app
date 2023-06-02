package com.jestor.domain.service;

import com.jestor.domain.exception.PermissionNotFoundException;
import com.jestor.domain.model.Permissao;
import com.jestor.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao searchOrFail(Long permissionId) {
        return permissaoRepository.findById(permissionId)
                .orElseThrow(() -> new PermissionNotFoundException(permissionId));
    }

}
