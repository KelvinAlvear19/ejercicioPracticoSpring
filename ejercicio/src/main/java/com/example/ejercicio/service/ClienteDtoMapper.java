package com.example.ejercicio.service;

import org.springframework.stereotype.Component;

import com.example.ejercicio.dto.ClienteConDireccionMatrizDTO;
import com.example.ejercicio.model.Cliente;
import com.example.ejercicio.model.DireccionCliente;

@Component
public class ClienteDtoMapper {
    public static ClienteConDireccionMatrizDTO toClienteConDireccionMatrizDTO(Cliente cliente) {
        // Aquí mapeas los atributos de Cliente al DTO ClienteConDireccionMatrizDTO
        ClienteConDireccionMatrizDTO dto = new ClienteConDireccionMatrizDTO();
        dto.setId(cliente.getId());
        dto.setTipoIdentificacion(cliente.getTipoIdentificacion()); // Si es necesario convertir a String
        dto.setNumeroIdentificacion(cliente.getNumeroIdentificacion());
        dto.setNombres(cliente.getNombres());
        dto.setCorreo(cliente.getCorreo());
        dto.setCelular(cliente.getCelular());

        // Aquí mapeas las direcciones de Cliente
        for (DireccionCliente direccion : cliente.getDirecciones()) {
            if (direccion.isMatriz()) {  // Verifica si la dirección es la matriz
                dto.setProvincia(direccion.getProvincia());
                dto.setCiudad(direccion.getCiudad());
                dto.setDireccion(direccion.getDireccion());
            }
        }

        return dto;
    }
}
