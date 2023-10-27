<?php
/**
 * 
 * Archivo para la obtencion de todos los cursos junto a sus test
 * 
 */
require_once("./utilities/conexion.php");
require_once("./utilities/funciones.php");
$conexion = conectarPDO($database);
$errores = [];
$datos = '';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    // Segunda consulta para obtener los datos de las preguntas de los tests
    $SELECT2 = 'SELECT p.id AS id_pregunta, p.enunciado AS enunciado_preguntas, p.id_curso
        FROM preguntas AS p
        WHERE p.id_curso = :idCurso;';

    // Tercera consulta para obtener las opciones de cada pregunta
    $SELECT3 = 'SELECT r.id AS id_respuesta, r.opcion AS opcion_respuesta, r.esCorrecta AS esCorrecta_respuesta
        FROM respuestasTest AS r
        WHERE r.id_pregunta = :idPreg;';

    if (isset($_GET['id_curso'])) { // Está definido el id del curso a obtener
        
        // consulta para obtener el curso
        $SELECT = 'SELECT c.id AS id_curso, c.nombre AS nombre_curso, c.ruta_pdf AS ruta_pdf_curso
                        FROM cursos AS c
                        WHERE c.id = :id_curso;';

        $consulta = $conexion->prepare($SELECT);
        $consulta->bindParam(':id_curso', $_GET['id_curso']);
        $consulta->execute();

        if ($consulta->rowCount() > 0) {
            $cursoConTestyOpciones = [];

            while ($datosCursos = $consulta->fetch(PDO::FETCH_ASSOC)) {
                $cursoConTestyOpciones[] = $datosCursos;

                // Ejecutamos la consulta para obtener las preguntas de los test del curso correspondiente
                $consulta2 = $conexion->prepare($SELECT2);
                $consulta2->bindParam(':idCurso', $datosCursos['id_curso']);
                $consulta2->execute();
                
                $preguntasTest = []; // Array para obtener los enunciados de las preguntas

                while ($enunciado_opcion = $consulta2->fetch(PDO::FETCH_ASSOC)) {

                    $preguntasTest[] = $enunciado_opcion; // Array para obtener los enunciados de las preguntas
                    $consulta3 = $conexion->prepare($SELECT3);
                    $consulta3->bindParam(':idPreg', $enunciado_opcion['id_pregunta']);
                    $consulta3->execute();

                        
                    $opcionesTestPregunta = []; // Array para obtener los valores de las opciones del test
                    while ($opcion = $consulta3->fetch(PDO::FETCH_ASSOC)) {
                        $opcionesTestPregunta[] = $opcion;
                    }
                    $preguntasTest[] = $opcionesTestPregunta;   
                }
                $cursoConTestyOpciones[] = $preguntasTest;
            }
            header($headerJSON);
            header($codigosHTTP['200']);
            $jsonDatos = json_encode($cursoConTestyOpciones);

        }else {
            header($headerJSON);
            header($codigosHTTP['404']);
            $jsonDatos = json_encode(array('No exite el curso indicado. '));
        }
        
    } else { // NO está definida la id del curso

        // Consultas para obtener los datos
        // Primera consulta para obtener los datos de los cursos
        $SELECT = 'SELECT c.id AS id_curso, c.nombre AS nombre_curso, c.ruta_pdf AS ruta_pdf_curso
                        FROM cursos AS c;';

        // preparamos la primera consulta
        $consulta = $conexion->prepare($SELECT);
        
        // Ejecutamos la consulta
        $consulta->execute();

        if ($consulta->rowCount() > 0) {
            // Creamos un array en el que guardaremos los cursos, las preguntas y sus opciones
            $CursosConPreguntasyRespuestas = [];

            while ($datosCursos = $consulta->fetch(PDO::FETCH_ASSOC)) { 
                $CursosConPreguntasyRespuestas[] = $datosCursos; // Obtenemos los datos de los cursos
                
                //print_r($CursosConPreguntasyRespuestas);
                //echo $datosCursos['id_curso'];
                
                // Ejecutamos la consulta para obtener las preguntas de los test del curso correspondiente
                $consulta2 = $conexion->prepare($SELECT2);
                $consulta2->bindParam(':idCurso', $datosCursos['id_curso']);
                $consulta2->execute();
                
                $preguntasTest = []; // Array para obtener los enunciados de las preguntas

                while ($enunciado_opcion = $consulta2->fetch(PDO::FETCH_ASSOC)) {

                    $preguntasTest[] = $enunciado_opcion; // Array para obtener los enunciados de las preguntas
                    $consulta3 = $conexion->prepare($SELECT3);
                    $consulta3->bindParam(':idPreg', $enunciado_opcion['id_pregunta']);
                    $consulta3->execute();

                        
                    $opcionesTestPregunta = []; // Array para obtener los valores de las opciones del test
                    while ($opcion = $consulta3->fetch(PDO::FETCH_ASSOC)) {
                        $opcionesTestPregunta[] = $opcion;
                    }
                    $preguntasTest[] = $opcionesTestPregunta;
                        
                }
                $CursosConPreguntasyRespuestas[] = $preguntasTest;
                
            }// echo "<br/><br/><br/><br/>";print_r($CursosConPreguntasyRespuestas);
            header($headerJSON);
            header($codigosHTTP['200']);
            $jsonDatos = json_encode($CursosConPreguntasyRespuestas);
        }
    }
    echo $jsonDatos;

    $consulta3 = null;
    $consulta2 = null;
    $consulta = null;
    $conexion = null;
}
die();
?>