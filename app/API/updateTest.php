<?php
/**
 * 
 * Archivo para modificar el test de un curso
 * 
 */
require_once("./utilities/conexion.php");
require_once("./utilities/funciones.php");
$conexion = conectarPDO($database);
$errores = [];
$datos = '';

if ($_SERVER['REQUEST_METHOD'] == 'PUT') {
    // Se transforma el json de entrada
    $datos = json_decode(file_get_contents('php://input'), true);
	// Obtenemos el id del curso
    $id_curso = $datos['id_curso'];
    $id_pregunta = $datos['id_pregunta'];

	// Creamos las sentencias de modificación de datos de las preguntas y las opciones
    $UPDATE_PREGUNTAS = 'UPDATE preguntas
                            SET Enunciado = :enun
                          WHERE id = :id
                            AND id_curso = :curso;';
    
    $UPDATE_OPCIONES =  'UPDATE respuestastest
                            SET opcion = :op,
                                esCorrecta = :cor
                          WHERE id = :id
                            AND id_curso = :curso
                            AND id_pregunta = :id_preg;';
    
    // Primera pregunta
    $consulta = $conexion->prepare($UPDATE_PREGUNTAS);
    $consulta->bindParam(':curso', $id_curso);
    $consulta->bindParam(':id', $id_pregunta);
    $consulta->bindParam(':enun', $datos['enunciado_pregunta']);
    try {
        //Ejecutamos la consulta
        $consulta->execute();
        
        // Primera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op1']);
        $consulta->bindParam(':id_preg', $id_pregunta);
        $consulta->bindParam(':op', $datos['enun_op1']);
        $consulta->bindParam(':cor', $datos['escor_op1']);
        $consulta->execute();

        // Segunda opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op2']);
        $consulta->bindParam(':id_preg', $id_pregunta);
        $consulta->bindParam(':op', $datos['enun_op2']);
        $consulta->bindParam(':cor', $datos['escor_op2']);
        $consulta->execute();

        // Tercera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op3']);
        $consulta->bindParam(':id_preg', $id_pregunta);
        $consulta->bindParam(':op', $datos['enun_op3']);
        $consulta->bindParam(':cor', $datos['escor_op3']);
        $consulta->execute();

        // Cuarta opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op4']);
        $consulta->bindParam(':id_preg', $id_pregunta);
        $consulta->bindParam(':op', $datos['enun_op4']);
        $consulta->bindParam(':cor', $datos['escor_op4']);
        $consulta->execute();

        //Creacion del JSON con los datos
        $jsonDatos = json_encode($datos);
        
        header($headerJSON);
        header($codigosHTTP['200']);
    } catch (PDOException $exception) { // Se produce error
// Capturamos el error producido
        $errorDescription = $exception->getMessage();
        $datos = array('error' => $errorDescription);
        // Creamos JSON con datos recibidos y el nuevo id
        $jsonDatos = json_encode($datos);
        // Cabeceras de error en la ejecución de la consulta
        header($headerJSON);
        header($codigosHTTP['500']);
    }
    // Devolvemos JSON
    echo $jsonDatos;

    //Cerramos la consulta, la conexion y salimos del programa
    $consulta = null;
    $conexion = null;
}

die();
?>