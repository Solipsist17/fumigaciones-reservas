package com.fumigaciones_ica_sac.fumigaciones.controllers;

import com.fumigaciones_ica_sac.fumigaciones.domain.servicio.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/servicios")
public class ServicioController {

    private final ServicioRepository servicioRepository;
    private final RegistroServicioService registroServicioService;

    @Autowired
    public ServicioController(ServicioRepository servicioRepository, RegistroServicioService registroServicioService) {
        this.servicioRepository = servicioRepository;
        this.registroServicioService = registroServicioService;
    }

    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid RegistrarServicioDTO servicioDTO, UriComponentsBuilder uriComponentsBuilder) {
        var responseDTO = registroServicioService.registrarServicio(servicioDTO);
        URI uri = uriComponentsBuilder.path("/servicios/{id}").buildAndExpand(responseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(responseDTO); // code 201
    }

    @GetMapping
    public ResponseEntity<Page<ListadoServicioDTO>> listar(@PageableDefault(size=10, page=0, sort = "id")Pageable paginacion) {
        return ResponseEntity.ok(servicioRepository.findAll(paginacion)
                .map(servicio -> new ListadoServicioDTO(servicio))); // code 200
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaServicioDTO> modificar(@RequestBody @Valid ActualizarServicioDTO servicioDTO) {
        Servicio servicioModificacion = servicioRepository.getReferenceById(servicioDTO.id());
        servicioModificacion.actualizar(servicioDTO);
        return ResponseEntity.ok(new RespuestaServicioDTO(servicioModificacion)); // code 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaServicioDTO> eliminar(@PathVariable Long id) {
        Servicio servicio = servicioRepository.getReferenceById(id);
        servicioRepository.delete(servicio);
        return ResponseEntity.noContent().build(); // code 204
    }
}
