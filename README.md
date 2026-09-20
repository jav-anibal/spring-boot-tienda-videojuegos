# Tienda de Videojuegos — Spring Boot REST API

API REST desarrollada con Spring Boot para gestionar clientes, videojuegos y ventas, con persistencia MySQL y reglas de negocio transaccionales.

## Stack

- Java 21
- Spring Boot 3.5.10
- Spring Web
- Spring Data JPA / Hibernate
- MySQL
- Maven
- Swagger / OpenAPI

## Arquitectura

```text
HTTP / JSON
    |
Controllers
    |
Services
    |
Repositories
    |
JPA / Hibernate
    |
MySQL
```

El proyecto separa controladores REST, lógica de negocio, repositorios y entidades persistentes.

## Regla de negocio principal

La operación de compra se ejecuta dentro de una transacción:

```text
Cliente + Videojuego
        |
validar stock y saldo
        |
actualizar saldo
        |
actualizar stock
        |
registrar venta
```

El servicio utiliza `@Transactional`, de modo que si una operación falla durante la compra, la transacción puede revertirse.

## Funcionalidades

- CRUD de videojuegos;
- gestión de clientes;
- búsqueda por género;
- búsqueda por precio;
- compra de videojuegos;
- validación de stock;
- validación de saldo;
- historial de ventas por cliente;
- listado de ventas.

## Endpoints principales

```text
/api/videojuegos
/api/clientes
/api/tienda/comprar
/api/tienda/ventas
/api/tienda/ventas/cliente/{clienteId}
```

## Swagger / OpenAPI

Con la aplicación en ejecución:

```text
http://localhost:8080/swagger-ui.html
```

## Ejecución

Requisitos:

- Java 21
- MySQL
- Maven o Maven Wrapper

La configuración de desarrollo utiliza una base MySQL local llamada:

```text
tienda_videojuegos
```

Ejecutar:

```bash
./mvnw spring-boot:run
```

En Windows:

```powershell
mvnw.cmd spring-boot:run
```

## Qué demuestra este proyecto

- diseño de endpoints REST;
- arquitectura por capas;
- persistencia con Spring Data JPA;
- reglas de negocio transaccionales;
- modelado relacional;
- integración con MySQL;
- documentación de API con OpenAPI.

---

**Anibal Solano**  
Backend Developer · Java · Spring Boot · PostgreSQL
