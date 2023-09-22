<?php
    /*Datos de la BBDD*/
    $database = [
        "host" => "localhost"
        "username" => "tfg_sergio"
        "password" => "tfg_sergio"
        "db" => "gestionCursos"
    ];

    $headerJSON = 'Content-Type: application/json';
    $codigosHTTP = [ 
        "200" => "HTTP/1.1 200 OK",
        "201" => "HTTP/1.1 201 Created",
        "202" => "HTTP/1.1 202 Accepted",
        "400" => "HTTP/1.1 400 Bad Request",
        "404" => "HTTP/1.1 404 Not Found",
        "500" => "HTTP/1.1 500 Internal Server Error"
    ];
    
    /**
     * Funcion que nos devuelve la conexion con la base de datos.
     * @param array $dbInfo -- Array con la informacion de la base de datos
     * @return mixed PDO -- Devuelve la conexion realizada con la base de datos
     */
    function conectarPDO(array $dbInfo): PDO
    {
        try {
            $mysql="mysql:host=$dbInfo[host];dbname=$dbInfo[db];charset=utf8";
            $conexion = new PDO($mysql, $dbInfo["username"], $dbInfo["password"]);
            // set the PDO error mode to exception
            $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (PDOException $exception) 
        {
          // Obtenemos el error
          $errorDescription = $exception->getMessage();
          $datosJson = json_encode(array('error' => $errorDescription));
    
          // Devolvemos codigo y mensaje del error
          header ("Content-Type: application/json");
          header ("HTTP/1.1 500 Internal Server Error");
          echo $datosJson;
          exit();
        }
        return $conexion;    
    }
?>