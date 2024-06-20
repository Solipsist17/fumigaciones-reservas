package com.fumigaciones_ica_sac.fumigaciones.controllers;

import com.fumigaciones_ica_sac.fumigaciones.domain.producto.Producto;
import com.fumigaciones_ica_sac.fumigaciones.domain.producto.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/productos")
public class ProductoController {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @PostMapping
    public void registrar(@RequestBody Producto producto) {
        productoRepository.save(producto);
    }

    @GetMapping
    public List<Producto> consultar() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> consultarPorId(@PathVariable Long id) {
        return productoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    @Transactional
    public void modificar(@RequestBody Producto producto) {
        Producto productoModificacion = productoRepository.getReferenceById(producto.getId());
        productoModificacion.actualizar(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        Producto producto = productoRepository.getReferenceById(id);
        productoRepository.delete(producto);
    }
}
