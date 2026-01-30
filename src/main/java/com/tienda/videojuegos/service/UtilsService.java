package com.tienda.videojuegos.service;

import com.tienda.videojuegos.model.Cliente;
import com.tienda.videojuegos.model.Videojuego;
import com.tienda.videojuegos.repository.ClienteRepository;
import com.tienda.videojuegos.repository.VentaRepository;
import com.tienda.videojuegos.repository.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UtilsService {

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VentaRepository ventaRepository;

    /**
     * Borra todos los datos y carga datos de prueba
     */
    @Transactional
    public String cargarDatosDePrueba() {
        // 1. Borrar todos los datos existentes
        ventaRepository.deleteAll();
        videojuegoRepository.deleteAll();
        clienteRepository.deleteAll();

        // 2. Crear videojuegos de prueba
        Videojuego v1 = new Videojuego(null, "The Legend of Zelda", "Aventura", new BigDecimal("59.99"), 10);
        Videojuego v2 = new Videojuego(null, "Super Mario Odyssey", "Plataformas", new BigDecimal("49.99"), 15);
        Videojuego v3 = new Videojuego(null, "Elden Ring", "RPG", new BigDecimal("69.99"), 8);
        Videojuego v4 = new Videojuego(null, "FIFA 24", "Deportes", new BigDecimal("59.99"), 20);
        Videojuego v5 = new Videojuego(null, "Call of Duty", "Acción", new BigDecimal("69.99"), 12);
        Videojuego v6 = new Videojuego(null, "Animal Crossing", "Simulación", new BigDecimal("39.99"), 25);

        videojuegoRepository.save(v1);
        videojuegoRepository.save(v2);
        videojuegoRepository.save(v3);
        videojuegoRepository.save(v4);
        videojuegoRepository.save(v5);
        videojuegoRepository.save(v6);

        // 3. Crear clientes de prueba
        Cliente c1 = new Cliente(null, "Juan Pérez", "juan@email.com", new BigDecimal("200.00"));
        Cliente c2 = new Cliente(null, "María García", "maria@email.com", new BigDecimal("150.00"));
        Cliente c3 = new Cliente(null, "Pedro López", "pedro@email.com", new BigDecimal("500.00"));
        Cliente c4 = new Cliente(null, "Ana Martínez", "ana@email.com", new BigDecimal("50.00"));

        clienteRepository.save(c1);
        clienteRepository.save(c2);
        clienteRepository.save(c3);
        clienteRepository.save(c4);

        return "Datos de prueba cargados correctamente: " +
               videojuegoRepository.count() + " videojuegos y " +
               clienteRepository.count() + " clientes";
    }
}