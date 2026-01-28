package com.tienda.videojuegos.service;

import com.tienda.videojuegos.model.Videojuego;
import com.tienda.videojuegos.repository.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

//@Service -> CAPA -> LOGICA DE NEGOCIO
@Service // Marca esta clase como un componente de servicio -> Permitiendo inyectarlas en otras clases
public class VideojuegoService {

     @Autowired // REPOSITORY <-> SERVICE ->  Conecta las capas
    private VideojuegoRepository videojuegoRepository;

    // Listar todos los videojuegos
    public List<Videojuego> listarTodos() {
        return videojuegoRepository.findAll();
    }

    // Buscar por ID -> Un ID puede no existir en la BD
    //
    // Optional -> evita NullPointerException
    public Optional<Videojuego> buscarPorId(Long id) {
        return videojuegoRepository.findById(id);
    }

    // Buscar por gnero
    public List<Videojuego> buscarPorGenero(String genero) {
        return videojuegoRepository.findByGenero(genero);
    }

    // Buscar ofertas (precio menor a X)
    public List<Videojuego> buscarOfertas(BigDecimal precioMaximo) {
        return videojuegoRepository.findByPrecioLessThan(precioMaximo);
    }

    // Crear videojuego
    public Videojuego crear(Videojuego videojuego) {
        return videojuegoRepository.save(videojuego);
    }

    // Actualizar videojuego
    public Videojuego actualizar(Long id, Videojuego videojuegoActualizado) {
        return videojuegoRepository.findById(id)
            .map(videojuego -> {
                videojuego.setTitulo(videojuegoActualizado.getTitulo());
                videojuego.setGenero(videojuegoActualizado.getGenero());
                videojuego.setPrecio(videojuegoActualizado.getPrecio());
                videojuego.setStock(videojuegoActualizado.getStock());
                return videojuegoRepository.save(videojuego);
            })
            .orElseThrow(() -> new RuntimeException("Videojuego no encontrado con id: " + id));
    }

    // Eliminar videojuego (con validación)
    public void eliminar(Long id) {
        Videojuego videojuego = videojuegoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Videojuego no encontrado con id: " + id));



        videojuegoRepository.delete(videojuego);
    }
}
