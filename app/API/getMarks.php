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

if ($_SERVER['REQUEST_METHOD'] == 'GET') { // La solicitud es GET
    if (isset($_GET['dni'])) { // El dni del alumno está definido.
        // Sentencia para obtener el nombre del curso, la puntuación, el dni de la persona y el id del curso
        $SELECT = 'SELECT r.dni AS dniPersona, r.id_curso AS idCurso, r.puntuacion AS puntuacion, c.nombre AS enunciado
                        FROM realizar r
                            INNER JOIN personas p ON p.dni = r.dni
                            INNER JOIN cursos c   ON c.id = r.id_curso
                        WHERE r.dni = :dni;';
        
        // Preparamos la consulta
        $consulta = $conexion->prepare($SELECT);
        // Asignamos los parametros a la consulta
        $consulta->bindParam(':dni', $_GET['dni']);
        // Ejecutamos la consulta
        $consulta->execute();

        if ($consulta->rowCount() > 0) { // La consulta ha devuelto datos
            $notas = [];
            while ($nota = $consulta->fetch(PDO::FETCH_ASSOC)) {
                $notas[] = $nota;
            }
            header($headerJSON);
            header($codigosHTTP['200']);
            $jsonDatos = json_encode($notas);
        }else {// La consulta no devolvio nada
            $errores[] = 'No se encontraron datos';
            header($headerJSON);
            header($codigosHTTP['404']);
            $jsonDatos = json_encode($errores);
        }
    }else{
        $errores[] = 'dni no introducido';
        header($headerJSON);
        header($codigosHTTP['500']);
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