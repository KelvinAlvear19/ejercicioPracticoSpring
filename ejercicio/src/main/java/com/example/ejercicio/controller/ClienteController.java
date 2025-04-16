package com.example.ejercicio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ejercicio.dto.ClienteConDireccionMatrizDTO;
import com.example.ejercicio.model.Cliente;
import com.example.ejercicio.model.DireccionCliente;
import com.example.ejercicio.service.ClienteDtoMapper;
import com.example.ejercicio.service.ClienteService;

@RestController
@RequestMapping("api/v1/clientes")

public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    
    //crear cliente    
    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.guardarCliente(cliente));

    }


    //buscar por cedula o nombre

    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteConDireccionMatrizDTO>> buscarCliente(
            @RequestParam(required = false) String nombres,
            @RequestParam(required = false) String numeroIdentificacion) {
    
        List<Cliente> clientes = clienteService.buscarClientes(nombres, numeroIdentificacion);
        List<ClienteConDireccionMatrizDTO> dtos = clientes.stream()
                .map(ClienteDtoMapper::toClienteConDireccionMatrizDTO)
                .toList();
    
        return ResponseEntity.ok(dtos);
    }
    
    // listar clientes
    @GetMapping
    public List<Cliente>listar(){
        return clienteService.listar();
    }
    
    //listar cliente por id
    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteConDireccionMatrizDTO> obtenerClientePorId(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = clienteService.buscarPorId(clienteId);

        if (cliente.isPresent()) {
            ClienteConDireccionMatrizDTO dto = ClienteDtoMapper.toClienteConDireccionMatrizDTO(cliente.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // mostrar las direcciones de un cliente
    @GetMapping("/{clienteId}/direcciones")
    public ResponseEntity<List<DireccionCliente>> obtenerDireccionesPorClienteId(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = clienteService.buscarPorId(clienteId);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get().getDirecciones());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar cliente por id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
        clienteService.eliminar(id);
        return ResponseEntity.ok("Cliente eliminado exitosamente");

    }

        // Editar cliente
    @PutMapping("/{id}")
    public ResponseEntity<?> editarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            Cliente actualizado = clienteService.editarCliente(id, cliente);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }
    
   
}
