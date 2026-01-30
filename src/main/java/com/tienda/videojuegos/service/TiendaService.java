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
import java.util.Optional;

/**
 * Logica de compras. Usa los 3 repositorios porque una compra afecta cliente, videojuego y venta.
 */
@Service
public class TiendaService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    /** @Transactional: si falla algo en medio, se deshace t-do (rollback) */
    @Transactional
    public Venta realizarCompra(Long clienteId, Long videojuegoId) {
        // 1. Buscar cliente y videojuego
        Optional<Cliente> optCliente = clienteRepository.findById(clienteId);
        if (optCliente.isEmpty()) {
            throw new RuntimeException("Cliente no encontrado");
        }
        Optional<Videojuego> optVj = videojuegoRepository.findById(videojuegoId);
        if (optVj.isEmpty()) {
            throw new RuntimeException("Videojuego no encontrado");
        }

        Cliente cliente = optCliente.get();
        Videojuego vj = optVj.get();

        // 2. Validar reglas de negocio
        if (vj.getStock() <= 0) {
            throw new RuntimeException("Sin stock");
        }
        if (cliente.getSaldo().compareTo(vj.getPrecio()) < 0) {  // compareTo: -1 si menor
            throw new RuntimeException("Saldo insuficiente");
        }

        // 3. Actualizar saldo y stock
        cliente.setSaldo(cliente.getSaldo().subtract(vj.getPrecio()));
        clienteRepository.save(cliente);

        vj.setStock(vj.getStock() - 1);
        videojuegoRepository.save(vj);

        // 4. Crear registro de venta
        Venta venta = new Venta(vj.getPrecio(), cliente, vj);
        return ventaRepository.save(venta);
    }

    public List<Venta> obtenerHistorialCliente(Long clienteId) {
        return ventaRepository.findByClienteId(clienteId);
    }

    public List<Venta> obtenerTodasLasVentas() {
        return ventaRepository.findAllByOrderByFechaDesc();
    }
}