package com.tienda.videojuegos.controller;

import com.tienda.videojuegos.model.Venta;
import com.tienda.videojuegos.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private TiendaService tiendaService;

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Venta>> obtenerHistorialCliente(@PathVariable Long idCliente) {
        List<Venta> ventas = tiendaService.obtenerHistorialCliente(idCliente);
        return ResponseEntity.ok(ventas);
    }
}
