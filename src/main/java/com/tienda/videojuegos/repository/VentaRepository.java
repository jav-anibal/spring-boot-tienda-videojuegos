package com.tienda.videojuegos.repository;

import com.tienda.videojuegos.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {


    // Buscar todas las ventas de un cliente específico
    // SELECT * FROM venta WHERE cliente_id = ?
    List<Venta> findByClienteId(Long clienteId);

    // Buscar todas las ventas de un videojuego específico
    // SELECT * FROM venta WHERE videojuego_id = ?
    List<Venta> findByVideojuegoId(Long videojuegoId);

    // Buscar ventas ordenadas por fecha descendente (más reciente primero)
    // SELECT * FROM venta ORDER BY fecha DESC
    List<Venta> findAllByOrderByFechaDesc();
}
