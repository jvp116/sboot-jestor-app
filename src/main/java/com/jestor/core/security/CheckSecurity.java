package com.jestor.core.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {

//
//    public @interface Pedidos {
//
//        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
//        @PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') or "
//                + "@algaSecurity.usuarioAutenticadoIgual(returnObject.cliente.id) or "
//                + "@algaSecurity.gerenciaRestaurante(returnObject.restaurante.id)")
//        @Retention(RUNTIME)
//        @Target(METHOD)
//        public @interface PodeBuscar {
//        }
//
//        @PreAuthorize("@algaSecurity.podePesquisarPedidos(#filtro.clienteId, #filtro.restauranteId)")
//        @Retention(RUNTIME)
//        @Target(METHOD)
//        public @interface PodePesquisar {
//        }
//
//        @PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
//        @Retention(RUNTIME)
//        @Target(METHOD)
//        public @interface PodeCriar {
//        }
//
//        @PreAuthorize("@algaSecurity.podeGerenciarPedidos(#codigoPedido)")
//        @Retention(RUNTIME)
//        @Target(METHOD)
//        public @interface PodeGerenciarPedidos {
//        }
//
//    }

    public @interface UsuariosGruposPermissoes {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and "
                + "@algaSecurity.usuarioAutenticadoIgual(#usuarioId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAlterarPropriaSenha {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') or "
                + "@algaSecurity.usuarioAutenticadoIgual(#usuarioId))")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAlterarUsuario {
        }

        @PreAuthorize("@algaSecurity.podeEditarUsuariosGruposPermissoes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeEditar {
        }


        @PreAuthorize("@algaSecurity.podeConsultarUsuariosGruposPermissoes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar {
        }

    }
}
