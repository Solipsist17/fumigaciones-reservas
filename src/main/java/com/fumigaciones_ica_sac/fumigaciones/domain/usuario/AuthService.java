package com.fumigaciones_ica_sac.fumigaciones.domain.usuario;

import com.fumigaciones_ica_sac.fumigaciones.controllers.UsuarioRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        /*List<Usuario> lista = usuarioRepository.findByNombre(usuario.getNombre());
        if (!lista.isEmpty()) {
            // verificamos la clave
            String claveHashed = lista.get(0).getClave(); // clave hasheada de la db
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            boolean mismaClave = argon2.verify(claveHashed, usuario.getClave());

            if (mismaClave) {
                return usuario;
            } else {
                return null;
            }

        } else {
            return null;
        }*/
        return null;
    }
}
