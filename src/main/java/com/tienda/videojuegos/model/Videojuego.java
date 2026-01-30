package com.tienda.videojuegos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Entidad Videojuego: representa la tabla "videojuego" en la BD.
 * Stock = unidades disponibles para vender.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "videojuego")
public class Videojuego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String titulo;

    @NotNull
    @Column(nullable = false)
    private String genero;

    @NotNull
    @Min(0)  // Precio no puede ser negativo
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @NotNull
    @Min(0)  // Stock no puede ser negativo
    @Column(nullable = false)
    private Integer stock;
}
