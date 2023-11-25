-- ----------------------------------------
-- INSERCION DE DATOS 
-- ----------------------------------------

-- Tabla personas
INSERT INTO gestionCursos.personas (dni, nombre, apellido1, apellido2, telefono, email, usuario, contra, perfil)
	VALUES
		('33387392V', 'Juan',   'Lopez',   'Perez',     '123456789', 'juan@gmail.com',   'jualopper', (SELECT MD5('juan') FROM DUAL),   'a'),
		('97939374S', 'Ana',    'Gomez',   'Martinez',  '987654321', 'ana@hotmail.com',  'angomart',  (SELECT MD5('ana') FROM DUAL),    'g'),
		('32097880T', 'Ana',    'Romero',   'Raposo',  '989634321', 'anita@gmail.com',  'anitarom',  (SELECT MD5('ana') FROM DUAL),    'g'),
		('29157459Z', 'Carlos', 'Sanchez', 'Rodriguez', '555555555', 'carlos@yahoo.com', 'carsanrod', (SELECT MD5('carlos') FROM DUAL), 'a');

-- Tabla cursos
INSERT INTO gestionCursos.cursos (id, nombre, ruta_pdf)
	VALUES
		(1, 'Gestion de archivos en Linux 16.04 LTS', 'gestion_archivos_ubuntu'),
		(2, 'Bases de datos: Data Manipulation Languaje', 'dml'),
		(3, 'Bases de datos: Data Defining Languaje', 'ddl');
		
-- Tabla realizar
INSERT INTO gestionCursos.realizar (dni, id_curso, puntuacion)
	VALUES
		('33387392V', 3, 5),
		('29157459Z', 3, 10),
		('33387392V', 1, 3);
		
-- Tabla gestionar
INSERT INTO gestionCursos.gestionar (dni, id_curso, last_updateDate)
	VALUES
		('97939374S', 3, SYSDATE()),
		('32097880T', 2, SYSDATE()),
		('97939374S', 1, SYSDATE());

-- ----------------------------------------
-- Curso Gestion de archivos en Linux 16.04 LTS
-- ----------------------------------------

