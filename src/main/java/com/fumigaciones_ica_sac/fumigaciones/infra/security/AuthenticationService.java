package com.fumigaciones_ica_sac.fumigaciones.infra.security;

import com.fumigaciones_ica_sac.fumigaciones.controllers.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Carga los detalles del usuario desde la BD
 */

@Service
public class AuthenticationService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public AuthenticationService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByNombre(username);
    }
}
