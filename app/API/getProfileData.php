<?php
/**
 * 
 * Archivo para mostrar la puntuación en los cursos realizados por un alumno
 * 
 */
require_once("./utilities/conexion.php");
require_once("./utilities/funciones.php");
$conexion = conectarPDO($database);
$errores = [];
$datos = '';


if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    if (isset($_GET['dni'])) {
        $SELECT = 'SELECT dni, nombre, apellido1, apellido2, telefono, email, usuario, contra, perfil
                        FROM personas
                        WHERE UPPER(dni) = UPPER(:dni);';

        // Preparamos la consulta
        $consulta = $conexion->prepare($SELECT);

        // Asignamos los parametros a la consulta
        $consulta->bindParam(':dni', $_GET['dni']);

        // Ejecutamos la consulta
        $consulta->execute();

        if ($consulta->rowCount() > 0) {// La consulta devuelve valores
            $datosPersona = [];

            while ($datos = $consulta->fetch(PDO::FETCH_ASSOC)) {
                $datosPersona[] = $datos;
            }
            header($headerJSON);
            header($codigosHTTP['200']);
            $jsonDatos = json_encode($datosPersona);
        }else {
            header($headerJSON);
            header($codigosHTTP['404']);
            $errores[] = 'no hay datos';
            $jsonDatos = json_encode($errores);
        }
    }else {
        header($headerJSON);
        header($codigosHTTP['404']);
        $errores[] = 'Debe insertar un DNI';
        $jsonDatos = json_encode($errores);
    }

    // Cerramos la consulta y la conexion
    $consulta = null;
    $conexion = null;

    // Devolvemos los datos
    echo $jsonDatos;
}

die();
?>