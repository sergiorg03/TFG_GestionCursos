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
		(1, 'Gestion de archivos en Linux 16.04 LTS', '..\\app\\GestorDeCursos\\app\\src\\main\\res\\src\\cursos\\gestion_archivos_ubuntu.pdf'),
		(2, 'Bases de datos: Data Manipulation Languaje', '..\\app\\GestorDeCursos\\app\\src\\main\\res\\src\\cursos\\dml.pdf'),
		(3, 'Bases de datos: Data Defining Languaje', '..\\app\\GestorDeCursos\\app\\src\\main\\res\\src\\cursos\\ddl.pdf');

-- ----------------------------------------
-- Curso Gestion de archivos en Linux 16.04 LTS
-- ----------------------------------------

-- Pregunta 1
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(1, 1, '¿Cuál comando se utiliza para mostrar el contenido de un directorio en Linux?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 1, 1, 'ls', 1),
		(2, 1, 1, 'cp', 0),
		(3, 1, 1, 'cd', 0);

-- Pregunta 2
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(2, 1, '¿Qué comando se usa para cambiar los permisos de un archivo en Linux?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 2, 1, 'chmod', 1),
		(2, 2, 1, 'chown', 0),
		(3, 2, 1, 'permisos', 0);
		
-- Pregunta 3
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(3, 1, '¿Qué comando se usa para crear un directorio en Linux?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 3, 1, 'mkdir', 1),
		(2, 3, 1, 'touch', 0),
		(3, 3, 1, 'cat', 0);
		
-- Pregunta 4
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(4, 1, '¿Cuál comando se utiliza para eliminar un archivo en Linux?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 4, 1, 'rm', 1),
		(2, 4, 1, 'rd', 0),
		(3, 4, 1, 'del', 0);
		
-- ----------------------------------------
-- Curso Bases de datos: Data Manipulation Languaje
-- ----------------------------------------

-- Pregunta 1
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(1, 2, '¿Cuál es la instrucción SQL utilizada para insertar datos en una tabla?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 1, 2, 'ADD', 0),
		(2, 1, 2, 'INSERT', 1),
		(3, 1, 2, 'UPDATE', 0);

-- Pregunta 2
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(2, 2, '¿Qué instrucción SQL se utiliza para eliminar filas de una tabla?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 2, 2, 'DELETE', 1),
		(2, 2, 2, 'REMOVE', 0),
		(3, 2, 2, 'ERASE', 0);
		
-- Pregunta 3
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(3, 2, '¿Qué cláusula SQL se utiliza para actualizar datos en una tabla?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 3, 2, 'MODIFY', 0),
		(2, 3, 2, 'CHANGE', 0),
		(3, 3, 2, 'UPDATE', 1);
		
-- Pregunta 4
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(4, 2, '¿Cuál es la instrucción SQL para recuperar datos de una tabla?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 4, 2, 'GET', 0),
		(2, 4, 2, 'SELECT', 1),
		(3, 4, 2, 'RETRIEVE', 0);
		
-- Pregunta 5
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(5, 2, '¿Qué cláusula SQL se usa para filtrar resultados en una consulta SELECT?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 5, 2, 'FILTER', 0),
		(2, 5, 2, 'WHERE', 1),
		(3, 5, 2, 'RESTRICT', 0);

-- ----------------------------------------
-- Bases de datos: Data Defining Languaje
-- ----------------------------------------

-- Curso Introducción al Lenguaje de Definición de Datos (DDL)

-- Pregunta 1
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(1, 3, '¿Qué comando SQL se utiliza para crear una tabla?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 1, 3, 'CREATE TABLE', 1),
		(2, 1, 3, 'ADD TABLE', 0),
		(3, 1, 3, 'INSERT TABLE', 0);

-- Pregunta 2
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(2, 3, '¿Cuál comando se utiliza para eliminar una tabla en SQL?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 2, 3, 'DROP TABLE', 1),
		(2, 2, 3, 'DELETE TABLE', 0),
		(3, 2, 3, 'REMOVE TABLE', 0);
		
-- Pregunta 3
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(3, 3, '¿Qué comando se utiliza para modificar una tabla existente?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta)  
	VALUES
		(1, 3, 3, 'ALTER TABLE', 1),
		(2, 3, 3, 'UPDATE TABLE', 0),
		(3, 3, 3, 'MODIFY TABLE', 0);
		
-- Pregunta 4
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(4, 3, '¿Cuál comando se usa para agregar una nueva columna a una tabla existente?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 4, 3, 'ALTER TABLE', 1),
		(2, 4, 3, 'ADD COLUMN', 0),
		(3, 4, 3, 'INSERT COLUMN', 0);
		
-- Pregunta 5
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(5, 3, '¿Qué comando se usa para eliminar una columna de una tabla?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 5, 3, 'ALTER TABLE', 0),
		(2, 5, 3, 'DROP COLUMN', 1),
		(3, 5, 3, 'REMOVE COLUMN', 0);

-- Tabla realizar
INSERT INTO gestionCursos.realizar (dni, id_curso, puntuacion)
	VALUES
		('33387392V', 3, 5),
		('29157459Z', 3, 10),
		('33387392V', 1, 3);
		
-- Tabla gestionar
INSERT INTO gestionCursos.gestionar (dni, id_curso)
	VALUES
		('97939374S', 3),
		('97939374S', 2),
		('97939374S', 1);