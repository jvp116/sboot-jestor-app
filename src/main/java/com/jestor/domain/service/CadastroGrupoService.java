package com.jestor.domain.service;

import com.jestor.domain.exception.EntityInUseException;
import com.jestor.domain.exception.GroupNotFoundException;
import com.jestor.domain.model.Permissao;
import com.jestor.domain.model.Grupo;
import com.jestor.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroGrupoService {

    private static final String MSG_GRUPO_EM_USO = "UserGroup de código %d não pode ser removido, pois está em uso";

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroPermissaoService cadastroPermissaoService;

    @Transactional
    public Grupo save(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void delete(Long groupId) {
        try {
            grupoRepository.deleteById(groupId);
            grupoRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new GroupNotFoundException(groupId);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(MSG_GRUPO_EM_USO, groupId));
        }
    }

    @Transactional
    public void disassociatePermission(Long groupId, Long permissionId) {
        Grupo group = searchOrFail(groupId);
        Permissao permissao = cadastroPermissaoService.searchOrFail(permissionId);

        group.removePermission(permissao);
    }

    @Transactional
    public void connectPermission(Long groupId, Long permissionId) {
        Grupo group = searchOrFail(groupId);
        Permissao permissao = cadastroPermissaoService.searchOrFail(permissionId);

        group.addPermission(permissao);
    }

    public Grupo searchOrFail(Long groupId) {
        return grupoRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId));
    }

}
