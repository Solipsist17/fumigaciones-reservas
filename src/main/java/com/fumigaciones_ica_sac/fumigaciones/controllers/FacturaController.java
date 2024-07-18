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
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.*;

import java.io.FileNotFoundException;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/facturas")
public class FacturaController {

    private final FacturaRepository facturaRepository;
    private final RegistroFacturaService registroFacturaService;
    private final ReporteFacturaService reporteFacturaService;

    @Autowired
    public FacturaController(FacturaRepository facturaRepository, RegistroFacturaService registroFacturaService, ReporteFacturaService reporteFacturaService) {
        this.facturaRepository = facturaRepository;
        this.registroFacturaService = registroFacturaService;
        this.reporteFacturaService = reporteFacturaService;
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

    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("facturasReport", "facturasReport.pdf");
        return ResponseEntity.ok().headers(headers).body(reporteFacturaService.exportPdf());
    }

    @GetMapping("/export-xls")
    public ResponseEntity<byte[]> exportXls() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("facturasReport" + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(reporteFacturaService.exportXls());
    }
}
