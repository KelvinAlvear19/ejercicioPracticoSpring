package com.example.ejercicio.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import com.example.ejercicio.dto.ClienteConDireccionMatrizDTO;
import com.example.ejercicio.model.Cliente;
import com.example.ejercicio.model.DireccionCliente;
import com.example.ejercicio.model.TipoIdentificacion;
import com.example.ejercicio.service.ClienteService;

public class ClienteControllerTest {

    private ClienteService clienteServiceMock;
    private ClienteController clienteController;



    @BeforeEach
    void setUp(){
        clienteServiceMock = Mockito.mock(ClienteService.class);
        clienteController = new ClienteController(clienteServiceMock);
    }

    @Test
    void testCrear() {
        Cliente cliente = new Cliente();
        cliente.setTipoIdentificacion(TipoIdentificacion.valueOf("CEDULA"));
        cliente.setNumeroIdentificacion("1805232373");
        cliente.setNombres("Kevin Alvear");
        cliente.setCorreo("kevin.alvear@email.com");
        cliente.setCelular("1991234567");

        DireccionCliente direccion = new DireccionCliente();
        direccion.setProvincia("Tungurahua");
        direccion.setCiudad("Ambato");
        direccion.setDireccion("Av.  Guaytambos miniarica");
        direccion.setMatriz(true);
        cliente.setDirecciones(Collections.singletonList(direccion));

        // Simula el servicio devolviendo ese cliente
        when(clienteServiceMock.guardarCliente(cliente)).thenReturn(cliente);

        // Ejecuta el método del controlador
        ResponseEntity<Cliente> response = clienteController.crear(cliente);

        // Verifica
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Kevin Alvear", response.getBody().getNombres());
        assertEquals("1805232373", response.getBody().getNumeroIdentificacion());
    }
    
    @Test
    void testListar() {
        // Crear clientes mock con datos adicionales
        Cliente cliente1 = new Cliente();
        cliente1.setTipoIdentificacion(TipoIdentificacion.CEDULA);
        cliente1.setNumeroIdentificacion("1805232373");
        cliente1.setNombres("Kevin Alvear");
        cliente1.setCorreo("kevin.alvear@email.com");
        cliente1.setCelular("1991234567");

        DireccionCliente direccion1 = new DireccionCliente();
        direccion1.setProvincia("Tungurahua");
        direccion1.setCiudad("Ambato");
        direccion1.setDireccion("Av. Guaytambos miniarica");
        direccion1.setMatriz(true);
        cliente1.setDirecciones(Arrays.asList(direccion1));

        Cliente cliente2 = new Cliente();
        cliente2.setTipoIdentificacion(TipoIdentificacion.RUC);
        cliente2.setNumeroIdentificacion("A123456789");
        cliente2.setNombres("Juan Perez");
        cliente2.setCorreo("juan.perez@email.com");
        cliente2.setCelular("1997654321");

        DireccionCliente direccion2 = new DireccionCliente();
        direccion2.setProvincia("Pichincha");
        direccion2.setCiudad("Quito");
        direccion2.setDireccion("Calle Falsa 123");
        direccion2.setMatriz(false);
        cliente2.setDirecciones(Arrays.asList(direccion2));

        // Lista de clientes que simula el servicio
        List<Cliente> clientesMock = Arrays.asList(cliente1, cliente2);

        // Simula el comportamiento del servicio
        when(clienteServiceMock.listar()).thenReturn(clientesMock);

        // Ejecuta el método del controlador
        List<Cliente> response = clienteController.listar();

        // Verifica que la respuesta no sea nula
        assertNotNull(response);
        // Verifica que la lista tenga exactamente 2 elementos
        assertEquals(2, response.size());

        // Verifica el contenido del primer cliente
        Cliente clienteResponse1 = response.get(0);
        assertEquals("Kevin Alvear", clienteResponse1.getNombres());
        assertEquals("1805232373", clienteResponse1.getNumeroIdentificacion());
        assertEquals("kevin.alvear@email.com", clienteResponse1.getCorreo());
        assertEquals("1991234567", clienteResponse1.getCelular());
        assertEquals("Tungurahua", clienteResponse1.getDirecciones().get(0).getProvincia());

        // Verifica el contenido del segundo cliente
        Cliente clienteResponse2 = response.get(1);
        assertEquals("Juan Perez", clienteResponse2.getNombres());
        assertEquals("A123456789", clienteResponse2.getNumeroIdentificacion());
        assertEquals("juan.perez@email.com", clienteResponse2.getCorreo());
        assertEquals("1997654321", clienteResponse2.getCelular());
        assertEquals("Pichincha", clienteResponse2.getDirecciones().get(0).getProvincia());
    }

