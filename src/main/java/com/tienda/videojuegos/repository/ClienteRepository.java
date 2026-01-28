package com.tienda.videojuegos.repository;

import com.tienda.videojuegos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Buscar cliente por email (debe ser único)
    // SELECT * FROM cliente WHERE email = ?
    Optional<Cliente> findByEmail(String email);

    // Verificar si existe un cliente con ese email
    // SELECT COUNT(*) > 0 FROM cliente WHERE email = ?
    boolean existsByEmail(String email);
}
