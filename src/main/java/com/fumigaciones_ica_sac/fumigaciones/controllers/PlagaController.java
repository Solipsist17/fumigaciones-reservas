package com.fumigaciones_ica_sac.fumigaciones.controllers;

import com.fumigaciones_ica_sac.fumigaciones.domain.plaga.Plaga;
import com.fumigaciones_ica_sac.fumigaciones.domain.plaga.PlagaRepository;
import com.fumigaciones_ica_sac.fumigaciones.domain.producto.Producto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/plagas")
public class PlagaController {

    private final PlagaRepository plagaRepository;

    @Autowired
    public PlagaController(PlagaRepository plagaRepository) {
        this.plagaRepository = plagaRepository;
    }

    @PostMapping
    public void registrar(@RequestBody Plaga plaga) {
        plagaRepository.save(plaga);
    }

    @GetMapping
    public List<Plaga> consultar() {
        return plagaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plaga> consultarPorId(@PathVariable Long id) {
        return plagaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    @Transactional
    public void modificar(@RequestBody Plaga plaga) {
        Plaga plagaModificacion = plagaRepository.getReferenceById(plaga.getId());
        plagaModificacion.actualizar(plaga);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        Plaga plaga = plagaRepository.getReferenceById(id);
        plagaRepository.delete(plaga);
    }
}
