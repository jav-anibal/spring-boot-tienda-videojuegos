package com.tienda.videojuegos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Entidad Cliente: representa la tabla "cliente" en la BD.
 * BigDecimal para saldo evita errores de redondeo con dinero.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ID autoincremental por la BD
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(unique = true)
    private String email;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal saldo;
}
