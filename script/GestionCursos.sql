-- Script Base de Datos P.I.

-- Creamos el schema para la gestion de la Base de Datos
CREATE SCHEMA IF NOT EXISTS gestionCursos DEFAULT CHARACTER SET utf8;
USE gestionCursos;

-- Tabla Personas
DROP TABLE IF EXISTS gestionCursos.personas;

CREATE TABLE IF NOT EXISTS gestionCursos.personas(
	dni				VARCHAR(9) PRIMARY KEY,
	nombre			VARCHAR(50),
	apellido1		VARCHAR(50),
	apellido2 		VARCHAR(50),
	telefono		VARCHAR(9),
	email			VARCHAR(50),
	usuario			VARCHAR(15),
	contra			ENUM('md5'),
	perfil			CHAR(1),
	UNIQUE (telefono, email, usuario),
	CHECK (REGEXP_LIKE(dni, '^[0-9]{8}[a-zA-Z]$')), -- Comprueba que el DNI tiene el formato correcto
	CHECK (REGEXP_LIKE(email,'^.+@.+\..+$')),		-- Comprueba que el email tiene el formato correcto %@%.%
	CHECK (REGEXP_LIKE(telefono, '^[0-9]{9}$')),	-- Comprueba que el telefono tiene el formato correcto, es decir, 9 números
	CHECK (UPPER(perfil) IN UPPER('a', 'g'))  		-- Comprueba que el perfil es alumno o gestor
);