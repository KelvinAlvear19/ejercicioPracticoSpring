package com.example.ejercicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ejercicio.model.Cliente;
import com.example.ejercicio.model.DireccionCliente;
import com.example.ejercicio.service.ClienteService;

@RestController
@RequestMapping("api/v1/direcciones")
public class DireccionController {
    @Autowired
    private ClienteService clienteService;


    // insertar direccion en un cliente
    @PostMapping("{numeroIdentificacion}")
    public ResponseEntity<Cliente> agregarDireccion(@PathVariable String numeroIdentificacion, @RequestBody DireccionCliente nuevaDireccion){
        return clienteService.buscarPorIdentificacion(numeroIdentificacion).map(cliente->{
            nuevaDireccion.setCliente(cliente);
            cliente.getDirecciones().add(nuevaDireccion);
            return ResponseEntity.ok(clienteService.guardarCliente(cliente));

        }).orElse(ResponseEntity.notFound().build());
    }
}