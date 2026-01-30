package com.tienda.videojuegos.controller;

import com.tienda.videojuegos.model.Venta;
import com.tienda.videojuegos.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API para operaciones de tienda: comprar, ver ventas.
 */
@RestController
@RequestMapping("/api/tienda")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    @PostMapping("/comprar")  // POST con body { "clienteId": 1, "videojuegoId": 3 }
    public ResponseEntity<?> comprar(@RequestBody CompraRequest request) {
        try {
            Venta venta = tiendaService.realizarCompra(
                request.getClienteId(),
                request.getVideojuegoId()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(venta);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/ventas")
    public ResponseEntity<List<Venta>> obtenerTodasLasVentas() {
        List<Venta> ventas = tiendaService.obtenerTodasLasVentas();
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/ventas/cliente/{clienteId}")  // historial de compras de un cliente
    public ResponseEntity<List<Venta>> obtenerHistorialCliente(@PathVariable Long clienteId) {
        List<Venta> ventas = tiendaService.obtenerHistorialCliente(clienteId);
        return ResponseEntity.ok(ventas);
    }

    public static class CompraRequest {
        private Long clienteId;
        private Long videojuegoId;

        public Long getClienteId() {
            return clienteId;
        }

        public void setClienteId(Long clienteId) {
            this.clienteId = clienteId;
        }

        public Long getVideojuegoId() {
            return videojuegoId;
        }

        public void setVideojuegoId(Long videojuegoId) {
            this.videojuegoId = videojuegoId;
        }
    }

    /** DTO para devolver errores en JSON: { "mensaje": "..." } */
    public static class ErrorResponse {
        private String mensaje;

        public ErrorResponse(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    }
}