     @Test
    void testObtenerClientePorId() {
        // Crear un cliente de prueba
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setTipoIdentificacion(TipoIdentificacion.valueOf("CEDULA"));
        cliente.setNumeroIdentificacion("1805232373");
        cliente.setNombres("Kevin Alvear");
        cliente.setCorreo("kevin.alvear@email.com");
        cliente.setCelular("1991234567");

        DireccionCliente direccion = new DireccionCliente();
        direccion.setProvincia("Tungurahua");
        direccion.setCiudad("Ambato");
        direccion.setDireccion("Av. Guaytambos miniárica");
        direccion.setMatriz(true);
        cliente.setDirecciones(Collections.singletonList(direccion));

        // Simula el comportamiento del servicio, devolviendo el cliente cuando se busque por su ID
        when(clienteServiceMock.buscarPorId(1L)).thenReturn(Optional.of(cliente));

        // Ejecuta el método del controlador
        ResponseEntity<ClienteConDireccionMatrizDTO> response = clienteController.obtenerClientePorId(1L);

        // Verifica que la respuesta sea 200 OK
        assertEquals(200, response.getStatusCodeValue());

        // Verifica que el cliente esté presente en la respuesta
        assertEquals("Kevin Alvear", response.getBody().getNombres());
        assertEquals("1805232373", response.getBody().getNumeroIdentificacion());
    }

    @Test
    void testObtenerDireccionesPorClienteId() {
        // Crear un cliente de prueba
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setTipoIdentificacion(TipoIdentificacion.valueOf("CEDULA"));
        cliente.setNumeroIdentificacion("1805232373");
        cliente.setNombres("Kevin Alvear");
        cliente.setCorreo("kevin.alvear@email.com");
        cliente.setCelular("1991234567");

        DireccionCliente direccion = new DireccionCliente();
        direccion.setProvincia("Tungurahua");
        direccion.setCiudad("Ambato");
        direccion.setDireccion("Av. Guaytambos miniárica");
        direccion.setMatriz(true);

        // Asignamos la lista de direcciones al cliente
        cliente.setDirecciones(Collections.singletonList(direccion));

        // Simula el comportamiento del servicio, devolviendo el cliente cuando se busque por su ID
        when(clienteServiceMock.buscarPorId(1L)).thenReturn(Optional.of(cliente));

        // Ejecuta el método del controlador
        ResponseEntity<List<DireccionCliente>> response = clienteController.obtenerDireccionesPorClienteId(1L);

        // Verifica que la respuesta sea 200 OK
        assertEquals(200, response.getStatusCodeValue());

        // Verifica que la lista de direcciones no esté vacía y contenga la dirección esperada
        assertEquals(1, response.getBody().size());
        assertEquals("Tungurahua", response.getBody().get(0).getProvincia());
        assertEquals("Av. Guaytambos miniárica", response.getBody().get(0).getDireccion());
    }

