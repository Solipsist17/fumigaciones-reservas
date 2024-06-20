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
                        .requestMatchers("/login.html").permitAll()
                        .requestMatchers("/principal.html").permitAll()
                        .requestMatchers("/usuarios.html").permitAll()
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
                        .requestMatchers("/index.html").permitAll()
                        .requestMatchers("/a.html").permitAll()
                        .requestMatchers("/assets/**", "/css/**", "/js/**", "/img/**", "/scss/**", "/vendor/**").permitAll()
                        .anyRequest().authenticated()
                ).formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/principal.html")
                        .permitAll()
                        .defaultSuccessUrl("/usuarios.html"))
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutSuccessUrl("/login.html")
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

}
