-- Script Base de Datos P.I.

-- Configuración de los ficheros de XAMPP

-- Fichero Apache (httpd.conf)
/*
# Sergio. 22/09/2023. Asignacion de la carpeta para la realizacion del proyecto integrado.
	Alias /tfg "C:\Users\srgye\OneDrive\Escritorio\Sergio\Instituto\2DAM\TFG_Nomada"
	<Directory "C:\Users\srgye\OneDrive\Escritorio\Sergio\Instituto\2DAM\TFG_Nomada">
		Options Indexes FollowSymLinks Includes ExecCGI
		AllowOverride All
		Require all granted
	</Directory>
*/

-- Fichero phpMyAdmin (config.inc.php)
/*
$cfg['Servers'][$i]['auth_type'] = 'cookie';
$cfg['Servers'][$i]['user'] = '';
$cfg['Servers'][$i]['password'] = '';
*/

------------------------------------------
-- CREACION DEL USUARIO
------------------------------------------

-- Creación del usuario para la gestion del schema
DROP USER IF EXISTS 'tfg_sergio'@'localhost';  -- Si existe el usuario previamente
CREATE USER 'tfg_sergio'@'localhost' 
	IDENTIFIED VIA mysql_native_password 
	USING PASSWORD('tfg_sergio');
GRANT ALL PRIVILEGES 
	ON *.* 
	TO 'tfg_sergio'@'localhost' 
	REQUIRE NONE WITH GRANT OPTION 
		MAX_QUERIES_PER_HOUR 0 
		MAX_CONNECTIONS_PER_HOUR 0 
		MAX_UPDATES_PER_HOUR 0 
		MAX_USER_CONNECTIONS 0;
GRANT ALL PRIVILEGES 
	ON `tfg\_sergio\_%`.* 
	TO 'tfg_sergio'@'localhost';

------------------------------------------
-- CREACION DEL SCHEMA
------------------------------------------

-- Creamos el schema para la gestion de la Base de Datos
CREATE SCHEMA IF NOT EXISTS gestionCursos DEFAULT CHARACTER SET utf8;
USE gestionCursos;

------------------------------------------
-- CREACION DE TABLAS
------------------------------------------
-- Tabla Personas
DROP TABLE IF EXISTS gestionCursos.personas CASCADE;

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
	UNIQUE (telefono, email, usuario)
	-- CHECK (REGEXP_LIKE(dni, '^[0-9]{8}[a-zA-Z]$')), -- Comprueba que el DNI tiene el formato correcto
	-- CHECK (REGEXP_LIKE(email,'^.+@.+\..+$')),		-- Comprueba que el email tiene el formato correcto %@%.%
	-- CHECK (REGEXP_LIKE(telefono, '^[0-9]{9}$')),	-- Comprueba que el telefono tiene el formato correcto, es decir, 9 números
	-- CHECK (UPPER(perfil) IN UPPER('a', 'g'))  		-- Comprueba que el perfil es alumno o gestor
);

-- Tabla Cursos
-- PDF con la teoria a subir 
DROP TABLE IF EXISTS gestionCursos.cursos CASCADE;

CREATE TABLE IF NOT EXISTS gestionCursos.cursos(
	id 				INT(2) PRIMARY KEY,
	nombre			VARCHAR(50), 
	archivo			LONGBLOB,
	id_test			INT(3), -- id del test de dicho curso
	FOREIGN KEY id_test REFERENCES gestionCursos.preguntas
);

-- Tabla test
-- Tabla para los enunciados de los test 
DROP TABLE IF EXISTS gestionCursos.preguntas CASCADE;

CREATE TABLE IF NOT EXISTS gestionCursos.preguntas(
	id 				INT(3) PRIMARY KEY,
	Enunciado		VARCHAR(255)
);


-- Tabla respuestas
-- Tabla para guardar las opciones de los test y cual de ellas es verdadera
DROP TABLE IF EXISTS gestionCursos.respuestasTest CASCADE;

CREATE TABLE IF NOT EXISTS gestionCursos.respuestasTest(
	id				INT(5) PRIMARY KEY,
	id_pregunta		INT(3), -- Pregunta a la que hace referencia
	opcion			VARCHAR(255), -- Enunciado de la opcion
	esCorrecta		INT(1),
	FOREIGN KEY id_pregunta REFERENCES gestionCursos.Preguntas(id)
);
------------------------------------------
-- CREACION DE SECUENCIAS
------------------------------------------

-- Secuencia para la asignacion de los id de los cursos
CREATE SEQUENCE gestionCursos.idCurso
	START WITH 1
	INCREMENT BY 1
	MAXVALUE 99
	MINVALUE 1
	NOCYCLE;
	
-- Secuencia para la asignacion de los id de las preguntas
CREATE SEQUENCE gestionCursos.idPreguntas
	START WITH 1
	INCREMENT BY 1
	MAXVALUE 999
	MINVALUE 1
	NOCYCLE;

-- Secuencia para la asignacion de los id de las opciones de las preguntas
CREATE SEQUENCE gestionCursos.idOpcion
	START WITH 1
	INCREMENT BY 1
	MAXVALUE 99999
	MINVALUE 1
	NOCYCLE;	
	
------------------------------------------
-- INSERCION DE DATOS 
------------------------------------------

-- Tabla personas
INSERT INTO gestionCursos.personas (dni, nombre, apellido1, apellido2, telefono, email, usuario, contra, perfil)
	VALUES
		(),
		(),
		();
		
-- Tabla cursos
INSERT INTO gestionCursos.cursos (id, nombre, archivo)
	VALUES
		(NEXT VALUE FOR idCurso, ),
		(NEXT VALUE FOR idCurso, ),
		(NEXT VALUE FOR idCurso, );
		
