package com.fumigaciones_ica_sac.fumigaciones.infra.security;

import com.fumigaciones_ica_sac.fumigaciones.domain.usuario.Usuario;
import com.fumigaciones_ica_sac.fumigaciones.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Intercepta las solicitudes http y verifica la validez del token JWT
 */

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    /*
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // obtenemos el authHeader
        var authHeader = request.getHeader("Authorization");
        System.out.println("AuthHeader: " + authHeader);
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            System.out.println(token);
            // verificamos si es válido
            var nombreUsuario = tokenService.getSubject(token);
            if (nombreUsuario != null) {
                // autenticamos
                var usuario = usuarioRepository.findByNombre(nombreUsuario);
                var authentication = new UsernamePasswordAuthenticationToken(
                        usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
    */

    /*
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        // obtenemos el auth

        if (token != null) {
            String nombreUsuario = tokenService.getSubject(token);
            if (nombreUsuario != null) {
                Usuario usuario = (Usuario) usuarioRepository.findByNombre(nombreUsuario);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
    */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Verificar si existe el token en el encabezado Authorization
        String authHeader = request.getHeader("Authorization");
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            System.out.println("AuthHeader: " + authHeader);
            token = authHeader.replace("Bearer ", "");
        }

        // Si no se encontró en el encabezado, intentar obtenerlo de la cookie
        if (token == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("token".equals(cookie.getName())) {
                        System.out.println("cookie: " + cookie.getValue());
                        token = cookie.getValue();
                        break;
                    }
                }
            }
        }

        if (token != null) {
            // Verificar si el token es válido
            String nombreUsuario = tokenService.getSubject(token);
            if (nombreUsuario != null) {
                Usuario usuario = (Usuario) usuarioRepository.findByNombre(nombreUsuario);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
