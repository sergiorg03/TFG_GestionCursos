<?php
    /**
     * 
     * File to check if the user exists, if the user exists gets its profile 
     * 
     */
    
    require_once("./conexion.php");
    require_once("./funciones.php");
    $conexion = conectarPDO($database);
    $errores = [];
    
    if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    
    if (isset($_GET['usuario'])) { // Comprobamos si nos llega un usuario por GET
        
        if (isset($_GET['contra'])) { // Comprobamos si nos llega una contraseña por GET
            // Guardamos los valores
            $user = $_GET['usuario'];
            $contra = $_GET['contra'];

            // Comprobamos que el usuario introducido existe en la BD
            $select = 'SELECT usuario
                            FROM personas
                            WHERE UPPER(usuario) = UPPER(:us);';

            // Preparamos la consulta
            $consulta = $conexion->prepare($select);

            // Asignamos el parametro user a la consulta
            $consulta->bindParam(':us', $user);

            // Ejecutamos la consulta
            $consulta->execute();

            if ($consulta->rowCount() > 0) { // Si existe el usuario introducido
                $select2 = 'SELECT usuario, md5(contra)
                                FROM personas
                                WHERE UPPER(usuario) = UPPER(:us)
                                  AND UPPER(contra) = UPPER(:contra);';
                
                // Preparamos la consulta
                $consulta = $conexion->prepare($select2);
                // Asignamos los parametros a la consulta
                $consulta->bindParam(':us', $user);
                $contraMD5 = cadToMD5($contra);
                print($contraMD5);
                $consulta->bindParam(':contra', $contraMD5);

                $consulta->execute();
                
                if ($consulta->rowCount() > 0) { // la contraseña indicada es correcta 
                    $jsonDatos = json_encode($consulta->fetch(PDO::FECTH_ASSOC));
                    header($headerJSON);
                    header($codigosHTTP['200']);
                } else { // La contraseña indicada es erronea
                    // Mostramos por la salida el mensaje 404 de Not Found si no existen datos con el id recibido
                    header($headerJSON);
                    header($codigosHTTP['404']);
                    $errores[] = "La contraseña es erronea";
                    $jsonDatos = json_encode($errores);
                }
            }else { // No existe el usuario introducido
                // Mostramos por la salida el mensaje 404 de Not Found si no existen datos con el id recibido
                header($headerJSON);
                header($codigosHTTP['404']);
                $errores[] = "El usuario indicado es erroneo";
                $jsonDatos = json_encode($errores);
            }
        }else{ // No se pasa la contraseña
            header($headerJSON);
            header($codigosHTTP['404']);
            $errores[] = "Debe introducir una contraseña";
            $jsonDatos = json_encode($errores);
        }
    }else{ // No se pasa el usuario 
        header($headerJSON);
        header($codigosHTTP['404']);
        $errores[] = "Debe introducir un usuario";
        $jsonDatos = json_encode($errores);
    }

    
    // Devolvemos JSON
    echo $jsonDatos;
    // Cerramos la consulta
    $consulta = null;
    $conexion = null;
    }

    die();

?>