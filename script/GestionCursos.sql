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

-- ----------------------------------------
-- CREACION DEL USUARIO
-- ----------------------------------------

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

-- ----------------------------------------
-- CREACION DEL SCHEMA
-- ----------------------------------------

-- Creamos el schema para la gestion de la Base de Datos
CREATE SCHEMA IF NOT EXISTS gestionCursos DEFAULT CHARACTER SET utf8;
USE gestionCursos;

-- ----------------------------------------
-- BORRADO DE TABLAS
-- ----------------------------------------

DROP TABLE IF EXISTS gestionCursos.personas;
DROP TABLE IF EXISTS gestionCursos.respuestasTest;
DROP TABLE IF EXISTS gestionCursos.preguntas;
DROP TABLE IF EXISTS gestionCursos.cursos;

-- ----------------------------------------
-- CREACION DE TABLAS
-- ----------------------------------------

-- Tabla Personas
CREATE TABLE IF NOT EXISTS gestionCursos.personas(
	dni				VARCHAR(9) PRIMARY KEY,
	nombre			VARCHAR(50),
	apellido1		VARCHAR(50),
	apellido2 		VARCHAR(50),
	telefono		VARCHAR(9),
	email			VARCHAR(50),
	usuario			VARCHAR(15),
	contra			VARCHAR(255),
	perfil			CHAR(1),
	UNIQUE (telefono, email, usuario)
);

-- Tabla Cursos
-- PDF con la teoria a subir y su test correspondiente
CREATE TABLE IF NOT EXISTS gestionCursos.cursos(
	id 				INT(2) PRIMARY KEY,
	nombre			VARCHAR(50), 
	ruta_pdf		VARCHAR(255)
);

-- Tabla test
-- Tabla para los enunciados de los test 
CREATE TABLE IF NOT EXISTS gestionCursos.preguntas(
	id 				INT(3) PRIMARY KEY,
	Enunciado		VARCHAR(255),
	id_curso 		INT(2), -- Referencia al id del curso del que se va a evaluar al alumno
	FOREIGN KEY (id_curso) REFERENCES gestionCursos.cursos(id) ON DELETE CASCADE
);


-- Tabla respuestas
-- Tabla para guardar las opciones de los test y cual de ellas es verdadera

CREATE TABLE IF NOT EXISTS gestionCursos.respuestasTest(
	id				INT(5) AUTO INCREMENT,
	id_pregunta		INT(3), -- Pregunta a la que hace referencia
	opcion			VARCHAR(255), -- Enunciado de la opcion
	esCorrecta		INT(1),
	PRIMARY KEY (id, id_pregunta),
	FOREIGN KEY (id_pregunta) REFERENCES gestionCursos.preguntas(id) ON DELETE CASCADE
);

-- ----------------------------------------
-- BORRADO DE SECUENCIAS
-- ----------------------------------------

DROP SEQUENCE IF EXISTS gestionCursos.idCurso;
DROP SEQUENCE IF EXISTS gestionCursos.idPreguntas;
DROP SEQUENCE IF EXISTS gestionCursos.idOpcion;

-- ----------------------------------------
-- CREACION DE SECUENCIAS
-- ----------------------------------------

-- Secuencia para la asignacion de los id de los cursos
CREATE SEQUENCE IF NOT EXISTS gestionCursos.idCurso
	START WITH 1
	INCREMENT BY 1
	MAXVALUE 99
	MINVALUE 1
	NOCYCLE;
	
-- Secuencia para la asignacion de los id de las preguntas
CREATE SEQUENCE IF NOT EXISTS gestionCursos.idPreguntas
	START WITH 1
	INCREMENT BY 1
	MAXVALUE 999
	MINVALUE 1
	NOCYCLE;

-- Secuencia para la asignacion de los id de las opciones de las preguntas
CREATE SEQUENCE IF NOT EXISTS gestionCursos.idOpcion
	START WITH 1
	INCREMENT BY 1
	MAXVALUE 99999
	MINVALUE 1
	NOCYCLE;	
	
-- ----------------------------------------
-- INSERCION DE DATOS 
-- ----------------------------------------

-- Tabla personas
INSERT INTO gestionCursos.personas (dni, nombre, apellido1, apellido2, telefono, email, usuario, contra, perfil)
	VALUES
		('33387392V', 'Juan',   'Lopez',   'Perez',     '123456789', 'juan@gmail.com',   'jualopper', (SELECT MD5('juan') FROM DUAL),   'a'),
		('97939374S', 'Ana',    'Gomez',   'Martinez',  '987654321', 'ana@hotmail.com',  'angomart',  (SELECT MD5('ana') FROM DUAL),    'g'),
		('29157459Z', 'Carlos', 'Sanchez', 'Rodriguez', '555555555', 'carlos@yahoo.com', 'carsanrod', (SELECT MD5('carlos') FROM DUAL), 'a');

-- Tabla cursos
INSERT INTO gestionCursos.cursos (id, nombre, ruta_pdf)
	VALUES
		(NEXT VALUE FOR idCurso, 'Gestion de archivos en Linux 16.04 LTS', '..\\app\\GestorDeCursos\\app\\src\\main\\res\\src\\cursos\\gestion_archivos_ubuntu.pdf'),
		(NEXT VALUE FOR idCurso, 'Bases de datos: Data Manipulation Languaje', '..\\app\\GestorDeCursos\\app\\src\\main\\res\\src\\cursos\\dml.pdf'),
		(NEXT VALUE FOR idCurso, 'Bases de datos: Data Defining Languaje', '..\\app\\GestorDeCursos\\app\\src\\main\\res\\src\\cursos\\ddl.pdf');
		
