package com.example.ejercicio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ejercicio.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente>findByNumeroIdentificacion(String numeroIdentificacion);
    List<Cliente>findByNombres(String nombres);
    boolean existsByNumeroIdentificacion(String numeroIdentificacion);
}
