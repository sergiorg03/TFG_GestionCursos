<?php
/**
 * 
 * Archivo para añadir un nuevo curso
 * 
 */
require_once("./utilities/conexion.php");
require_once("./utilities/funciones.php");
$conexion = conectarPDO($database);
$errores = [];
$datos = '';

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	// Obtenemos los datos a introducir
	$datos = json_decode(file_get_contents('php://input'), true);
	
	// Preparamos la consulta
	$INSERT = 'INSERT INTO gestionCursos.cursos (nombre, ruta_pdf)
                            VALUES
                                (:nombre, :ruta);';
	$consulta = $conexion->prepare($INSERT);
	// Asignamos los parametros a la consulta
	$consulta->bindParam(':nombre', $datos['nombre']);
	$consulta->bindParam(':ruta', $datos['ruta_pdf']);
	try{
		$consulta->execute();
        $id = $conexion->lastInsertId();
        $datos["id_curso"] = $id;
		// creamos el json de salida para comprobar que todo fue correcto
        $jsonDatos = json_encode($datos);
        header($headerJSON);
        header($codigosHTTP['201']);
	} catch (PDOException $exception) {
        // Capturamos el error producido
        $errorDescription = $exception->getMessage();
        $datos = array('error' => $errorDescription);
        // Creamos JSON con el mensaje de error
        $jsonDatos = json_encode($datos);
        // Cabeceras de error en la ejecución de la consulta
        header($headerJSON);
        header($codigosHTTP['500']);
    }
	// Devolvemos el resultado de la consulta
	echo $jsonDatos;

    // Cerramos la consulta y la conexion
    $consulta = null;
    $conexion = null;
}
die();
?>