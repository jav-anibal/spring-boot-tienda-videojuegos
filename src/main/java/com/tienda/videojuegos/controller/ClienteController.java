package com.tienda.videojuegos.controller;

import com.tienda.videojuegos.model.Cliente;
import com.tienda.videojuegos.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API para Cliente. Todas las rutas bajo /api/clientes.
 * Recibe HTTP, llama al Service, devuelve JSON con codigo de estado.
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping  // GET /api/clientes
    public ResponseEntity<List<Cliente>> listarTodos() {
        List<Cliente> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")  // GET /api/clientes/1
    public ResponseEntity<Cliente> obtenerPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)           // si existe -> 200 OK
                .orElse(ResponseEntity.notFound().build());  // si no -> 404
    }

    @PostMapping  // POST /api/clientes con JSON en body
    public ResponseEntity<Cliente> crear(@Valid @RequestBody Cliente cliente) {
        try {
            Cliente nuevoCliente = clienteService.crear(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);  // 201
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();  // 409 si email duplicado
        }
    }

    @PutMapping("/{id}")  // PUT /api/clientes/1
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

    @DeleteMapping("/{id}")  // DELETE /api/clientes/1
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            clienteService.eliminar(id);
            return ResponseEntity.noContent().build();  // 204 sin cuerpo
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}