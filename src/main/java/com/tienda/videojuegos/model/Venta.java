package com.tienda.videojuegos.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad Venta: registra cada compra. Relaciona Cliente + Videojuego.
 * ManyToOne: muchas ventas pueden pertenecer al mismo cliente/videojuego.
 */
@NoArgsConstructor
@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal coste;

    @ManyToOne  // FK: cada venta tiene un cliente
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne  // FK: cada venta tiene un videojuego
    @JoinColumn(name = "videojuego_id", nullable = false)
    private Videojuego videoJuego;

    /** Constructor para crear venta: asigna fecha automática */
    public Venta(BigDecimal coste, Cliente cliente, Videojuego videoJuego) {
        this.coste = coste;
        this.cliente = cliente;
        this.videoJuego = videoJuego;
        this.fecha = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public BigDecimal getCoste() {
        return coste;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Videojuego getVideoJuego() {
        return videoJuego;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setCoste(BigDecimal coste) {
        this.coste = coste;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setVideoJuego(Videojuego videoJuego) {
        this.videoJuego = videoJuego;
    }
}



