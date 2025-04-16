# 💻 ejercicioPracticoSpring

Proyecto de práctica con **Spring Boot** y **PostgreSQL** desarrollado en **Java 17**.  
Incluye funcionalidades básicas para la gestión de clientes y direcciones mediante una API RESTful.

---

## 🚀 Tecnologías

- Java 17 ☕  
- Spring Boot 🌱  
- PostgreSQL 🐘  
- JPA / Hibernate 🔄  
- Maven 📦  

---

## ⚙️ Configuración

### application.properties

Ubicado en: `src/main/resources/application.properties`

```properties
spring.application.name=ejercicio

spring.datasource.url=jdbc:postgresql://localhost:5432/Prueba
spring.datasource.username=postgres
spring.datasource.password=1234

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true

Método | Endpoint | Descripción
GET | /api/v1/clientes/buscar?nombre=Alvear | Buscar clientes por nombre
POST | /api/v1/clientes | Crear cliente con dirección principal
PUT | /api/v1/clientes/{id} | Editar cliente por ID
DELETE | /api/v1/clientes/{id} | Eliminar cliente por ID
GET | /api/v1/clientes/{clienteId}/direcciones | Listar direcciones adicionales del cliente

Método | Endpoint | Descripción
POST | /api/v1/direcciones/{numeroIdentificacion} | Registrar nueva dirección al cliente


📥 Ejemplos de Peticiones y Respuestas
Crear Cliente
POST /api/v1/clientes

json
Copiar
Editar
{
  "tipoIdentificacion": "CEDULA",
  "numeroIdentificacion": "1805232371",
  "nombres": "cesar Alvear",
  "correo": "kevin.alvear@email.com",
  "celular": "1991234567",
  "direcciones": [
    {
      "provincia": "Tungurahua",
      "ciudad": "Ambato",
      "direccion": "Av. Guaytambos miniarica",
      "matriz": true
    }
  ]
}
Buscar Cliente por Nombre
GET /api/v1/clientes/buscar?nombre=Alvear

json
Copiar
Editar
[
  {
    "id": 1,
    "tipoIdentificacion": "CEDULA",
    "numeroIdentificacion": "1805232371",
    "nombres": "cesar Alvear",
    "correo": "kevin.alvear@email.com",
    "celular": "1991234567",
    "provincia": "Tungurahua",
    "ciudad": "Ambato",
    "direccion": "Av. Guaytambos miniarica"
  }
]
Agregar Dirección Adicional
POST /api/v1/direcciones/1805232371

json
Copiar
Editar
{
  "provincia": "Pichincha1",
  "ciudad": "Quitoaaaaaa223",
  "direccion": "Av. Amazonas a y Naciones Unidas",
  "matriz": false
}
Listar Direcciones del Cliente
GET /api/v1/clientes/1/direcciones

json
Copiar
Editar
[
  {
    "id": 1,
    "provincia": "Tungurahua",
    "ciudad": "Ambato",
    "direccion": "Av. Guaytambos miniarica",
    "matriz": true
  },
  {
    "id": 2,
    "provincia": "Pichincha1",
    "ciudad": "Quitoaaaaaa223",
    "direccion": "Av. Amazonas a y Naciones Unidas",
    "matriz": false
  }
]




microServicios
• Funcionalidad para buscar y obtener un listado de clientes.
API: http://localhost:8080/api/v1/clientes/buscar?nombre=Alvear
respuesta esperada
[
    {
        "id": 1,
        "tipoIdentificacion": "CEDULA",
        "numeroIdentificacion": "1805232371",
        "nombres": "cesar Alvear",
        "correo": "kevin.alvear@email.com",
        "celular": "1991234567",
        "provincia": "Tungurahua",
        "ciudad": "Ambato",
        "direccion": "Av.  Guaytambos miniarica"
    }
]
• Funcionalidad para crear un nuevo cliente con la dirección matriz
API : http://localhost:8080/api/v1/clientes
se crea un booleano en matriz para saber cual es la direccion principal
{
  "tipoIdentificacion": "CEDULA",
  "numeroIdentificacion": "1805232371",
  "nombres": "cesar Alvear",
  "correo": "kevin.alvear@email.com",
  "celular": "1991234567",
  "direcciones": [{  
    "provincia": "Tungurahua",
    "ciudad": "Ambato",
    "direccion": "Av.  Guaytambos miniarica",
    "matriz": true
  }
  ]
}
• Funcionalidad para editar los datos del cliente
  @PutMapping("/{id}")
API: http://localhost:8080/api/v1/clientes/1
• Funcionalidad para eliminar un cliente
    @DeleteMapping("/{id}")
API: http://localhost:8080/api/v1/clientes/1

• Funcionalidad para registrar una nueva dirección por cliente
    @PostMapping("{numeroIdentificacion}")
API: http://localhost:8080/api/v1/direcciones/1805232371
ejemplo 
{
  "provincia": "Pichincha1",
  "ciudad": "Quitoaaaaaa223",
  "direccion": "Av. Amazonas a y Naciones Unidas",
  "matriz": false
}


• Funcionalidad para listar las direcciones adicionales del cliente
    @GetMapping("/{clienteId}/direcciones")
API : http://localhost:8080/api/v1/clientes/1/direcciones
Respuesta esperada
[
    {
        "id": 1,
        "provincia": "Tungurahua",
        "ciudad": "Ambato",
        "direccion": "Av.  Guaytambos miniarica",
        "matriz": true
    },
    {
        "id": 2,
        "provincia": "Pichincha1",
        "ciudad": "Quitoaaaaaa223",
        "direccion": "Av. Amazonas a y Naciones Unidas",
        "matriz": false
    }
]
