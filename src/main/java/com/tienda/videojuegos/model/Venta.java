package com.tienda.videojuegos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


//Muchas ventas pueden pertenecer a un mismo cliente -> @ManyToOne
@Data
@NoArgsConstructor
@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @NotNull(message = "El coste no puede ser nulo")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal coste;

    @ManyToOne // (FK) Muchas ventas pueden pertenecer a un mismo client.
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne // (FK)
    @JoinColumn(name = "videojuego_id", nullable = false)
    private VideoJuego videoJuego;

    public Venta(BigDecimal coste, Cliente cliente, VideoJuego videoJuego) {
        this.fecha = fecha;
        this.coste = coste;
        this.cliente = cliente;
        this.videoJuego = videoJuego;
        this.fecha = LocalDateTime.now(); // fecha actual
    }
}



