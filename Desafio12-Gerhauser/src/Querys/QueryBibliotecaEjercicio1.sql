CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

-- ===============================================
-- TABLA: libros
-- ===============================================
CREATE TABLE libros (
    id_libro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(50) NOT NULL,
    autor VARCHAR(50) NOT NULL,
    anio_publicacion INT,
    isbn INT NOT NULL UNIQUE,
    disponible BOOLEAN
);