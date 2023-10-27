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
    if (isset($_GET['id_curso'])) { // Está definido el id del curso a obtener

    } else { // NO está definida la id del curso
        // Consulta de prueba
        /*$SELECT = 'SELECT c.id AS `id_curso`, c.nombre AS `nombre_curso`, c.ruta_pdf AS `ruta_pdf_curso`, p.id AS `id_preguntas`, p.enunciado AS `enunciado_preguntas`, r.id AS `id_respuesta`, r.opcion AS `opcion_respuesta`, r.esCorrecta AS `esCorrecta_respuesta`
                        FROM `gestionCursos.cursos` AS c
                            INNER JOIN `gestionCursos.preguntas` AS p ON `c.id` = `p.id_curso`
                            INNER JOIN `gestionCursos.respuestasTest` AS r ON `p.id` = `r.id_pregunta`;';*/

        // Consulta para obtener los datos
        // Primera consulta para obtener los datos de los cursos
        $SELECT = 'SELECT c.id AS id_curso, c.nombre AS nombre_curso, c.ruta_pdf AS ruta_pdf_curso
                        FROM cursos AS c;';
        
        // Segunda consulta para obtener los datos de las preguntas de los tests
        $SELECT2 = 'SELECT p.id AS id_pregunta, p.enunciado AS enunciado_preguntas
                        FROM preguntas AS p
                        WHERE p.id_curso = :idCurso;';

        // Tercera consulta para obtener las opciones de cada pregunta
        $SELECT3 = 'SELECT r.id AS id_respuesta, r.opcion AS opcion_respuesta, r.esCorrecta AS esCorrecta_respuesta
                        FROM respuestasTest AS r
                        WHERE r.id_pregunta = :idPreg;';

        $consulta = $conexion->prepare($SELECT);
        $consulta->execute();
        if ($consulta->rowCount() > 0) {
            $resultado = [];

            while ($datosCursos = $consulta->fetch(PDO::FETCH_ASSOC)) { // Finish
                $resultado[] = $datosCursos;
                
                /*
                $consulta2 = $conexion->prepare($SELECT2);
                $consulta2->bindParam(':id_pregunta', $resultado['id_pregunta']);
                $consulta2->execute();
                if ($consulta2->rowCount() > 0) {
                    $opcinesTests = [];
                    while ($enunciado_opcion = $consulta->fetch(PDO::FETCH_ASSOC)) {
                        $opcinesTests[] = $enunciado_opcion;
                    }
                    $resultado['opcines_test'] = $opcinesTests;
                }*/
            }print_r($resultado);
        }
    }
}

die();
?>