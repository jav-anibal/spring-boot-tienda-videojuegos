package com.tienda.videojuegos.controller;

import com.tienda.videojuegos.model.Venta;
import com.tienda.videojuegos.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tienda")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    /**
     * POST /api/tienda/comprar
     * Realizar una compra
     * Body: { "clienteId": 1, "videojuegoId": 3 }
     */
    @PostMapping("/comprar")
    public ResponseEntity<?> comprar(@RequestBody CompraRequest request) {
        try {
            Venta venta = tiendaService.realizarCompra(
                request.getClienteId(),
                request.getVideojuegoId()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(venta);
        } catch (RuntimeException e) {
            // Puede ser: cliente no existe, videojuego no existe, sin stock, sin saldo
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * GET /api/tienda/ventas
     * Obtener todas las ventas
     */
    @GetMapping("/ventas")
    public ResponseEntity<List<Venta>> obtenerTodasLasVentas() {
        List<Venta> ventas = tiendaService.obtenerTodasLasVentas();
        return ResponseEntity.ok(ventas);
    }

    /**
     * GET /api/tienda/ventas/cliente/{clienteId}
     * Obtener historial de compras de un cliente
     */
    @GetMapping("/ventas/cliente/{clienteId}")
    public ResponseEntity<?> obtenerHistorialCliente(@PathVariable Long clienteId) {
        try {
            List<Venta> ventas = tiendaService.obtenerHistorialCliente(clienteId);
            return ResponseEntity.ok(ventas);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Clases auxiliares para request/response

    /**
     * DTO para la petición de compra
     */
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

    /**
     * DTO para respuestas de error
     */
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