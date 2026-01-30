package com.tienda.videojuegos.repository;

import com.tienda.videojuegos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Acceso a BD para Cliente. Hereda findAll, findById, save, delete...
 * Spring Data genera el SQL segun el nombre del metodo (findByEmail -> WHERE email = ?)
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);  // puede no existir -> Optional

    boolean existsByEmail(String email);  // para validar antes de crear
}
