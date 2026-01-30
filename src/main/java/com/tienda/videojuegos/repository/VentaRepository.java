package com.tienda.videojuegos.repository;

import com.tienda.videojuegos.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Acceso a BD para Venta.
 * findByClienteId usa la relacion: venta tiene cliente_id (propiedad "cliente").
 */
@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByClienteId(Long clienteId);  // ventas de un cliente

    List<Venta> findAllByOrderByFechaDesc();  // mas recientes primero


    // metodo para verificar si un videojuego tiene ventas
    boolean existsByVideoJuegoId(Long videoJuegoId);
}
