package com.tienda.videojuegos.controller;

import com.tienda.videojuegos.model.Videojuego;
import com.tienda.videojuegos.service.VideojuegoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    @Autowired
    private VideojuegoService videojuegoService;

    /**
     * GET /api/videojuegos
     * Listar todos los videojuegos
     */
    @GetMapping
    public ResponseEntity<List<Videojuego>> listarTodos() {
        List<Videojuego> videojuegos = videojuegoService.listarTodos();
        return ResponseEntity.ok(videojuegos);
    }

    /**
     * GET /api/videojuegos/{id}
     * Obtener un videojuego por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Videojuego> obtenerPorId(@PathVariable Long id) {
        return videojuegoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET /api/videojuegos/genero/{genero}
     * Buscar videojuegos por género
     */
    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Videojuego>> buscarPorGenero(@PathVariable String genero) {
        List<Videojuego> videojuegos = videojuegoService.buscarPorGenero(genero);
        return ResponseEntity.ok(videojuegos);
    }

    /**
     * GET /api/videojuegos/oferta?maxPrecio=50.00
     * Buscar videojuegos por rango de precio
     */
    @GetMapping("/oferta")
    public ResponseEntity<List<Videojuego>> buscarOfertas(@RequestParam BigDecimal maxPrecio) {
        List<Videojuego> videojuegos = videojuegoService.buscarOfertas(maxPrecio);
        return ResponseEntity.ok(videojuegos);
    }

    /**
     * POST /api/videojuegos
     * Crear un nuevo videojuego
     */
    @PostMapping
    public ResponseEntity<Videojuego> crear(@Valid @RequestBody Videojuego videojuego) {
        Videojuego nuevoVideojuego = videojuegoService.crear(videojuego);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVideojuego);
    }

    /**
     * PUT /api/videojuegos/{id}
     * Actualizar un videojuego existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<Videojuego> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Videojuego videojuego) {
        try {
            Videojuego videojuegoActualizado = videojuegoService.actualizar(id, videojuego);
            return ResponseEntity.ok(videojuegoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE /api/videojuegos/{id}
     * Eliminar un videojuego
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            videojuegoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}