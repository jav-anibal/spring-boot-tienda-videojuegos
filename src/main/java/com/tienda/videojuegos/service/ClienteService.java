package com.tienda.videojuegos.service;

import com.tienda.videojuegos.model.Cliente;
import com.tienda.videojuegos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Logica de negocio para Cliente. El Controller llama aqui, nunca al Repository directo.
 */
@Service
public class ClienteService {

    @Autowired  // Spring inyecta el repositorio
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);  // Optional: puede no existir
    }

    public Optional<Cliente> buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public Cliente crear(Cliente cliente) {
        validarCliente(cliente);
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new RuntimeException("Email ya existe");
        }
        return clienteRepository.save(cliente);
    }

    private void validarCliente(Cliente c) {
        if (c.getNombre() == null || c.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (c.getSaldo() == null || c.getSaldo().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El saldo no puede ser negativo");
        }
    }

    public Cliente actualizar(Long id, Cliente datos) {
        validarCliente(datos);
        Optional<Cliente> opt = clienteRepository.findById(id);
        if (opt.isEmpty()) {
            throw new RuntimeException("Cliente no encontrado");
        }
        Cliente c = opt.get();
        c.setNombre(datos.getNombre());
        c.setEmail(datos.getEmail());
        c.setSaldo(datos.getSaldo());
        return clienteRepository.save(c);
    }

    public void eliminar(Long id) {
        Optional<Cliente> opt = clienteRepository.findById(id);
        if (opt.isEmpty()) {
            throw new RuntimeException("Cliente no encontrado");
        }
        clienteRepository.delete(opt.get());
    }
}