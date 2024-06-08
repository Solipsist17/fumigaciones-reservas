package com.fumigaciones_ica_sac.fumigaciones.controllers;

import com.fumigaciones_ica_sac.fumigaciones.domain.usuario.AuthenticationUsuarioDTO;
import com.fumigaciones_ica_sac.fumigaciones.domain.usuario.Usuario;
import com.fumigaciones_ica_sac.fumigaciones.infra.security.JwtTokenDTO;
import com.fumigaciones_ica_sac.fumigaciones.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(@RequestBody AuthenticationUsuarioDTO authenticationUsuarioDTO) {
        // creamos un token
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authenticationUsuarioDTO.nombre(),
                authenticationUsuarioDTO.clave()
        );

        System.out.println("Usario nombre: " + authenticationUsuarioDTO.nombre());
        System.out.println("Usario clave: " + authenticationUsuarioDTO.clave());

        var usuarioAutenticado = authenticationManager.authenticate(authToken);

        // generamos el jwt
        var jwtToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new JwtTokenDTO(jwtToken));
    }

    @PostMapping(value = "/logout")
    public ResponseEntity logoutUsuario() {
        return ResponseEntity.ok("LOGOUT");
    }

    @PostMapping(value = "/validate-token")
    public ResponseEntity loginForm() {
        return ResponseEntity.ok("OK");
    }
}
