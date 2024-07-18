package com.fumigaciones_ica_sac.fumigaciones.controllers;

import com.fumigaciones_ica_sac.fumigaciones.domain.usuario.AuthenticationUsuarioDTO;
import com.fumigaciones_ica_sac.fumigaciones.domain.usuario.RegisterUsuarioDTO;
import com.fumigaciones_ica_sac.fumigaciones.domain.usuario.Usuario;
import com.fumigaciones_ica_sac.fumigaciones.domain.usuario.UsuarioDTO;
import com.fumigaciones_ica_sac.fumigaciones.infra.security.JwtTokenDTO;
import com.fumigaciones_ica_sac.fumigaciones.infra.security.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
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

    /*
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
    */

    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@RequestBody AuthenticationUsuarioDTO authenticationUsuarioDTO, HttpServletResponse response) {
        // Crear token de autenticación
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authenticationUsuarioDTO.nombre(),
                authenticationUsuarioDTO.clave()
        );

        System.out.println("Usuario nombre: " + authenticationUsuarioDTO.nombre());
        System.out.println("Usuario clave: " + authenticationUsuarioDTO.clave());

        // Autenticar usuario
        Authentication usuarioAutenticado = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(usuarioAutenticado);

        // Generar JWT
        String jwtToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        // Almacenar el token en una cookie HttpOnly
        Cookie cookie = new Cookie("token", jwtToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/app");
        response.addCookie(cookie);

        return ResponseEntity.ok(new JwtTokenDTO(jwtToken));
    }

    /*
    @PostMapping(value = "/logout")
    public ResponseEntity logoutUsuario() {
        return ResponseEntity.ok("LOGOUT");
    }
    */

    @PostMapping(value = "/logout")
    public ResponseEntity<?> logoutUsuario(HttpServletRequest request, HttpServletResponse response) {
        // limpiar la cookie
        //new CookieClearingLogoutHandler("token").logout(request, response, null);


        // Crear una cookie con el mismo nombre, valor vacío y tiempo de vida negativo para eliminarla
        Cookie cookie = new Cookie("token", "");
        cookie.setHttpOnly(true);
        cookie.setPath("/app");
        cookie.setMaxAge(0);
        response.addCookie(cookie);



        return ResponseEntity.ok("LOGOUT");
    }

    @PostMapping(value = "/validate-token")
    public ResponseEntity loginForm() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/me")
    public ResponseEntity obtenerUsuarioActual(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        System.out.println(usuario);
        return ResponseEntity.ok(new UsuarioDTO(usuario));
    }
}