     @Test
    void testEliminar() {
        // ID del cliente que vamos a eliminar
        Long clienteId = 1L;

        // Simula que el servicio elimina el cliente sin problemas
        doNothing().when(clienteServiceMock).eliminar(clienteId);

        // Ejecuta el método del controlador
        ResponseEntity<String> response = clienteController.eliminar(clienteId);

        // Verifica que el código de estado sea 200 OK
        assertEquals(200, response.getStatusCodeValue());

        // Verifica que el mensaje sea el esperado
        assertEquals("Cliente eliminado exitosamente", response.getBody());

        // Verifica que el método de eliminación haya sido llamado una vez
        verify(clienteServiceMock, times(1)).eliminar(clienteId);
    }
    void testEditarCliente() {
        Long clienteId = 1L;
        // Creamos un cliente con los nuevos datos que vamos a actualizar
        Cliente cliente = new Cliente();
        cliente.setTipoIdentificacion(TipoIdentificacion.valueOf("CEDULA"));
        cliente.setNumeroIdentificacion("1805232373");
        cliente.setNombres("Nuevo Nombre");
        cliente.setCorreo("nuevo.correo@email.com");
        cliente.setCelular("2991234567");

        // Simulamos que el servicio devuelve el cliente actualizado
        when(clienteServiceMock.editarCliente(clienteId, cliente)).thenReturn(cliente);

        // Ejecutamos el método del controlador
        ResponseEntity<?> response = clienteController.editarCliente(clienteId, cliente);

        // Verificamos que el código de estado sea 200 OK
        assertEquals(200, response.getStatusCodeValue());

        // Verificamos que el cliente actualizado esté en la respuesta
        assertEquals(cliente, response.getBody());
        
        // Verificamos que el servicio fue llamado correctamente
        verify(clienteServiceMock, times(1)).editarCliente(clienteId, cliente);
    }
    @Test
    void testMapearClienteADto() {
        // Crear una dirección matriz
        DireccionCliente direccionMatriz = new DireccionCliente();
        direccionMatriz.setProvincia("Pichincha");
        direccionMatriz.setCiudad("Quito");
        direccionMatriz.setDireccion("Av. 6 de Diciembre 123");
        direccionMatriz.setMatriz(true);
        
        // Crear cliente con una lista de direcciones que incluye la dirección matriz
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setTipoIdentificacion(TipoIdentificacion.valueOf("CEDULA"));
        cliente.setNumeroIdentificacion("1805232373");
        cliente.setNombres("Kevin Alvear");
        cliente.setCorreo("kevin@example.com");
        cliente.setCelular("0998765432");
        cliente.setDirecciones(Collections.singletonList(direccionMatriz));  // Lista con una dirección
        
        // Crear el DTO y mapear los valores
        ClienteConDireccionMatrizDTO dto = new ClienteConDireccionMatrizDTO();
        dto.setId(cliente.getId());
        dto.setTipoIdentificacion(cliente.getTipoIdentificacion());
        dto.setNumeroIdentificacion(cliente.getNumeroIdentificacion());
        dto.setNombres(cliente.getNombres());
        dto.setCorreo(cliente.getCorreo());
        dto.setCelular(cliente.getCelular());
    
        // Mapeamos las direcciones, en este caso solo la matriz
        for (DireccionCliente direccion : cliente.getDirecciones()) {
            if (direccion.isMatriz()) {
                dto.setProvincia(direccion.getProvincia());
                dto.setCiudad(direccion.getCiudad());
                dto.setDireccion(direccion.getDireccion());
            }
        }
    
        // Verificar que los valores del DTO estén correctamente asignados
        assertEquals(cliente.getId(), dto.getId());
        assertEquals(cliente.getTipoIdentificacion(), dto.getTipoIdentificacion());
        assertEquals(cliente.getNumeroIdentificacion(), dto.getNumeroIdentificacion());
        assertEquals(cliente.getNombres(), dto.getNombres());
        assertEquals(cliente.getCorreo(), dto.getCorreo());
        assertEquals(cliente.getCelular(), dto.getCelular());
        assertEquals(direccionMatriz.getProvincia(), dto.getProvincia());
        assertEquals(direccionMatriz.getCiudad(), dto.getCiudad());
        assertEquals(direccionMatriz.getDireccion(), dto.getDireccion());
    }

}
