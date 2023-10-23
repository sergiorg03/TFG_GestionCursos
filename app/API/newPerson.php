<?php
/**
 * 
 * Archivo para la insercion en la base de datos de una nueva persona
 * 
 */
require_once("./utilities/conexion.php");
require_once("./utilities/funciones.php");
$conexion = conectarPDO($database);
$errores = [];
$datos = '';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    // Obtenemos los datos del usuario a insertar
    $datos = json_decode(file_get_contents('php://input'), true);

    // Creamos la consulta SQL
    $INSERT = 'INSERT INTO personas (dni, nombre, apellido1, apellido2, telefono, email, usuario, contra, perfil)
                    VALUES (:dni, :nombre, :ap1, :ap2, :telf, :email, :usuario, :contra, :perfil);';

    // Preparamos la consulta
    $consulta = $conexion->prepare($INSERT);

    // Asignamos los parametros a la consulta
    $consulta->bindParam(":dni", $datos['dni']);
    $consulta->bindParam(":nombre", $datos['nombre']);
    $consulta->bindParam(":ap1", $datos['apellido1']);
    $consulta->bindParam(":ap2", $datos['apellido2']);
    $consulta->bindParam(":telf", $datos['telefono']);
    $consulta->bindParam(":email", $datos['email']);
    $consulta->bindParam(":usuario", $datos['usuario']);
    $consulta->bindParam(":contra", cadToMD5($datos['contra']));
    $consulta->bindParam(":perfil", $datos['perfil']);

    try {
        $consulta->execute();

        // Añadimos el encabezado y el codigo HTTP
        header($headerJSON);
        header($codigosHTTP["201"]);
    } catch (PDOException $pdoe) {
        $error = $pdoe->getMessage();
        $datos = array("error" => $error);
        // Añadimos el encabezado y el codigo HTTP
        header($headerJSON);
        header($codigosHTTP["500"]);
    }
    $jsonDatos = json_encode($datos);

    // Cerramos la conexion y la consulta
    $consulta = null;
    $conexion = null;
    
    // Devolvemos si ha ocurrido algun error
    echo $jsonDatos;
}
die();
?>