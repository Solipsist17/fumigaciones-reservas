package com.fumigaciones_ica_sac.fumigaciones.controllers;

import com.fumigaciones_ica_sac.fumigaciones.domain.usuario.Usuario;
import com.fumigaciones_ica_sac.fumigaciones.domain.usuario.UsuarioRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //@RequestMapping(value = "usuario/{id}")
    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        return new Usuario(id,"user","123",true);
    }

    @GetMapping
    public List<Usuario> consultar(/*@RequestHeader(value = "Authorization") String token*/) {
        /*String usuarioId = jwtUtil.getKey(token);
        if (usuarioId == null) {
            return new ArrayList<>();
        }*/

        return usuarioRepository.findAll();
    }

    @PostMapping
    public void registrar(@RequestBody Usuario usuario) {
        // CAMBIAR POR BCRYPT ALGORITHM //////////////////////////////////////////////
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String claveHashed = argon2.hash(1, 1024, 1, usuario.getClave());
        usuario.setClave(claveHashed);
        usuarioRepository.save(usuario);
    }

    @PutMapping
    @Transactional
    public void modificar(@RequestBody Usuario usuario) {
        Usuario usuarioModificacion = usuarioRepository.getReferenceById(usuario.getId());
        usuarioModificacion.actualizar(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
    }

}
