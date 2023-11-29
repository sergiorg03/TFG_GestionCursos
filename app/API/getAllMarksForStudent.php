<?php
/**
 * Archivo creado para la obtencion de todas las notas de los alumnos
 */
require_once("./utilities/conexion.php");
require_once("./utilities/funciones.php");
$conexion = conectarPDO($database);
$errores = [];
$datos = '';

if($_SERVER['REQUEST_METHOD'] == 'GET'){
    
    // Creamos la consulta para obtener los datos
    $SELECT = 'SELECT lastModifiedDate, puntuacion 
                    FROM realizar
                    WHERE dni = :dni
                    AND id_curso = :idCurso
                    ORDER BY puntuacion DESC
                    LIMIT 10;';

    $dni = $_GET['dni'];
    $curso = $_GET['id_curso'];

    // Preparamos la consulta
    $consulta = $conexion->prepare();

    // Asignamos los parametros a la consulta
    $consulta->bindParam(':dni', $dni);
    $consulta->bindParam(':idCurso', $curso);

    // Ejecutamos la consulta
    $consulta->execute();

    // Comprobamos que hemos obtenido datos
    if($consulta->rowCount() > 0){
        // Hemos obtenido datos
        $notas = [];

        while ($nota = $consulta->fetch(PDO::FETCH_ASSOC)) {
            $notas[] = $nota;
        }

        header($headerJSON);
        header($codigosHTTP['200']);
        $jsonDatos = json_encode($notas); // Añadimos los datos obtenidos en la consulta
    }else{ // No se obtuvieron datos en la consulta
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