package com.tienda.videojuegos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor

@Entity
@Table (name = "videojuego")
public class Videojuego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El título no puede ser nulo")
    @Column(nullable = false, length = 255)
    private String titulo;

    @NotNull(message = "El género no puede ser nulo")
    @Column(nullable = false, length = 255)
    private String genero;

    @NotNull(message = "El género no puede ser nulo")
    @Min(value = 0, message = "El precio no puede ser negativo")
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal precio;

    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(nullable = false)
    private Integer stock;
}
