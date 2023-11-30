<?php
/**
 * 
 * Archivo para añadir un nuevo test a un curso nuevo
 * 
 */
require_once("./utilities/conexion.php");
require_once("./utilities/funciones.php");
$conexion = conectarPDO($database);
$errores = [];
$datos = '';

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    // Transformamos el JSON de entrada de datos a un array asociativo 
    $datos = json_decode(file_get_contents('php://input'), true);
    $id_curso = intval($datos['id_curso']);
    $id_pregunta = intval($datos['id_pregunta']);
    // consultas para la insercion de tests y sus opciones
    $INSERT_PREGUNTAS = 'INSERT INTO gestionCursos.preguntas (id, id_curso, enunciado)
                            VALUES
                                (:id, :curso, :enun);';
    $INSERT_OPCIONES =  'INSERT INTO gestionCursos.respuestastest (id, id_pregunta, id_curso, opcion, esCorrecta)
                            VALUES
                                (:id, :id_preg, :curso, :op, :cor);';

    try {
        // preparamos la consulta
        $consulta = $conexion->prepare($INSERT_PREGUNTAS);
        // parámetros de la consulta
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $id_pregunta);
        $consulta->bindParam(':enun', $datos['enunciado_pregunta']);

        //Ejecutamos la consulta
        $consulta->execute();

        // Primera opcion
        $consulta = $conexion->prepare($INSERT_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op1']);
        $consulta->bindParam(':id_preg', $id_pregunta);
        $consulta->bindParam(':op', $datos['enun_op1']);
        $consulta->bindParam(':cor', $datos['escor_op1']);
        $consulta->execute();

        // Segunda opcion
        $consulta = $conexion->prepare($INSERT_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op2']);
        $consulta->bindParam(':id_preg', $id_pregunta);
        $consulta->bindParam(':op', $datos['enun_op2']);
        $consulta->bindParam(':cor', $datos['escor_op2']);
        $consulta->execute();

        // Tercera opcion
        $consulta = $conexion->prepare($INSERT_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op3']);
        $consulta->bindParam(':id_preg', $id_pregunta);
        $consulta->bindParam(':op', $datos['enun_op3']);
        $consulta->bindParam(':cor', $datos['escor_op3']);
        $consulta->execute();

        // Cuarta opcion
        $consulta = $conexion->prepare($INSERT_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op4']);
        $consulta->bindParam(':id_preg', $id_pregunta);
        $consulta->bindParam(':op', $datos['enun_op4']);
        $consulta->bindParam(':cor', $datos['escor_op4']);
        $consulta->execute();

        // creamos el json de salida para comprobar que todo fue correcto
        $jsonDatos = json_encode($datos);
        header($headerJSON);
        header($codigosHTTP['201']);
    } catch (PDOException $exception) {
        // Capturamos el error producido
        $errorDescription = $exception->getMessage();
        $datos = array('error' => $errorDescription);
        // Creamos JSON con datos recibidos 
        $jsonDatos = json_encode($datos);
        // Cabeceras de error en la ejecución de la consulta
        header($headerJSON);
        header($codigosHTTP['500']);
    }
    echo $jsonDatos;

    // Cerramos la consulta y la conexion
    $consulta = null;
    $conexion = null;
}
die();
?>