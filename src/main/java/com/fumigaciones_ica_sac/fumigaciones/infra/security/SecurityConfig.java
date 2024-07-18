package com.fumigaciones_ica_sac.fumigaciones.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuración de Spring Security: tipo (stateless, stateful), rutas protegidas, encriptado
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private SecurityFilter securityFilter;

    @Autowired
    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/logout").permitAll()
                        .requestMatchers("/auth/login/validate-token").permitAll()
                        //.requestMatchers(HttpMethod.GET, "/app/**").permitAll()
                        /*
                        .requestMatchers(HttpMethod.GET, "/app/index.html", "/app/usuarios.html", "/app/clientes.html", "/app/maquinarias.html", "/app/plagas.html", "/app/productos.html")
                        .hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/app/index.html", "/app/servicios.html", "/app/reservas.html", "/app/facturas.html")
                        .hasRole("GERENTE")
                        */
                        .requestMatchers(HttpMethod.GET, "/app/usuarios.html", "/app/clientes.html", "/app/maquinarias.html", "/app/plagas.html", "/app/productos.html")
                        .hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/app/servicios.html", "/app/reservas.html", "/app/facturas.html", "/facturas/export-pdf")
                        .hasRole("GERENTE")
                        .requestMatchers(HttpMethod.GET, "/app/index.html").hasAnyRole("ADMINISTRADOR", "GERENTE")
                        /*
                        .requestMatchers("/app/login.html").permitAll()
                        .requestMatchers("/app/principal.html").permitAll()
                        .requestMatchers(HttpMethod.GET, "/app/usuarios.html").hasAnyRole("ADMINISTRADOR", "GERENTE")
                        .requestMatchers("/productos.html").permitAll()
                        .requestMatchers("/plagas.html").permitAll()
                        .requestMatchers("/maquinarias.html").permitAll()
                        .requestMatchers("/servicios.html").permitAll()
                        .requestMatchers("/facturacion_y_pagos.html").permitAll()
                        .requestMatchers("/editarMaquinaria.html").permitAll()
                        .requestMatchers("/agregarMaquinaria.html").permitAll()
                        .requestMatchers("/agregarProducto.html").permitAll()
                        .requestMatchers("/editarProducto.html").permitAll()
                        .requestMatchers("/registrarPlaga.html").permitAll()
                        .requestMatchers("/editarPlaga.html").permitAll()
<<<<<<< HEAD
                        .requestMatchers("/index.html").hasAnyRole("ADMINISTRADOR", "GERENTE")
=======
                        .requestMatchers("/registrarServicio.html").permitAll()
                        .requestMatchers("/editarServicio.html").permitAll()
                        .requestMatchers("/index.html").permitAll()
                        .requestMatchers("/cards.html").permitAll()
                        .requestMatchers("/register.html").permitAll()
>>>>>>> 34b8e1f943f92c2fb652ded16604a6f5a6041821
                        .requestMatchers("/a.html").permitAll()
                        */
                        .requestMatchers("/assets/**", "/css/**", "/js/**", "/img/**", "/scss/**", "/vendor/**", "/img/**").permitAll()
                        .anyRequest().authenticated()
                ).formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                                .loginPage("/app/login.html")
                                .permitAll()
                        /*.defaultSuccessUrl(getSuccessUrlForRole(), true))
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutSuccessUrl("/app/login.html")*/
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private String getSuccessUrlForRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals("ROLE_ADMINISTRADOR")) {
                    return "/app/usuarios.html";
                } else if (authority.getAuthority().equals("ROLE_GERENTE")) {
                    return "/app/servicios.html";
                }
            }
        }
        return "/app/login.html";
    }


}