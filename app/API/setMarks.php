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

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    // Transformamos el JSON de entrada de datos a un array asociativo 
    $datos = json_decode(file_get_contents('php://input'), true);
    // consulta preparada con el dato a insertar
    $INSERT = 'INSERT INTO gestionCursos.realizar (dni, id_curso, lastModifiedDate, puntuacion)
	            VALUES
		            (:dni, :id_curso, SYSDATE(), :puntuacion);';
                    
    // preparamos la consulta
    $consulta = $conexion->prepare($INSERT);
    // parámetros de la consulta
    $consulta->bindParam(':dni', $datos['dni']);
    $consulta->bindParam(':id_curso', $datos['id_curso']);
    $consulta->bindParam(':puntuacion', $datos['puntuacion']);

    // Comprobamos si hay un error en la ejecución de la consulta
    try {
        // ejecutamos la consulta
        $consulta->execute();
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

    // Devolvemos JSON
    echo $jsonDatos;

    // Cerramos consulta y conexión y salimos
    $consulta = null;
    $conexion = null;

   
}

if ($_SERVER['REQUEST_METHOD'] == 'PUT') {
    // Se transforma el json de entrada
    $datos = json_decode(file_get_contents('php://input'), true);
    //Obtenemos el id del dato a modificar
    $dni = $datos['dni'];
    $id_curso = $datos['id_curso'];

    // Comprobamos que existe
    $select = "SELECT * FROM realizar WHERE dni = :dni AND id_curso = :id_curso;";

    // Creamos y preparamos la consulta
    $consulta = $conexion->prepare($select);

    // Asociamos los parametros a la consulta
    $consulta->bindParam(':dni', $dni);
    $consulta->bindParam(':id_curso', $id_curso);

    // ejecutamos la consulta para comprobar que existe en la BBDD
    $consulta->execute();

    if ($consulta->rowCount() == 0) { //No existe
        header($headerJSON);
        // Mostramos el codigo de error
        header($codigosHTTP['404']);
    } else { // Existen datos

        //Actualizamos los datos
        $update = 'UPDATE realizar
                        SET puntuacion = :puntuacion
                    WHERE dni = :dni
                    AND id_curso = :id_curso;';
        // Preparamos la consulta
        $consulta = $conexion->prepare($update);
        //Asignamos los valores
        $consulta->bindParam(':puntuacion', $datos['puntuacion']);
        $consulta->bindParam(':dni', $dni);
        $consulta->bindParam(':id_curso', $datos['id_curso']);
        //Comprobamos que no haya ningun error en la ejecucion
        try {
            //Ejecutamos la consulta
            $consulta->execute();
            //Creacion del JSON con los datos
            $jsonDatos = json_encode($datos);
            //Devolvemos mensaje de que todo esta correcto
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
    }
    //Cerramos la consulta, la conexion y salimos del programa
    $consulta = null;
    $conexion = null;
}

die();
?>