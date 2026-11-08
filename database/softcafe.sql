CREATE DATABASE IF NOT EXISTS softcafe
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE softcafe;

CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(30) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    CONSTRAINT chk_productos_precio CHECK (precio >= 0),
    CONSTRAINT chk_productos_stock CHECK (stock >= 0)
);

INSERT INTO productos (codigo, nombre, precio, stock) VALUES
('CAF-001', 'Capuchino', 8000.00, 30),
('CAF-002', 'Latte Vainilla', 9500.00, 25),
('REP-001', 'Brownie artesanal', 6000.00, 20),
('CAF-003', 'Café americano', 7000.00, 40)
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre);
