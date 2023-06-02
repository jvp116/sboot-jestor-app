package com.jestor.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Grupo {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable(name = "grupo_permissao", joinColumns = @JoinColumn(name = "grupo_id"), inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    private Set<Permissao> permissoes = new HashSet<>();

    public boolean removePermission(Permissao permissao) {
        return getPermissoes().remove(permissao);
    }

    public boolean addPermission(Permissao permissao) {
        return getPermissoes().add(permissao);
    }

}