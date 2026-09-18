-- Script de la base de datos usuarios_db
-- La app lo crea sola con Hibernate (ddl-auto=update), esto es solo
-- para tener el SQL a mano / documentacion del lab.

CREATE TABLE usuarios (
    id       BIGSERIAL PRIMARY KEY,
    nombre   VARCHAR(100) NOT NULL,
    email    VARCHAR(150) NOT NULL UNIQUE,
    rol      VARCHAR(20)  NOT NULL,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- usuario admin para poder loguearse la primera vez
INSERT INTO usuarios (nombre, email, rol, username, password)
VALUES ('Administrador', 'admin@utec.edu.pe', 'admin', 'admin', 'admin123')
ON CONFLICT (username) DO NOTHING;
