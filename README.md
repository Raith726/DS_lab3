# Lab 3 - Login y CRUD de Usuarios

Aplicacion web hecha en Spring Boot para el Lab 3 del curso de Diseno de
Software. Tiene una pantalla de login que valida contra la base de datos
y, una vez adentro, un CRUD completo de usuarios (crear, listar, editar
y eliminar).

## Tecnologias usadas

- Java 17
- Spring Boot 4.1.1 (Web, Data JPA, Thymeleaf)
- PostgreSQL
- Docker / Docker Compose

## Estructura de carpetas

```
src/main/java/pe/edu/utec/usuarios/
  controller/   -> LoginController, UsuarioController
  service/      -> UsuarioService y su implementacion
  repository/   -> UsuarioRepository (JPA)
  model/        -> entidad Usuario

src/main/resources/
  templates/    -> vistas Thymeleaf (login, admin, usuarios/*)
  application.properties
  data.sql      -> inserta el usuario admin al levantar la app

db/
  schema.sql    -> script de la tabla usuarios (referencia)

Dockerfile
docker-compose.yml
```

## Como levantarlo (con Docker, recomendado)

Necesitas tener Docker Desktop abierto. Parado en esta carpeta:

```
docker compose up --build -d
```

Esto levanta dos contenedores: la base de datos Postgres y la app.
Cuando termine de arrancar, entra a http://localhost:9000

Para bajarlo:

```
docker compose down
```

(los datos de la base no se pierden, quedan guardados en un volumen de
Docker)

## Usuario para el primer login

La app inserta un usuario admin automaticamente la primera vez que
levanta (esta en `data.sql`), para poder entrar sin tener que crear
nada a mano:

- usuario: `admin`
- contrasena: `admin123`

Una vez adentro, desde el panel se pueden crear mas usuarios.

## Como levantarlo sin Docker (opcional)

Si prefieres correrlo directo con Maven, necesitas un Postgres corriendo
en el puerto 5433 con una base llamada `usuarios_db` (usuario/clave
`postgres`/`postgres`, o cambia esos datos en
`application.properties`). Despues:

```
./mvnw spring-boot:run
```