-- Pregunta 1
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(1, 1, '¿Cuál comando se utiliza para mostrar el contenido de un directorio?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 1, 1, 'ls', 1),
		(2, 1, 1, 'cp', 0),
		(3, 1, 1, 'cd', 0);

-- Pregunta 2
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(2, 1, '¿Qué ruta se corresponde con una ruta absoluta?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 2, 1, './documentos/file.txt', 0),
		(2, 2, 1, '/home/usuario/documentos/file.txt', 1),
		(3, 2, 1, 'ninguna es correcta', 0);
		
-- Pregunta 3
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(3, 1, '¿Qué comando se usa para crear un directorio?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 3, 1, 'mkdir', 1),
		(2, 3, 1, 'touch', 0),
		(3, 3, 1, 'cat', 0);
		
-- Pregunta 4
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(4, 1, '¿Cuál comando se utiliza para eliminar un archivo?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 4, 1, 'rm', 1),
		(2, 4, 1, 'rd', 0),
		(3, 4, 1, 'del', 0);

-- Pregunta 5
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(5, 1, '¿Qué comando se utiliza para buscar archivos en un directorio?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 5, 1, 'locate', 0),
		(2, 5, 1, 'find', 1),
		(3, 5, 1, 'search', 0);

-- Pregunta 6
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(6, 1, '¿Qué comando se usa ir al directorio raiz?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 6, 1, 'cd c:', 0),
		(2, 6, 1, '/', 0),
		(3, 6, 1, 'cd /', 1);

-- Pregunta 7
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(7, 1, '¿Qué comando se utiliza para cambiar el directorio actual?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 7, 1, 'change', 0),
		(2, 7, 1, 'cd', 1),
		(3, 7, 1, 'switchdir', 0);

-- Pregunta 8
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(8, 1, '¿Cuál comando se utiliza para crear una copia de seguridad?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 8, 1, 'duplicity', 1),
		(2, 8, 1, 'BackUp', 0),
		(3, 8, 1, 'save', 0);

-- Pregunta 9
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(9, 1, '¿Qué comando se usa para mostrar el contenido de archivos?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 9, 1, 'cat', 0),
		(2, 9, 1, 'more', 0),
		(3, 9, 1, 'Ambas son correctas', 1);

-- Pregunta 10
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(10, 1, '¿Cuál comando se utiliza para copiar archivos y directorios?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 10, 1, 'cop', 0),
		(2, 10, 1, 'copyDir', 0),
		(3, 10, 1, 'cp', 1);
		
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
		(5, 2, '¿Qué cláusula SQL se usa para filtrar resultados en una consulta?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 5, 2, 'FILTER', 0),
		(2, 5, 2, 'WHERE', 1),
		(3, 5, 2, 'RESTRICT', 0);
		
-- Pregunta 6
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(6, 2, '¿Qué instrucción SQL se utiliza para combinar datos de dos tablas en una sola tabla?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 6, 2, 'JOIN', 0),
		(2, 6, 2, ',', 0),
		(3, 6, 2, 'Ambas son correctas', 1);

-- Pregunta 7
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(7, 2, '¿Qué instrucción SQL se utiliza para agregar una nueva columna a una tabla existente?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 7, 2, 'APPEND COLUMN', 0),
		(2, 7, 2, 'ADD COLUMN', 1),
		(3, 7, 2, 'INSERT COLUMN', 0);

-- Pregunta 8
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(8, 2, '¿Qué instrucción SQL se utiliza para eliminar una columna de una tabla?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 8, 2, 'DROP COLUMN', 1),
		(2, 8, 2, 'REMOVE COLUMN', 0),
		(3, 8, 2, 'DELETE COLUMN', 0);

-- Pregunta 9
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(9, 2, '¿Qué instrucción SQL se utiliza para modificar datos existentes en una tabla?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 9, 2, 'MODIFY', 0),
		(2, 9, 2, 'CHANGE', 0),
		(3, 9, 2, 'UPDATE', 1);

-- Pregunta 10
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(10, 2, '¿Qué instrucción SQL se utiliza para ordenar los resultados de una consulta?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 10, 2, 'ARRANGE BY', 0),
		(2, 10, 2, 'SORT BY', 0),
		(3, 10, 2, 'ORDER BY', 1);

-- ----------------------------------------
-- Bases de datos: Data Defining Languaje
-- ----------------------------------------

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
		(1, 2, 3, 'REMOVE TABLE', 0),
		(2, 2, 3, 'DELETE TABLE', 0),
		(3, 2, 3, 'DROP TABLE', 1);
		
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
		(1, 4, 3, 'INSERT COLUMN', 0),
		(2, 4, 3, 'ADD COLUMN', 0),
		(3, 4, 3, 'ALTER TABLE', 1);
		
-- Pregunta 5
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado)
	VALUES
		(5, 3, '¿Qué comando se usa para eliminar una columna de una tabla?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 5, 3, 'ALTER TABLE', 0),
		(2, 5, 3, 'DROP COLUMN', 1),
		(3, 5, 3, 'REMOVE COLUMN', 0);

-- Pregunta 6
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(6, 3, '¿Qué comando SQL se utiliza para añadir una restricción de clave primaria a una tabla?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 6, 3, 'ADD PRIMARY', 0),
		(2, 6, 3, 'PRIMARY KEY', 1),
		(3, 6, 3, 'INSERT KEY', 0);

-- Pregunta 7
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(7, 3, '¿Qué comando SQL se utiliza para eliminar una restricción de clave primaria de una tabla?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 7, 3, 'REMOVE KEY', 0),
		(2, 7, 3, 'DELETE PRIMARY', 0),
		(3, 7, 3, 'DROP PRIMARY KEY', 1);

-- Pregunta 8
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(8, 3, '¿Qué comando SQL se utiliza para agregar un índice a una tabla?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 8, 3, 'ADD INDEX', 0),
		(2, 8, 3, 'CREATE INDEX', 1),
		(3, 8, 3, 'INSERT INDEX', 0);

-- Pregunta 9
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(9, 3, '¿Qué comando SQL se utiliza para eliminar un índice de una tabla?');
		
INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 9, 3, 'REMOVE INDEX', 0),
		(2, 9, 3, 'DELETE INDEX', 0),
		(3, 9, 3, 'DROP INDEX', 1);

-- Pregunta 10
INSERT INTO gestionCursos.preguntas(id, id_curso, Enunciado) 
	VALUES
		(10, 3, '¿Qué comando SQL se utiliza para renombrar una tabla existente?');

INSERT INTO gestionCursos.respuestasTest(id, id_pregunta, id_curso, opcion, esCorrecta) 
	VALUES
		(1, 10, 3, 'RENAME TABLE', 1),
		(2, 10, 3, 'ALTER TABLE', 0),
		(3, 10, 3, 'CHANGE TABLE', 0);