-- ----------------------------------------
-- INSERCION DE DATOS 
-- ----------------------------------------

-- Tabla personas
INSERT INTO gestionCursos.personas (dni, nombre, apellido1, apellido2, telefono, email, usuario, contra, perfil)
	VALUES
		('33387392V', 'Juan',   'Lopez',   'Perez',     '123456789', 'juan@gmail.com',   'jualopper', (SELECT MD5('juan') FROM DUAL),   'a'),
		('97939374S', 'Ana',    'Gomez',   'Martinez',  '987654321', 'ana@hotmail.com',  'angomart',  (SELECT MD5('ana') FROM DUAL),    'g'),
		('32097880T', 'Ana',    'Romero',   'Raposo',  '987654321', 'anita@gmail.com',  'anapiquitos',  (SELECT MD5('piquitos') FROM DUAL),    'a'),
		('29157459Z', 'Carlos', 'Sanchez', 'Rodriguez', '555555555', 'carlos@yahoo.com', 'carsanrod', (SELECT MD5('carlos') FROM DUAL), 'a');

-- ----------------------------------------
-- Curso Gestion de archivos en Linux 16.04 LTS
-- ----------------------------------------

INSERT INTO gestionCursos.cursos (id, nombre, ruta_pdf)
	VALUES
		(NEXT VALUE FOR idCursos, 'Gestion de archivos en Linux 16.04 LTS', 'gestion_archivos_ubuntu');
		
