package com.tienda.videojuegos.repository;

import com.tienda.videojuegos.model.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Acceso a BD para Videojuego.
 * findByPrecioLessThan = videojuegos con precio menor que X (ofertas).
 */
@Repository
public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {

    List<Videojuego> findByGenero(String genero);  // WHERE genero = ?

    List<Videojuego> findByPrecioLessThan(BigDecimal precio);  // WHERE precio < ?
}
