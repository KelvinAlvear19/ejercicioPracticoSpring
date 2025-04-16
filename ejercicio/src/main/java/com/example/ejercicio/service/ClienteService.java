package com.example.ejercicio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ejercicio.model.Cliente;
import com.example.ejercicio.model.DireccionCliente;
import com.example.ejercicio.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente guardarCliente(Cliente cliente){
        long matrizCount = cliente.getDirecciones().stream().filter(DireccionCliente::isMatriz).count();
        if(matrizCount>1){
            throw new IllegalArgumentException("Solo puede existir una direccion matriz");
        }

        cliente.getDirecciones().forEach(d->d.setCliente(cliente));
        return clienteRepository.save(cliente);
    }



    public List<Cliente> buscarClientes(String nombres, String numeroIdentificacion) {
        if (numeroIdentificacion != null && !numeroIdentificacion.isEmpty()) {
            return clienteRepository.findByNumeroIdentificacion(numeroIdentificacion)
                    .map(List::of) 
                    .orElse(List.of()); 
        } else if (nombres != null && !nombres.isEmpty()) {
            return clienteRepository.findByNombres(nombres);
        }
        return clienteRepository.findAll(); 
    }



    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente>buscarPorId(Long id){
        return clienteRepository.findById(id);
    }

    public void eliminar(Long id){
        clienteRepository.deleteById(id);
    }

    

    public Optional<Cliente> buscarPorIdentificacion(String numero){
        return clienteRepository.findByNumeroIdentificacion(numero);

    }
    public Cliente editarCliente(Long id, Cliente nuevoCliente) {
        Cliente existente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        if (!existente.getNumeroIdentificacion().equals(nuevoCliente.getNumeroIdentificacion()) &&
            clienteRepository.existsByNumeroIdentificacion(nuevoCliente.getNumeroIdentificacion())) {
            throw new IllegalArgumentException("Ya existe otro cliente con ese número de identificación");
        }

        existente.setTipoIdentificacion(nuevoCliente.getTipoIdentificacion());
        existente.setNumeroIdentificacion(nuevoCliente.getNumeroIdentificacion());
        existente.setNombres(nuevoCliente.getNombres());
        existente.setCorreo(nuevoCliente.getCorreo());
        existente.setCelular(nuevoCliente.getCelular());

        return clienteRepository.save(existente);
    }




}
