package com.fumigaciones_ica_sac.fumigaciones.domain.usuario;

public record UsuarioDTO(Long id, String nombre, Rol rol, Boolean activo) {
    public UsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getRol(), usuario.getActivo());
    }
}
