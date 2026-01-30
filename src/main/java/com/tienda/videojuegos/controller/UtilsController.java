package com.tienda.videojuegos.controller;

import com.tienda.videojuegos.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint para cargar datos de prueba. POST /api/utils/seed
 */
@RestController
@RequestMapping("/api/utils")
public class UtilsController {

    @Autowired
    private UtilsService utilsService;

    @PostMapping("/seed")  // Borra t-do y crea videojuegos + clientes de prueba
    public ResponseEntity<String> cargarDatosDePrueba() {
        String mensaje = utilsService.cargarDatosDePrueba();
        return ResponseEntity.ok(mensaje);
    }
}