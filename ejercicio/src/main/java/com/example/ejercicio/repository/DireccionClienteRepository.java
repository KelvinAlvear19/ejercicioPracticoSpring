package com.example.ejercicio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ejercicio.model.Cliente;
import com.example.ejercicio.model.DireccionCliente;

@Repository
public interface DireccionClienteRepository extends JpaRepository<DireccionCliente, Long > {
    List<DireccionCliente>findByCliente(Cliente cliente);

}
