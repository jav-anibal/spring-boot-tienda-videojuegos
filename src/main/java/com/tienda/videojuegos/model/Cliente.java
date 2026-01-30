package com.tienda.videojuegos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Column(nullable = false)
    private String nombre;

    @Column(unique = true)  // No puede haber dos clientes con el mismo email
    private String email;

    @NotNull
    @Min(0)  // El saldo no puede ser negativo
    @Column(nullable = false, precision = 10, scale = 2)  // 10 digitos, 2 decimales
    private BigDecimal saldo;
}
