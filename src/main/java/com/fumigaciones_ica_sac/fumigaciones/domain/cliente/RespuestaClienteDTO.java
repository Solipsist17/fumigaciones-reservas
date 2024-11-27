package com.fumigaciones_ica_sac.fumigaciones.domain.cliente;

public record RespuestaClienteDTO(Long id, String nombre, String apellido, Sexo sexo, String dni, String telefono, Boolean activo) {
    public RespuestaClienteDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getSexo(), cliente.getDni(),
                cliente.getTelefono(), cliente.getActivo());
    }
}
