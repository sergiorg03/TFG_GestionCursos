<?php
// Añadimos los ficheros necesarios para la conexion con la Base de Datos y las funciones a utilizar
require_once("../conexion.php");
require_once("../funciones.php");

// Creamos la conexion a la base de datos
$conexion = conectarPDO($database);

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    if (isset($_GET['usuario'])) {

        $sentencia = "SELECT * 
                            FROM personas 
                            WHERE UPPER(usuario) = UPPER(:us);";

        $consulta = $conexion->prepare($sentencia);

        $consulta->bindParam(":us", $_GET['usuario']);

        $consulta->execute();

        if ($consulta->rowCount() > 0) { // Comprobamos que exista el usuario

            if (isset($_GET['contra'])) {
                // Comprobamos que la contraseña sea la misma
                $sentencia = "SELECT *
                                FROM personas
                                WHERE UPPER(usuario) = UPPER(:us)
                                AND UPPER(contra) = UPPER(:contra);";

                $consulta = $conexion->prepare($sentencia);

                $consulta->bindParam(":us", $_GET['usuario']);
                $consulta->bindParam(":contra", $_GET['contra']);

                $consulta->execute();

                if ($consulta->rowCount() > 0) { // Obtenemos el perfil del usuario
                    $sentencia = "SELECT perfil
                                    FROM personas
                                    WHERE UPPER(usuario) = UPPER(:us);";
                    
                    // Preparamos la consulta
                    $consulta = $conexion->prepare($sentencia);


                    $consulta->bindParam(":us", $_GET['usuario']);

                    $consulta->execute();

                    $jsonDatos = json_encode($consulta->fetch(PDO::FETCH_ASSOC));
                    header($headerJSON);
                    header($codigosHTTP["200"]);

                } else { // NO coinciden las contraseñas
                    header($headerJSON);
                    header($codigosHTTP['404']);
                }
                
            } else {// La contraseña no esta definida
                header($headerJSON);
                header($codigosHTTP["404"]);
            }
            
        } else { // No existe el usuario introducido
            header($headerJSON);
            header($codigosHTTP["404"]);
        }

    } else { // NO esta definido el usuario
        header($headerJSON);
        header($codigosHTTP["404"]);
    }

    // Devolvemos el json 
    echo $jsonDatos;
}

exit();
?>