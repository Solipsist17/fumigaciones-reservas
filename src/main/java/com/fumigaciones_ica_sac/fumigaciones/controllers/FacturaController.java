package com.fumigaciones_ica_sac.fumigaciones.controllers;

import com.fumigaciones_ica_sac.fumigaciones.domain.factura.*;
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
import java.util.Optional;

@RestController
@RequestMapping(value = "/facturas")
public class FacturaController {

    private final FacturaRepository facturaRepository;
    private final RegistroFacturaService registroFacturaService;

    @Autowired
    public FacturaController(FacturaRepository facturaRepository, RegistroFacturaService registroFacturaService) {
        this.facturaRepository = facturaRepository;
        this.registroFacturaService = registroFacturaService;
    }

    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid RegistrarFacturaDTO facturaDTO, UriComponentsBuilder uriComponentsBuilder) {
        var responseDTO = registroFacturaService.registrarFactura(facturaDTO);
        URI uri = uriComponentsBuilder.path("/facturas/{id}").buildAndExpand(responseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(responseDTO); // code 201
    }

    @GetMapping
    public ResponseEntity<Page<ListadoFacturaDTO>> listar(@PageableDefault(size=10, page=0, sort = "id") Pageable paginacion) {
        return ResponseEntity.ok(facturaRepository.findAll(paginacion)
                .map(factura -> new ListadoFacturaDTO(factura))); // code 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaFacturaDTO> consultarPorId(@PathVariable Long id) {
        Optional<Factura> facturaOptional = facturaRepository.findById(id);
        if (facturaOptional.isPresent()) {
            Factura factura = facturaOptional.get();
            RespuestaFacturaDTO facturaDTO = new RespuestaFacturaDTO(factura);
            return ResponseEntity.ok(facturaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaFacturaDTO> modificar(@RequestBody @Valid ActualizarFacturaDTO facturaDTO) {
        Factura facturaModificacion = facturaRepository.getReferenceById(facturaDTO.id());
        facturaModificacion.actualizar(facturaDTO);
        return ResponseEntity.ok(new RespuestaFacturaDTO(facturaModificacion)); // code 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaFacturaDTO> eliminar(@PathVariable Long id) {
        Factura factura = facturaRepository.getReferenceById(id);
        facturaRepository.delete(factura);
        return ResponseEntity.noContent().build(); // code 204
    }
}
