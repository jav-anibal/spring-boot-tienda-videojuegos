package com.tienda.videojuegos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre no puede ser nulo")
    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(nullable = false, length = 255, unique = true)
    private String email;

    @NotNull(message = "El saldo no puede ser nulo")
    @Min(value = 0, message = "No puede tener un saldo negativo")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal saldo;
}
