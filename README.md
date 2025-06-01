```sh
# Microservices Project with Spring Boot

Este proyecto es una arquitectura de microservicios implementada con Spring Boot, Spring Cloud y Eureka. Todo corre en local y está diseñado para ser modular, escalable y mantenible.

## 🧩 Arquitectura

- `config-server`: Servidor central de configuración.
- `eureka-server`: Descubrimiento de servicios.
- `gateway-service`: Gateway API con balanceo de carga.
- `user-service`: CRUD de usuarios.
- `task-service`: CRUD de tareas, asociadas a usuarios.

## ⚙️ Tecnologías

- Java 17
- Spring Boot 3.x
- Spring Cloud
- Eureka
- Spring Cloud Gateway
- MySQL
- Postman (para pruebas)

## 🚀 Cómo ejecutar

    1. Asegúrate de tener MySQL corriendo y las bases de datos necesarias creadas (ver abajo).
    2. Levanta cada servicio individualmente desde tu IDE (IntelliJ o Spring Tool Suite).
    3. Verifica Eureka en: [http://localhost:8761](http://localhost:8761)
    4. Consume las rutas a través del gateway: `http://localhost:8080/`

## 🗃️ Bases de Datos

### `taskdb` (para task-service)
```sql
CREATE DATABASE taskdb;

CREATE TABLE tasks (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255),
  description TEXT,
  completed Boolean,
  user_id BIGINT
);

```
### `userdb` (para user-service)

```sql
CREATE DATABASE userdb;

CREATE TABLE user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  email TEXT,
);
```

## 📡 Endpoints y Consumo de Servicios

### User Service (`user-service`)

| Método | Endpoint               | Descripción                  | Ejemplo curl                                           |
|--------|------------------------|------------------------------|-------------------------------------------------------|
| GET    | `/api/v1/users`        | Obtener lista de usuarios     | `curl http://localhost:8080/api/v1/users`             |
| GET    | `/api/v1/users/{id}`   | Obtener usuario por ID        | `curl http://localhost:8080/api/v1/users/1`           |
| POST   | `/api/v1/users`        | Crear un nuevo usuario        | `curl -X POST -H "Content-Type: application/json" -d '{"name":"Juan","email":"juan@mail.com"}' http://localhost:8080/api/v1/users` |
| DELETE | `/api/v1/users/{id}`   | Eliminar usuario por ID       | `curl -X DELETE http://localhost:8080/api/v1/users/1` |

### Task Service (`task-service`)

| Método | Endpoint                           | Descripción                            | Ejemplo curl                                           |
|--------|----------------------------------|--------------------------------------|-------------------------------------------------------|
| GET    | `/api/v1/tasks`                   | Obtener lista de tareas               | `curl http://localhost:8080/api/v1/tasks`             |
| GET    | `/api/v1/tasks/{id}`              | Obtener tarea por ID                  | `curl http://localhost:8080/api/v1/tasks/1`           |
| GET    | `/api/v1/tasks/by-user/{userId}` | Obtener tareas asociadas a un usuario| `curl http://localhost:8080/api/v1/tasks/by-user/1`   |
| POST   | `/api/v1/tasks`                   | Crear una nueva tarea                 | `curl -X POST -H "Content-Type: application/json" -d '{"title":"Comprar","description":"Comprar leche","completed":false,"user_id":1}' http://localhost:8080/api/v1/tasks` |

### Endpoints combinados (tareas con usuarios)

Si tienes un endpoint que devuelve tareas junto con info del usuario, puede ser algo así:

| Método | Endpoint                  | Descripción                              | Ejemplo curl                                           |
|--------|---------------------------|----------------------------------------|-------------------------------------------------------|
| GET    | `/api/v1/tasks/with-user` | Listar tareas incluyendo datos del usuario asociado | `curl http://localhost:8080/api/v1/tasks/with-user`  |

> **Nota:** Este último endpoint puede ser implementado en el `task-service` realizando join o llamada interna al `user-service`.


```