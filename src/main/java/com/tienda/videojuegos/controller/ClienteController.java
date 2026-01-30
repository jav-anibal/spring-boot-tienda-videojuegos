package com.tienda.videojuegos.controller;

import com.tienda.videojuegos.model.Cliente;
import com.tienda.videojuegos.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /**
     * GET /api/clientes
     * Listar todos los clientes
     */
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        List<Cliente> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    /**
     * GET /api/clientes/{id}
     * Obtener un cliente por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /api/clientes
     * Crear un nuevo cliente
     */
    @PostMapping
    public ResponseEntity<Cliente> crear(@Valid @RequestBody Cliente cliente) {
        try {
            Cliente nuevoCliente = clienteService.crear(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
        } catch (RuntimeException e) {
            // Email duplicado u otro error
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * PUT /api/clientes/{id}
     * Actualizar un cliente existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Cliente cliente) {
        try {
            Cliente clienteActualizado = clienteService.actualizar(id, cliente);
            return ResponseEntity.ok(clienteActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE /api/clientes/{id}
     * Eliminar un cliente
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            clienteService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}