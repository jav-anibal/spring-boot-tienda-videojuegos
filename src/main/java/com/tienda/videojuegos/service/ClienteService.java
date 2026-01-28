package com.tienda.videojuegos.service;

import com.tienda.videojuegos.model.Cliente;
import com.tienda.videojuegos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Listar todos los clientes
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    // Buscar por ID
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    // Buscar por email
    public Optional<Cliente> buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    // Crear cliente
    public Cliente crear(Cliente cliente) {
        // Validar que el email no exista
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new RuntimeException("Ya existe un cliente con el email: " + cliente.getEmail());
        }
        return clienteRepository.save(cliente);
    }

    // Actualizar cliente
    public Cliente actualizar(Long id, Cliente clienteActualizado) {
        return clienteRepository.findById(id)
            .map(cliente -> {
                cliente.setNombre(clienteActualizado.getNombre());
                cliente.setEmail(clienteActualizado.getEmail());
                cliente.setSaldo(clienteActualizado.getSaldo());
                return clienteRepository.save(cliente);
            })
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
    }

    // Eliminar cliente
    public void eliminar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));

        clienteRepository.delete(cliente);
    }
}