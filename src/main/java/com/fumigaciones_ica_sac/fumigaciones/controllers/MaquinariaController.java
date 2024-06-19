package com.fumigaciones_ica_sac.fumigaciones.controllers;

import com.fumigaciones_ica_sac.fumigaciones.domain.maquinaria.Maquinaria;
import com.fumigaciones_ica_sac.fumigaciones.domain.maquinaria.MaquinariaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/maquinarias")
public class MaquinariaController {

    private final MaquinariaRepository maquinariaRepository;

    @Autowired
    public MaquinariaController(MaquinariaRepository maquinariaRepository) {
        this.maquinariaRepository = maquinariaRepository;
    }

    @PostMapping
    public void registrar(@RequestBody Maquinaria maquinaria) {
        maquinariaRepository.save(maquinaria);
    }

    @GetMapping
    public List<Maquinaria> consultar() {
        return maquinariaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maquinaria> consultarPorId(@PathVariable Long id) {
        return maquinariaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    @Transactional
    public void modificar(@RequestBody Maquinaria maquinaria) {
        Maquinaria maquinariaModificacion = maquinariaRepository.getReferenceById(maquinaria.getId());
        maquinariaModificacion.actualizar(maquinaria);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        Maquinaria maquinaria = maquinariaRepository.getReferenceById(id);
        maquinariaRepository.delete(maquinaria);
    }
}
