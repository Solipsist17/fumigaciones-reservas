package com.fumigaciones_ica_sac.fumigaciones.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
//@RequestMapping("/prueba")
public class PruebaController {

    @PreAuthorize("hasAuthority('ROLE_USER')") // Ajusta según tu configuración de roles
    @GetMapping(value = "/prueba", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getUsuariosHtml() throws IOException {
        ClassPathResource htmlResource = new ClassPathResource("static/prueba.html");
        String htmlContent = StreamUtils.copyToString(htmlResource.getInputStream(), StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE)
                .body(htmlContent);
    }


    /*PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = "/a.html")
    public ResponseEntity<String> getProtectedPage() {
        // Lógica para verificar el token (si es necesario)
        System.out.println("Desde /a.html GetMapping");
        return ResponseEntity.ok("Contenido de la página protegida"); // O carga la página desde un archivo HTML
    }*/

}
