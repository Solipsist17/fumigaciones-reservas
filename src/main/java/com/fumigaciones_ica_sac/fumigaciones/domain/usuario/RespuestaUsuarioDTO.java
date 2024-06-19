package com.fumigaciones_ica_sac.fumigaciones.domain.usuario;

public record RespuestaUsuarioDTO(Long id, String nombre, String clave, Boolean activo) {
    public RespuestaUsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getClave(), usuario.getActivo());
    }
}
