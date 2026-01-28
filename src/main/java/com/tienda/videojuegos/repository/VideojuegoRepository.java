package com.tienda.videojuegos.repository;

import com.tienda.videojuegos.model.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {

    // Buscar por género (Query Method)
    // SELECT * FROM videojuego WHERE genero = ?
    List<Videojuego> findByGenero(String genero);

    // Buscar juegos con precio menor al indicado (Query Method)
    // SELECT * FROM videojuego WHERE precio < ?
    List<Videojuego> findByPrecioLessThan(BigDecimal precio);

    // Buscar juegos con stock mayor a 0 (Query Method)
    // SELECT * FROM videojuego WHERE stock > 0
    List<Videojuego> findByStockGreaterThan(Integer stock);

}
