package com.tienda.videojuegos.service;

import com.tienda.videojuegos.model.Videojuego;
import com.tienda.videojuegos.repository.VideojuegoRepository;
import com.tienda.videojuegos.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Logica de negocio para Videojuego. CRUD + busquedas por genero y ofertas.
 */
@Service
public class VideojuegoService {

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    public List<Videojuego> listarTodos() {
        return videojuegoRepository.findAll();
    }

    public Optional<Videojuego> buscarPorId(Long id) {
        return videojuegoRepository.findById(id);
    }

    public List<Videojuego> buscarPorGenero(String genero) {
        return videojuegoRepository.findByGenero(genero);
    }

    public List<Videojuego> buscarOfertas(BigDecimal precioMax) {
        return videojuegoRepository.findByPrecioLessThan(precioMax);  // precio < precioMax
    }

    public Videojuego crear(Videojuego v) {
        validarVideojuego(v);
        return videojuegoRepository.save(v);
    }

    private void validarVideojuego(Videojuego v) {
        if (v.getTitulo() == null || v.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El titulo es obligatorio");
        }
        if (v.getGenero() == null || v.getGenero().isBlank()) {
            throw new IllegalArgumentException("El genero es obligatorio");
        }
        if (v.getPrecio() == null || v.getPrecio().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (v.getStock() == null || v.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }

    public Videojuego actualizar(Long id, Videojuego datos) {
        validarVideojuego(datos);
        Optional<Videojuego> opt = videojuegoRepository.findById(id);
        if (opt.isEmpty()) {
            throw new RuntimeException("Videojuego no encontrado");
        }
        Videojuego v = opt.get();
        v.setTitulo(datos.getTitulo());
        v.setGenero(datos.getGenero());
        v.setPrecio(datos.getPrecio());
        v.setStock(datos.getStock());
        return videojuegoRepository.save(v);
    }

    public void eliminar(Long id) {
        Optional<Videojuego> opt = videojuegoRepository.findById(id);
        if (opt.isEmpty()) {
            throw new RuntimeException("Videojuego no encontrado");
        }
        if (ventaRepository.existsByVideoJuegoId(id)) {
            throw new RuntimeException("CONFLICT");
        }
        videojuegoRepository.delete(opt.get());
    }
}
