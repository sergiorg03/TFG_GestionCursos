<?php
/**
 * 
 * Archivo para la obtencion de los DNI de los usuarios registrados como alumnos.
 * 
 */
require_once("./utilities/conexion.php");
require_once("./utilities/funciones.php");
$conexion = conectarPDO($database);
$errores = [];
$datos = '';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

	// Creamos las consultas para la obtencion de los tests y sus opciones
    $SELECT = 'SELECT p.dni
                    FROM personas AS p
                    WHERE p.perfil = "a";';
    
    // Preparamos la consulta para obtener los DNI de los alumnos
    $consulta = $conexion->prepare($SELECT);

    // Ejecutamos la consulta
    $consulta->execute();

    if ($consulta->rowCount() > 0) {
        $dnis = [];
        while ($dni = $consulta->fetch(PDO::FETCH_ASSOC)) {
            $dnis[] = array($dni);
            
        }

        header($headerJSON);
        header($codigosHTTP['200']);
        $jsonDatos = json_encode($dnis); // Añadimos los dni de los alumnos
    }else{ // Se produjo un error 
        header($headerJSON);
        header($codigosHTTP['404']);
        $errores[] = 'No se pudieron obtener datos de los alumnos. ';
        $jsonDatos = json_encode($errores);
    }

    echo $jsonDatos;// Devolvemos los datos recaudados

    // Cerramos la conexion y las consultas
    $consulta = null;
    $conexion = null;
}

die();
?>