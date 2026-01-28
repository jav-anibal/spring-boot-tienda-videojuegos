package com.tienda.videojuegos.repository;

import com.tienda.videojuegos.model.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository // Marca la clase como un componente de acceso a datos
public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {


    // SELECT * FROM videojuego WHERE genero = ?
    List<Videojuego> findByGenero(String genero);


    // SELECT * FROM videojuego WHERE precio < ?
    List<Videojuego> findByPrecioLessThan(BigDecimal precio);


    // SELECT * FROM videojuego WHERE stock > 0
    List<Videojuego> findByStockGreaterThan(Integer stock);

}
