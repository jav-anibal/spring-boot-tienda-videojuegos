package com.tienda.videojuegos.service;

import com.tienda.videojuegos.model.Cliente;
import com.tienda.videojuegos.model.Venta;
import com.tienda.videojuegos.model.Videojuego;
import com.tienda.videojuegos.repository.ClienteRepository;
import com.tienda.videojuegos.repository.VentaRepository;
import com.tienda.videojuegos.repository.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TiendaService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    /**
     * Realiza la compra de un videojuego por parte de un cliente.
     * Operación transaccional: tdo o nada.
     */
    @Transactional
    public Venta realizarCompra(Long clienteId, Long videojuegoId) {
        // 1. Verificar que el cliente existe
        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + clienteId));

        // 2. Verificar que el videojuego existe
        Videojuego videojuego = videojuegoRepository.findById(videojuegoId)
            .orElseThrow(() -> new RuntimeException("Videojuego no encontrado con id: " + videojuegoId));

        // 3. Verificar que hay stock disponible
        if (videojuego.getStock() <= 0) {
            throw new RuntimeException("No hay stock disponible para el videojuego: " + videojuego.getTitulo());
        }

        // 4. Verificar que el cliente tiene saldo suficiente
        if (cliente.getSaldo().compareTo(videojuego.getPrecio()) < 0) {
            throw new RuntimeException("Saldo insuficiente. Necesitas: " + videojuego.getPrecio() +
                                     ", tienes: " + cliente.getSaldo());
        }

        // 5. Restar el precio del saldo del cliente
        BigDecimal nuevoSaldo = cliente.getSaldo().subtract(videojuego.getPrecio());
        cliente.setSaldo(nuevoSaldo);
        clienteRepository.save(cliente);

        // 6. Restar 1 unidad al stock del videojuego
        videojuego.setStock(videojuego.getStock() - 1);
        videojuegoRepository.save(videojuego);

        // 7. Crear y guardar el registro de venta
        Venta venta = new Venta(videojuego.getPrecio(), cliente, videojuego);
        return ventaRepository.save(venta);
    }

    // Obtener historial de compras de un cliente
    public List<Venta> obtenerHistorialCliente(Long clienteId) {
        // Verificar que el cliente existe
        clienteRepository.findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + clienteId));

        return ventaRepository.findByClienteId(clienteId);
    }

    // Obtener todas las ventas
    public List<Venta> obtenerTodasLasVentas() {
        return ventaRepository.findAllByOrderByFechaDesc();
    }
}