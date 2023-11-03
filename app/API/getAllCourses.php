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
    
    $SELECT = 'SELECT id, nombre, ruta_pdf
                    FROM cursos;';

    $consulta = $conexion->prepare($SELECT);

    $consulta->execute();

    if ($consulta->rowCount() > 0) {
        $cursos = [];
        while ($curso = $consulta->fetch(PDO::FETCH_ASSOC)) {
            $cursos[] = $curso;
        }
        header($headerJSON);
        header($codigosHTTP['200']);
        $jsonDatos = json_encode($cursos);
    }

    //Cerramos la consulta y la conexion
    $consulta = null;
    $conexion = null;

    // Devolvemos los datos
    echo $jsonDatos;
}

die();
?>