-- Pregunta 1
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(1, (LAST_INSERT_ID()), '¿Cuál comando se utiliza para mostrar el contenido de un directorio?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 1, (LAST_INSERT_ID()), 'ls', 1),
		(2, 1, (LAST_INSERT_ID()), 'cp', 0),
		(3, 1, (LAST_INSERT_ID()), 'cd', 0);

-- Pregunta 2
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(2, (LAST_INSERT_ID()), '¿Qué ruta se corresponde con una ruta absoluta?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 2, (LAST_INSERT_ID()), './documentos/file.txt', 0),
		(2, 2, (LAST_INSERT_ID()), '/home/usuario/documentos/file.txt', 1),
		(3, 2, (LAST_INSERT_ID()), 'ninguna es correcta', 0);
		
-- Pregunta 3
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(3, (LAST_INSERT_ID()), '¿Qué comando se usa para crear un directorio?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 3, (LAST_INSERT_ID()), 'mkdir', 1),
		(2, 3, (LAST_INSERT_ID()), 'touch', 0),
		(3, 3, (LAST_INSERT_ID()), 'cat', 0);
		
-- Pregunta 4
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(4, (LAST_INSERT_ID()), '¿Cuál comando se utiliza para eliminar un archivo?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 4, (LAST_INSERT_ID()), 'rm', 1),
		(2, 4, (LAST_INSERT_ID()), 'rd', 0),
		(3, 4, (LAST_INSERT_ID()), 'del', 0);

-- Pregunta 5
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(5, (LAST_INSERT_ID()), '¿Qué comando se utiliza para buscar archivos en un directorio?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 5, (LAST_INSERT_ID()), 'locate', 0),
		(2, 5, (LAST_INSERT_ID()), 'find', 1),
		(3, 5, (LAST_INSERT_ID()), 'search', 0);

-- Pregunta 6
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(6, (LAST_INSERT_ID()), '¿Qué comando se usa ir al directorio raiz?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 6, (LAST_INSERT_ID()), 'cd c:', 0),
		(2, 6, (LAST_INSERT_ID()), '/', 0),
		(3, 6, (LAST_INSERT_ID()), 'cd /', 1);

-- Pregunta 7
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(7, (LAST_INSERT_ID()), '¿Qué comando se utiliza para cambiar el directorio actual?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 7, (LAST_INSERT_ID()), 'change', 0),
		(2, 7, (LAST_INSERT_ID()), 'cd', 1),
		(3, 7, (LAST_INSERT_ID()), 'switchdir', 0);

-- Pregunta 8
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(8, (LAST_INSERT_ID()), '¿Cuál comando se utiliza para crear una copia de seguridad?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 8, (LAST_INSERT_ID()), 'duplicity', 1),
		(2, 8, (LAST_INSERT_ID()), 'BackUp', 0),
		(3, 8, (LAST_INSERT_ID()), 'save', 0);

-- Pregunta 9
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(9, (LAST_INSERT_ID()), '¿Qué comando se usa para mostrar el contenido de archivos?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 9, (LAST_INSERT_ID()), 'cat', 0),
		(2, 9, (LAST_INSERT_ID()), 'more', 0),
		(3, 9, (LAST_INSERT_ID()), 'Ambas son correctas', 1);

-- Pregunta 10
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(10, (LAST_INSERT_ID()), '¿Cuál comando se utiliza para copiar archivos y directorios?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 10, (LAST_INSERT_ID()), 'cop', 0),
		(2, 10, (LAST_INSERT_ID()), 'copyDir', 0),
		(3, 10, (LAST_INSERT_ID()), 'cp', 1);
		
-- ----------------------------------------
-- Curso Bases de datos: Data Manipulation Languaje
-- ----------------------------------------
INSERT INTO gestionCursos.cursos (id, nombre, ruta_pdf)
	VALUES
		(NEXT VALUE FOR idCursos, 'Bases de datos: Data Manipulation Languaje', 'dml');

-- Pregunta 1
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(1, (SELECT LAST_INSERT_ID()), '¿Cuál es la instrucción SQL utilizada para insertar datos en una tabla?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 1, (SELECT LAST_INSERT_ID()), 'ADD', 0),
		(2, 1, (SELECT LAST_INSERT_ID()), 'INSERT', 1),
		(3, 1, (SELECT LAST_INSERT_ID()), 'UPDATE', 0);

-- Pregunta 2
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(2, (SELECT LAST_INSERT_ID()), '¿Qué instrucción SQL se utiliza para eliminar filas de una tabla?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 2, (SELECT LAST_INSERT_ID()), 'DELETE', 1),
		(2, 2, (SELECT LAST_INSERT_ID()), 'REMOVE', 0),
		(3, 2, (SELECT LAST_INSERT_ID()), 'ERASE', 0);
		
-- Pregunta 3
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(3, (SELECT LAST_INSERT_ID()), '¿Qué cláusula SQL se utiliza para actualizar datos en una tabla?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 3, (SELECT LAST_INSERT_ID()), 'MODIFY', 0),
		(2, 3, (SELECT LAST_INSERT_ID()), 'CHANGE', 0),
		(3, 3, (SELECT LAST_INSERT_ID()), 'UPDATE', 1);
		
-- Pregunta 4
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(4, (SELECT LAST_INSERT_ID()), '¿Cuál es la instrucción SQL para recuperar datos de una tabla?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 4, (SELECT LAST_INSERT_ID()), 'GET', 0),
		(2, 4, (SELECT LAST_INSERT_ID()), 'SELECT', 1),
		(3, 4, (SELECT LAST_INSERT_ID()), 'RETRIEVE', 0);
		
-- Pregunta 5
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(5, (SELECT LAST_INSERT_ID()), '¿Qué cláusula SQL se usa para filtrar resultados en una consulta?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 5, (SELECT LAST_INSERT_ID()), 'FILTER', 0),
		(2, 5, (SELECT LAST_INSERT_ID()), 'WHERE', 1),
		(3, 5, (SELECT LAST_INSERT_ID()), 'RESTRICT', 0);
		
-- Pregunta 6
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(6, (SELECT LAST_INSERT_ID()), '¿Qué instrucción SQL se utiliza para combinar datos de dos tablas en una sola tabla?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 6, (SELECT LAST_INSERT_ID()), 'JOIN', 0),
		(2, 6, (SELECT LAST_INSERT_ID()), ',', 0),
		(3, 6, (SELECT LAST_INSERT_ID()), 'Ambas son correctas', 1);

-- Pregunta 7
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(7, (SELECT LAST_INSERT_ID()), '¿Qué instrucción SQL se utiliza para agregar una nueva columna a una tabla existente?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 7, (SELECT LAST_INSERT_ID()), 'APPEND COLUMN', 0),
		(2, 7, (SELECT LAST_INSERT_ID()), 'ADD COLUMN', 1),
		(3, 7, (SELECT LAST_INSERT_ID()), 'INSERT COLUMN', 0);

-- Pregunta 8
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(8, (SELECT LAST_INSERT_ID()), '¿Qué instrucción SQL se utiliza para eliminar una columna de una tabla?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 8, (SELECT LAST_INSERT_ID()), 'DROP COLUMN', 1),
		(2, 8, (SELECT LAST_INSERT_ID()), 'REMOVE COLUMN', 0),
		(3, 8, (SELECT LAST_INSERT_ID()), 'DELETE COLUMN', 0);

-- Pregunta 9
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(9, (SELECT LAST_INSERT_ID()), '¿Qué instrucción SQL se utiliza para modificar datos existentes en una tabla?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 9, (SELECT LAST_INSERT_ID()), 'MODIFY', 0),
		(2, 9, (SELECT LAST_INSERT_ID()), 'CHANGE', 0),
		(3, 9, (SELECT LAST_INSERT_ID()), 'UPDATE', 1);

-- Pregunta 10
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(10, (SELECT LAST_INSERT_ID()), '¿Qué instrucción SQL se utiliza para ordenar los resultados de una consulta?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 10, (SELECT LAST_INSERT_ID()), 'ARRANGE BY', 0),
		(2, 10, (SELECT LAST_INSERT_ID()), 'SORT BY', 0),
		(3, 10, (SELECT LAST_INSERT_ID()), 'ORDER BY', 1);

-- ----------------------------------------
-- Bases de datos: Data Defining Languaje
-- ----------------------------------------
INSERT INTO gestionCursos.cursos (id, nombre, ruta_pdf)
	VALUES
		(NEXT VALUE FOR idCursos, 'Bases de datos: Data Defining Languaje', 'ddl');
		
-- Pregunta 1
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(1, (SELECT LAST_INSERT_ID()), '¿Qué comando SQL se utiliza para crear una tabla?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 1, (SELECT LAST_INSERT_ID()), 'CREATE TABLE', 1),
		(2, 1, (SELECT LAST_INSERT_ID()), 'ADD TABLE', 0),
		(3, 1, (SELECT LAST_INSERT_ID()), 'INSERT TABLE', 0);

-- Pregunta 2
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(2, (SELECT LAST_INSERT_ID()), '¿Cuál comando se utiliza para eliminar una tabla en SQL?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 2, (SELECT LAST_INSERT_ID()), 'REMOVE TABLE', 0),
		(2, 2, (SELECT LAST_INSERT_ID()), 'DELETE TABLE', 0),
		(3, 2, (SELECT LAST_INSERT_ID()), 'DROP TABLE', 1);
		
-- Pregunta 3
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(3, (SELECT LAST_INSERT_ID()), '¿Qué comando se utiliza para modificar una tabla existente?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta)  
	VALUES
		(1, 3, (SELECT LAST_INSERT_ID()), 'ALTER TABLE', 1),
		(2, 3, (SELECT LAST_INSERT_ID()), 'UPDATE TABLE', 0),
		(3, 3, (SELECT LAST_INSERT_ID()), 'MODIFY TABLE', 0);
		
-- Pregunta 4
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(4, (SELECT LAST_INSERT_ID()), '¿Cuál comando se usa para agregar una nueva columna a una tabla existente?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 4, (SELECT LAST_INSERT_ID()), 'INSERT COLUMN', 0),
		(2, 4, (SELECT LAST_INSERT_ID()), 'ADD COLUMN', 0),
		(3, 4, (SELECT LAST_INSERT_ID()), 'ALTER TABLE', 1);
		
-- Pregunta 5
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(5, (SELECT LAST_INSERT_ID()), '¿Qué comando se usa para eliminar una columna de una tabla?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 5, (SELECT LAST_INSERT_ID()), 'ALTER TABLE', 0),
		(2, 5, (SELECT LAST_INSERT_ID()), 'DROP COLUMN', 1),
		(3, 5, (SELECT LAST_INSERT_ID()), 'REMOVE COLUMN', 0);

-- Pregunta 6
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(6, (SELECT LAST_INSERT_ID()), '¿Qué comando SQL se utiliza para añadir una restricción de clave primaria a una tabla?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 6, (SELECT LAST_INSERT_ID()), 'ADD PRIMARY', 0),
		(2, 6, (SELECT LAST_INSERT_ID()), 'PRIMARY KEY', 1),
		(3, 6, (SELECT LAST_INSERT_ID()), 'INSERT KEY', 0);

-- Pregunta 7
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(7, (SELECT LAST_INSERT_ID()), '¿Qué comando SQL se utiliza para eliminar una restricción de clave primaria de una tabla?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 7, (SELECT LAST_INSERT_ID()), 'REMOVE KEY', 0),
		(2, 7, (SELECT LAST_INSERT_ID()), 'DELETE PRIMARY', 0),
		(3, 7, (SELECT LAST_INSERT_ID()), 'DROP PRIMARY KEY', 1);

-- Pregunta 8
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(8, (SELECT LAST_INSERT_ID()), '¿Qué comando SQL se utiliza para agregar un índice a una tabla?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 8, (SELECT LAST_INSERT_ID()), 'ADD INDEX', 0),
		(2, 8, (SELECT LAST_INSERT_ID()), 'CREATE INDEX', 1),
		(3, 8, (SELECT LAST_INSERT_ID()), 'INSERT INDEX', 0);

-- Pregunta 9
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(9, (SELECT LAST_INSERT_ID()), '¿Qué comando SQL se utiliza para eliminar un índice de una tabla?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 9, (SELECT LAST_INSERT_ID()), 'REMOVE INDEX', 0),
		(2, 9, (SELECT LAST_INSERT_ID()), 'DELETE INDEX', 0),
		(3, 9, (SELECT LAST_INSERT_ID()), 'DROP INDEX', 1);

-- Pregunta 10
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(10, (SELECT LAST_INSERT_ID()), '¿Qué comando SQL se utiliza para renombrar una tabla existente?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 10, (SELECT LAST_INSERT_ID()), 'RENAME TABLE', 1),
		(2, 10, (SELECT LAST_INSERT_ID()), 'ALTER TABLE', 0),
		(3, 10, (SELECT LAST_INSERT_ID()), 'CHANGE TABLE', 0);
		
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