<?php
/**
 * 
 * Archivo para modificar los datos del usuario
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
    
    if(!isset($datos['dni_antiguo'])){

        //Obtenemos el id del dato a modificar
        $dni = $datos['dni'];
        //Actualizamos el dato
        $update = 'UPDATE personas
                        SET nombre = :nombre, 
                            apellido1 = :apellido1, 
                            apellido2 = :apellido2, 
                            telefono = :telefono,
                            email = :email, 
                            contra = :contra
                        WHERE dni = :dni;';
        // Preparamos la consulta
        $consulta = $conexion->prepare($update);
        //Asignamos los valores
        $consulta->bindParam(':nombre', $datos['nombre']);
        $consulta->bindParam(':apellido1', $datos['apellido1']);
        $consulta->bindParam(':apellido2', $datos['apellido2']);
        $consulta->bindParam(':telefono', $datos['telefono']);
        $consulta->bindParam(':email', $datos['email']);
        $contra = isMd5($datos['contra'])? $datos['contra'] : cadToMD5($datos['contra']);
        $consulta->bindParam(':contra', $contra);

        $consulta->bindParam(':dni', $dni);
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
}else {
    // Sentencia para modificar el perfil del alumno
    $INSERT = 'UPDATE PERSONAS 
                    SET dni = :dni,
                            nombre = :nombre, 
                            apellido1 = :apellido1, 
                            apellido2 = :apellido2, 
                            telefono = :telefono,
                            email = :email, 
                            usuario = :us,
                            contra = :contra
                            perfil = :perfil
                        WHERE dni = :dni_antiguo;';
    $dni_antiguo = $datos['dni_antiguo'];

    // preparamos la consulta
    $consulta = $conexion->prepare($INSERT);

    // Asignamos los parametros a la consulta
    $consulta->bindParam(':dni_antiguo', $dni_antiguo);
    $consulta->bindParam(':dni', $datos['dni_a']);
    $consulta->bindParam(':nombre', $datos['nombre']);
    $consulta->bindParam(':apellido1', $datos['apellido1']);
    $consulta->bindParam(':apellido2', $datos['apellido2']);
    $consulta->bindParam(':telefono', $datos['telefono']);
    $consulta->bindParam(':email', $datos['email']);
    $consulta->bindParam(':us', $datos['usuario']);
    $contra = isMd5($datos['contra'])? $datos['contra'] : cadToMD5($datos['contra']);
    $consulta->bindParam(':contra', $contra);
    $consulta->bindParam(':perfil', $$datos['perfil']);      
    
    //Comprobamos que no haya ningun error en la ejecucion
    try {
        // ejecutamos la consulta
        $consulta->execute();
        //Creacion del JSON con los datos
        $jsonDatos = json_encode($datos);
        //Devolvemos mensaje de que todo esta correcto
        header($headerJSON);
        header($codigosHTTP['200']);
    }catch(PDOException $exception) { // Se produce error
        // Capturamos el error producido
            $errorDescription = $exception->getMessage();
            $datos = array('error' => $errorDescription);
            // Creamos JSON con datos recibidos y el nuevo id
            $jsonDatos = json_encode($datos);
            // Cabeceras de error en la ejecución de la consulta
            header($headerJSON);
            header($codigosHTTP['500']);
        }
}
    // Devolvemos JSON
    echo $jsonDatos;

    //Cerramos la consulta, la conexion y salimos del programa
    $consulta = null;
    $conexion = null;

    die();
}
?>