<?php
/**
 * 
 * Archivo para obtener el total de preguntas test de 1 curso
 * 
 */
require_once("./utilities/conexion.php");
require_once("./utilities/funciones.php");
$conexion = conectarPDO($database);
$errores = [];
$datos = '';

if($_SERVER['REQUEST_METHOD'] == 'GET'){
    // Comprobamos que el id del curso esta definido
    if(isset($_GET['id_curso'])){

        $SELECT = 'SELECT COUNT(*) AS numeroPreguntas
                        FROM preguntas
                        WHERE id_curso = :id;';

        // Preparamos la consulta
        $consulta = $conexion->prepare($SELECT);

        // Asignamos los parametros a la consulta
        $consulta->bindParam(':id', $_GET['id_curso']);

        // Ejecutamos la consulta
        $consulta->execute();

        $numeroPreguntas = [];

        while ($numero = $consulta->fetch(PDO::FETCH_ASSOC)) {
            $numeroPreguntas[] = $numero;
        }

        header($headerJSON);
        header($codigosHTTP['200']);
        $jsonDatos = json_encode($numeroPreguntas);
    }else{
        header($headerJSON);
        header($codigosHTTP['404']);
        $errores[] = 'Debe introducir un curso del que obtener el numero de preguntas. ';
        $jsonDatos = json_encode($errores);
    }

    // Cerramos la consulta y la conexion
    $consulta = null;
    $conexion = null;

    // Devolvemos el JSON con los datos
    echo $jsonDatos;
}

die();
?>