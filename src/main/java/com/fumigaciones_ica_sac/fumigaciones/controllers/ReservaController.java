package com.fumigaciones_ica_sac.fumigaciones.controllers;

import com.fumigaciones_ica_sac.fumigaciones.domain.reserva.*;
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

@RestController
@RequestMapping(value = "/reservas")
public class ReservaController {

    private final ReservaRepository reservaRepository;
    private final RegistroReservaService registroReservaService;

    @Autowired
    public ReservaController(ReservaRepository reservaRepository, RegistroReservaService registroReservaService) {
        this.reservaRepository = reservaRepository;
        this.registroReservaService = registroReservaService;
    }

    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid RegistrarReservaDTO reservaDTO, UriComponentsBuilder uriComponentsBuilder) {
        var responseDTO = registroReservaService.registrarReserva(reservaDTO);
        URI uri = uriComponentsBuilder.path("/reservas/{id}").buildAndExpand(responseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(responseDTO); // code 201
    }


    @GetMapping
    public ResponseEntity<Page<ListadoReservaDTO>> listar(@PageableDefault(size=10, page=0, sort = "id") Pageable paginacion) {
        return ResponseEntity.ok(reservaRepository.findAll(paginacion)
                .map(reserva -> new ListadoReservaDTO(reserva))); // code 200
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaReservaDTO> modificar(@RequestBody @Valid ActualizarReservaDTO reservaDTO) {
        Reserva reservaModificacion = reservaRepository.getReferenceById(reservaDTO.id());
        reservaModificacion.actualizar(reservaDTO);
        return ResponseEntity.ok(new RespuestaReservaDTO(reservaModificacion)); // code 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaReservaDTO> eliminar(@PathVariable Long id) {
        Reserva reserva = reservaRepository.getReferenceById(id);
        reservaRepository.delete(reserva);
        return ResponseEntity.noContent().build(); // code 204
    }
}
