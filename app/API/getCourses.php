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
    if (isset($_GET['id_curso'])) {// Está definido el id del curso a obtener
        
    }else { // NO está definida la id del curso
        $SELECT = 'SELECT c.id AS `id_curso`, c.nombre AS `nombre_curso`, c.ruta_pdf AS `ruta_pdf_curso`, p.id AS `id_preguntas`, p.enunciado AS `enunciado_preguntas`, r.id AS `id_respuesta`, r.opcion AS `opcion_respuesta`, r.esCorrecta AS `esCorrecta_respuesta`
                        FROM `gestionCursos.cursos` AS c
                            INNER JOIN `gestionCursos.preguntas` AS p ON `c.id` = `p.id_curso`
                            INNER JOIN `gestionCursos.respuestasTest` AS r ON `p.id` = `r.id_pregunta`;';

        $consulta = $conexion->prepare($SELECT);
        $consulta->execute();
        if ($consulta->rowCount() > 0) {
            $resultado = [];

            while () { // Finish
                # code...
            }
        }
    }
}

die();
?>