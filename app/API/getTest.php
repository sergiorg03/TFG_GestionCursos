<?php
/**
 * 
 * Archivo para la obtencion del test de un curso
 * 
 */
require_once("./utilities/conexion.php");
require_once("./utilities/funciones.php");
$conexion = conectarPDO($database);
$errores = [];
$datos = '';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    //Obtenemos el id del curso y las lineas a saltar
    $id_curso = $_GET['idCurso'];
    $lineasSalto = $_GET['lineasSalto'];

	// Creamos las consultas para la obtencion de los tests y sus opciones
    $SELECT_PREGUNTAS = 'SELECT p.id AS id_pregunta, p.enunciado AS enunciado_preguntas
                            FROM preguntas AS p
                            WHERE p.id_curso = :idCurso
                            LIMIT 1 OFFSET '.$lineasSalto.';';
    $SELECT_OPCIONES = 'SELECT r.id AS id_respuesta, r.opcion AS opcion_respuesta, r.esCorrecta AS esCorrecta_respuesta
                            FROM respuestasTest AS r
                            WHERE r.id_pregunta = :idPreg
                            AND r.id_curso = :id_curso;';
    
    // Preparamos la consulta para las preguntas
    $consulta_preg = $conexion->prepare($SELECT_PREGUNTAS);
    
    // Asignamos el parametro a la consulta
    $consulta_preg->bindParam(':idCurso', $id_curso);

    // Ejecutamos la consulta
    $consulta_preg->execute();

    if ($consulta_preg->rowCount() > 0) {
        $preguntas = [];
        while ($pregunta = $consulta_preg->fetch(PDO::FETCH_ASSOC)) {
            $preguntas[] = array($pregunta);
            
            // Preparamos la consulta para obtener las opciones de la pregunta
            $consulta_op = $conexion->prepare($SELECT_OPCIONES);

            // Asignamos el parametro a la consulta
            $consulta_op->bindParam(':idPreg', $pregunta['id_pregunta']);
            $consulta_op->bindParam(':id_curso', $id_curso);

            //Ejecutamos la consulta
            $consulta_op->execute();
            $opciones = [];
            while ($opcion = $consulta_op->fetch(PDO::FETCH_ASSOC)) {
                $opciones[] = $opcion;
            }
            $preguntas[] = $opciones;
        
        }

        header($headerJSON);
        header($codigosHTTP['200']);
        $jsonDatos = json_encode($preguntas); // Añadimos las preguntas con sus opciones
    }else{ // Se produjo un error y no hay test
        header($headerJSON);
        header($codigosHTTP['404']);
        $errores[] = 'No se pudo obtener datos del curso';
        $jsonDatos = json_encode($errores);
    }

    echo $jsonDatos;// Devolvemos los datos recaudados

    // Cerramos la conexion y las consultas
    $consulta_op = null;
    $consulta_preg = null;
    $conexion = null;
}

die();
?>