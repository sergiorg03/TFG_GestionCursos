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

if ($_SERVER['REQUEST_METHOD'] == 'DELETE' /*|| $_SERVER['REQUEST_METHOD'] == 'POST'*/) {

    $datos = json_decode(file_get_contents('php://input'), true);
    //Obtenemos el dni del perfil a borrar 
    $dni = $datos['dni'];

    //Creamos la consulta para borrar el dato
    $DELETE = 'DELETE FROM personas
                    WHERE dni = :dni';

    //Preparamos la consulta
    $consulta = $conexion->prepare($DELETE);

    //Asignamos el parametro a la consulta
    $consulta->bindParam(':dni', $dni);

    try {
        //Ejecutamos la consulta
        $consulta->execute();

        //Creacion del JSON con los datos
        $jsonDatos = json_encode($datos);

        // Comprobamos si hay un error en la ejecución de la consulta
        if ($consulta->rowCount() > 0) {
            //Escribimos la cabecera de peticion correcta
            header($headerJSON);
            header($codigosHTTP['200']);
        } else { // No existe el id introducido
            header($headerJSON);
            header($codigosHTTP['404']);
        }
    } catch (PDOException $exception) {
    //Capturamos el error
    $errorDescription = $exception->getMessage();
    $datos = array('error' => $errorDescription);
    //Creacion del JSON con los datos
    $jsonDatos = json_encode($datos);
    header($headerJSON);
    header($codigosHTTP['500']);
    }

    // Mostramos el Json
    echo $jsonDatos;

    // Cerramos la consulta, la conexion y salimos del programa
    $consulta = null;
    $conexion = null;    
}

die();
?>