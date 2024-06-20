package com.fumigaciones_ica_sac.fumigaciones.controllers;

import com.fumigaciones_ica_sac.fumigaciones.domain.cliente.Cliente;
import com.fumigaciones_ica_sac.fumigaciones.domain.cliente.ClienteRepository;
import com.fumigaciones_ica_sac.fumigaciones.domain.plaga.Plaga;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public void registrar(@RequestBody Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @GetMapping
    public List<Cliente> consultar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> consultarPorId(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    @Transactional
    public void modificar(@RequestBody Cliente cliente) {
        Cliente clienteModificacion = clienteRepository.getReferenceById(cliente.getId());
        clienteModificacion.actualizar(cliente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        Cliente cliente = clienteRepository.getReferenceById(id);
        clienteRepository.delete(cliente);
    }
}
