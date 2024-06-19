package com.fumigaciones_ica_sac.fumigaciones.controllers;

import com.fumigaciones_ica_sac.fumigaciones.domain.reserva.ActualizarUsuarioDTO;
import com.fumigaciones_ica_sac.fumigaciones.domain.usuario.RegisterUsuarioDTO;
import com.fumigaciones_ica_sac.fumigaciones.domain.usuario.RespuestaUsuarioDTO;
import com.fumigaciones_ica_sac.fumigaciones.domain.usuario.Usuario;
import com.fumigaciones_ica_sac.fumigaciones.domain.usuario.UsuarioRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    public List<Usuario> consultar() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<RespuestaUsuarioDTO> registrarUsuario(@RequestBody RegisterUsuarioDTO registerUsuarioDTO, UriComponentsBuilder uriComponentsBuilder) {
        String rawPasssword = registerUsuarioDTO.clave();
        String encodedPassword = encoderPassword(rawPasssword);

        System.out.println("Clave: " + rawPasssword);
        System.out.println("Clave hasheada: " + encodedPassword);

        Usuario usuario = new Usuario(null, registerUsuarioDTO.nombre(), encodedPassword, registerUsuarioDTO.activo());
        usuario = usuarioRepository.save(usuario);

        URI uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new RespuestaUsuarioDTO(usuario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaUsuarioDTO> modificar(@RequestBody ActualizarUsuarioDTO actualizarUsuarioDTO) {
        Usuario usuarioModificacion = usuarioRepository.getReferenceById(actualizarUsuarioDTO.id());

        String encodedPassword = encoderPassword(actualizarUsuarioDTO.clave());

        usuarioModificacion.actualizar(actualizarUsuarioDTO);
        usuarioModificacion.setClave(encodedPassword);

        return ResponseEntity.ok(new RespuestaUsuarioDTO(usuarioModificacion));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<RespuestaUsuarioDTO> eliminar(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.setActivo(Boolean.FALSE);
        //usuarioRepository.delete(usuario);
        return ResponseEntity.noContent().build();
    }

    public String encoderPassword(String rawPasssword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(rawPasssword);
    }
}
