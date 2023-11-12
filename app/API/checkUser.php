<?php
/**
 * 
 * Archivo para comprobar si un usuario existe o no
 * 
 */
// Devolver existe si el usuario indicado en la app existe, si no existe devolver noexiste

require_once("./utilities/conexion.php");
require_once("./utilities/funciones.php");
$conexion = conectarPDO($database);
$jsonDatos = '';

if ($_SERVER["REQUEST_METHOD"] == 'GET') {
    if (isset($_GET['usuario'])) {
        $select = 'SELECT usuario 
                        FROM personas
                        WHERE UPPER(usuario) = UPPER(:us);';
        $consulta = $conexion->prepare($select);
        $consulta->bindParam(':us', $_GET['usuario']);

        $consulta->execute();
        if ($consulta->rowCount() == 0) { // El usuario introducido no existe
            $respuesta["usuario"] = "noexiste";
            $jsonDatos = json_encode($respuesta);
            header($headerJSON);
            header($codigosHTTP['200']);
        }else { // El usuario introducido existe
            // Mostramos por la salida el mensaje 404 de Not Found si no existen datos con el id recibido
            $respuesta['usuario'] = 'existe';
            $jsonDatos = json_encode($respuesta);
            header($headerJSON);
            header($codigosHTTP['200']);
        }
    }  else {
        $SELECT = 'SELECT usuario 
                        FROM personas;';
        
        $consulta = $conexion->prepare($SELECT);
        $consulta->execute();
        if ($consulta->rowCount() > 0) {
            $respuesta = [];
            while ($usuario = $consulta->fetch(PDO::FETCH_ASSOC)) {
                $respuesta[] = $usuario;
            }
            $jsonDatos = json_encode($respuesta);
            header($headerJSON);
            header($codigosHTTP['200']);
        }else {
            header($headerJSON);
            header($codigosHTTP['404']);
        }
    }  
    echo $jsonDatos;
}

die();

?>