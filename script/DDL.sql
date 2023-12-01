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
/* GRANT ALL PRIVILEGES 
	ON `tfg\_sergio\_%`.* 
	TO 'tfg_sergio'@'localhost';*/

-- ----------------------------------------
-- CREACION DEL SCHEMA
-- ----------------------------------------

-- Creamos el schema para la gestion de la Base de Datos
CREATE SCHEMA IF NOT EXISTS gestionCursos DEFAULT CHARACTER SET utf8;
USE gestionCursos;

-- ----------------------------------------
-- BORRADO DE TABLAS
-- ----------------------------------------

DROP TABLE IF EXISTS gestionCursos.realizar;
DROP TABLE IF EXISTS gestionCursos.gestionar;
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
	perfil			CHAR(1) NOT NULL,
	UNIQUE (telefono, email, usuario)
);

-- Tabla Cursos
-- PDF con la teoria a subir y su test correspondiente
CREATE TABLE IF NOT EXISTS gestionCursos.cursos(
	id 				INT(2) PRIMARY KEY AUTO_INCREMENT,
	nombre			VARCHAR(50), 
	ruta_pdf		VARCHAR(255)
);

-- Tabla test
-- Tabla para los enunciados de los test 
CREATE TABLE IF NOT EXISTS gestionCursos.preguntas(
	id 				INT(3),
	id_curso 		INT(2), -- Referencia al id del curso del que se va a evaluar al alumno
	Enunciado		VARCHAR(255),
	PRIMARY KEY (id, id_curso),
	FOREIGN KEY (id_curso) REFERENCES gestionCursos.cursos(id) ON DELETE CASCADE
);
-- Entidad debil ya que si no existe el curso no existe el test a realizar


-- Tabla respuestas
-- Tabla para guardar las opciones de los test y cual de ellas es verdadera
CREATE TABLE IF NOT EXISTS gestionCursos.respuestasTest(
	id				INT(5),
	id_pregunta		INT(3), -- Pregunta a la que hace referencia
	id_curso		INT(2), -- curso al que hace referencia
	opcion			VARCHAR(255), -- Enunciado de la opcion
	esCorrecta		INT(1),
	PRIMARY KEY (id, id_pregunta, id_curso),
	FOREIGN KEY (id_pregunta, id_curso) REFERENCES gestionCursos.preguntas(id, id_curso) ON DELETE CASCADE
);
-- Entidad debil, ya que si no existe una pregunta de test, no existen sus respuestas

-- Tabla realizar
CREATE TABLE IF NOT EXISTS gestionCursos.realizar(
	dni					VARCHAR(9),
	id_curso			INT(2),
	lastModifiedDate	DATETIME,
	puntuacion			INT(2),
	PRIMARY KEY (dni, id_curso, lastModifiedDate),
	FOREIGN KEY (dni) REFERENCES gestionCursos.personas (dni) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (id_curso) REFERENCES gestionCursos.cursos (id) ON DELETE CASCADE
);

-- Tabla gestion
CREATE TABLE IF NOT EXISTS gestionCursos.gestionar(
	dni				VARCHAR(9),
	id_curso		INT(2),
	last_updateDate DATETIME,
	PRIMARY KEY (dni, id_curso, last_updateDate),
	FOREIGN KEY (dni) REFERENCES gestionCursos.personas (dni) ON DELETE CASCADE,
	FOREIGN KEY (id_curso) REFERENCES gestionCursos.cursos (id) ON DELETE CASCADE
);