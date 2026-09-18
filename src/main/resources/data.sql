INSERT INTO usuarios (nombre, email, rol, username, password)
VALUES ('Administrador', 'admin@utec.edu.pe', 'admin', 'admin', 'admin123')
ON CONFLICT (username) DO NOTHING;
