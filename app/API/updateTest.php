<?php
/**
 * 
 * Archivo para modificar el test de un curso
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
	// Obtenemos el id del curso
    $id_curso = $datos['id_curso'];

	// Creamos las sentencias de modificación de datos de las preguntas y las opciones
    $UPDATE_PREGUNTAS = 'UPDATE preguntas
                            SET Enunciado = :enun
                          WHERE id = :id
                            AND id_curso = :curso;';
    
    $UPDATE_OPCIONES =  'UPDATE respuestastest
                            SET opcion = :op,
                                esCorrecta = :cor
                          WHERE id = :id
                            AND id_curso = :curso
                            AND id_pregunta = :id_preg;';
    
    // Primera pregunta
    $consulta = $conexion->prepare($UPDATE_PREGUNTAS);
    $consulta->bindParam(':curso', $id_curso);
    $consulta->bindParam(':id', $datos['id_pregunta1']);
    $consulta->bindParam(':enun', $datos['enunciado_pregunta1']);
    try {
        //Ejecutamos la consulta
        $consulta->execute();
        
        // Primera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op1_p1']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta1']);
        $consulta->bindParam(':op', $datos['enun_op1_p1']);
        $consulta->bindParam(':cor', $datos['escor_op1_p1']);
        $consulta->execute();

        // Segunda opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op2_p1']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta1']);
        $consulta->bindParam(':op', $datos['enun_op2_p1']);
        $consulta->bindParam(':cor', $datos['escor_op2_p1']);
        $consulta->execute();

        // Tercera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op3_p1']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta1']);
        $consulta->bindParam(':op', $datos['enun_op3_p1']);
        $consulta->bindParam(':cor', $datos['escor_op3_p1']);
        $consulta->execute();

        // Segunda Pregunta
        $consulta = $conexion->prepare($UPDATE_PREGUNTAS);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_pregunta2']);
        $consulta->bindParam(':enun', $datos['enunciado_pregunta2']);
        $consulta->execute();
        // Primera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op1_p2']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta2']);
        $consulta->bindParam(':op', $datos['enun_op1_p2']);
        $consulta->bindParam(':cor', $datos['escor_op1_p2']);
        $consulta->execute();

        // Segunda opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op2_p2']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta2']);
        $consulta->bindParam(':op', $datos['enun_op2_p2']);
        $consulta->bindParam(':cor', $datos['escor_op2_p2']);
        $consulta->execute();

        // Tercera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op3_p2']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta2']);
        $consulta->bindParam(':op', $datos['enun_op3_p2']);
        $consulta->bindParam(':cor', $datos['escor_op3_p2']);
        $consulta->execute();

        // Trecera Pregunta
        $consulta = $conexion->prepare($UPDATE_PREGUNTAS);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_pregunta3']);
        $consulta->bindParam(':enun', $datos['enunciado_pregunta3']);
        $consulta->execute();
        // Primera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op1_p3']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta3']);
        $consulta->bindParam(':op', $datos['enun_op1_p3']);
        $consulta->bindParam(':cor', $datos['escor_op1_p3']);
        $consulta->execute();

        // Segunda opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op2_p3']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta3']);
        $consulta->bindParam(':op', $datos['enun_op2_p3']);
        $consulta->bindParam(':cor', $datos['escor_op2_p3']);
        $consulta->execute();

        // Tercera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op3_p3']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta3']);
        $consulta->bindParam(':op', $datos['enun_op3_p3']);
        $consulta->bindParam(':cor', $datos['escor_op3_p3']);
        $consulta->execute();

        // Cuarta Pregunta
        $consulta = $conexion->prepare($UPDATE_PREGUNTAS);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_pregunta4']);
        $consulta->bindParam(':enun', $datos['enunciado_pregunta4']);
        $consulta->execute();
        // Primera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op1_p4']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta4']);
        $consulta->bindParam(':op', $datos['enun_op1_p4']);
        $consulta->bindParam(':cor', $datos['escor_op1_p4']);
        $consulta->execute();

        // Segunda opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op2_p4']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta4']);
        $consulta->bindParam(':op', $datos['enun_op2_p4']);
        $consulta->bindParam(':cor', $datos['escor_op2_p4']);
        $consulta->execute();

        // Tercera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op3_p4']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta4']);
        $consulta->bindParam(':op', $datos['enun_op3_p4']);
        $consulta->bindParam(':cor', $datos['escor_op3_p4']);
        $consulta->execute();

        // Quinta Pregunta
        $consulta = $conexion->prepare($UPDATE_PREGUNTAS);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_pregunta5']);
        $consulta->bindParam(':enun', $datos['enunciado_pregunta5']);
        $consulta->execute();
        // Primera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op1_p5']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta5']);
        $consulta->bindParam(':op', $datos['enun_op1_p5']);
        $consulta->bindParam(':cor', $datos['escor_op1_p5']);
        $consulta->execute();

        // Segunda opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op2_p5']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta5']);
        $consulta->bindParam(':op', $datos['enun_op2_p5']);
        $consulta->bindParam(':cor', $datos['escor_op2_p5']);
        $consulta->execute();

        // Tercera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op3_p5']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta5']);
        $consulta->bindParam(':op', $datos['enun_op3_p5']);
        $consulta->bindParam(':cor', $datos['escor_op3_p5']);
        $consulta->execute();

        // Sexta Pregunta
        $consulta = $conexion->prepare($UPDATE_PREGUNTAS);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_pregunta6']);
        $consulta->bindParam(':enun', $datos['enunciado_pregunta6']);
        $consulta->execute();
        // Primera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op1_p6']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta6']);
        $consulta->bindParam(':op', $datos['enun_op1_p6']);
        $consulta->bindParam(':cor', $datos['escor_op1_p6']);
        $consulta->execute();

        // Segunda opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op2_p6']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta6']);
        $consulta->bindParam(':op', $datos['enun_op2_p6']);
        $consulta->bindParam(':cor', $datos['escor_op2_p6']);
        $consulta->execute();

        // Tercera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op3_p6']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta6']);
        $consulta->bindParam(':op', $datos['enun_op3_p6']);
        $consulta->bindParam(':cor', $datos['escor_op3_p6']);
        $consulta->execute();

        // Septima Pregunta
        $consulta = $conexion->prepare($UPDATE_PREGUNTAS);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_pregunta7']);
        $consulta->bindParam(':enun', $datos['enunciado_pregunta7']);
        $consulta->execute();
        // Primera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op1_p7']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta7']);
        $consulta->bindParam(':op', $datos['enun_op1_p7']);
        $consulta->bindParam(':cor', $datos['escor_op1_p7']);
        $consulta->execute();

        // Segunda opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op2_p7']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta7']);
        $consulta->bindParam(':op', $datos['enun_op2_p7']);
        $consulta->bindParam(':cor', $datos['escor_op2_p7']);
        $consulta->execute();

        // Tercera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op3_p7']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta7']);
        $consulta->bindParam(':op', $datos['enun_op3_p7']);
        $consulta->bindParam(':cor', $datos['escor_op3_p7']);
        $consulta->execute();

        // Octava Pregunta
        $consulta = $conexion->prepare($UPDATE_PREGUNTAS);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_pregunta8']);
        $consulta->bindParam(':enun', $datos['enunciado_pregunta8']);
        $consulta->execute();
        // Primera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op1_p8']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta8']);
        $consulta->bindParam(':op', $datos['enun_op1_p8']);
        $consulta->bindParam(':cor', $datos['escor_op1_p8']);
        $consulta->execute();

        // Segunda opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op2_p8']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta8']);
        $consulta->bindParam(':op', $datos['enun_op2_p8']);
        $consulta->bindParam(':cor', $datos['escor_op2_p8']);
        $consulta->execute();

        // Tercera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op3_p8']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta8']);
        $consulta->bindParam(':op', $datos['enun_op3_p8']);
        $consulta->bindParam(':cor', $datos['escor_op3_p8']);
        $consulta->execute();

        // Novena Pregunta
        $consulta = $conexion->prepare($UPDATE_PREGUNTAS);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_pregunta9']);
        $consulta->bindParam(':enun', $datos['enunciado_pregunta9']);
        $consulta->execute();
        // Primera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op1_p9']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta9']);
        $consulta->bindParam(':op', $datos['enun_op1_p9']);
        $consulta->bindParam(':cor', $datos['escor_op1_p9']);
        $consulta->execute();

        // Segunda opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op2_p9']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta9']);
        $consulta->bindParam(':op', $datos['enun_op2_p9']);
        $consulta->bindParam(':cor', $datos['escor_op2_p9']);
        $consulta->execute();

        // Tercera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op3_p9']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta9']);
        $consulta->bindParam(':op', $datos['enun_op3_p9']);
        $consulta->bindParam(':cor', $datos['escor_op3_p9']);
        $consulta->execute();

        // Decima Pregunta
        $consulta = $conexion->prepare($UPDATE_PREGUNTAS);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_pregunta10']);
        $consulta->bindParam(':enun', $datos['enunciado_pregunta10']);
        $consulta->execute();
        // Primera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op1_p10']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta10']);
        $consulta->bindParam(':op', $datos['enun_op1_p10']);
        $consulta->bindParam(':cor', $datos['escor_op1_p10']);
        $consulta->execute();

        // Segunda opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op2_p10']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta10']);
        $consulta->bindParam(':op', $datos['enun_op2_p10']);
        $consulta->bindParam(':cor', $datos['escor_op2_p10']);
        $consulta->execute();

        // Tercera opcion
        $consulta = $conexion->prepare($UPDATE_OPCIONES);
        $consulta->bindParam(':curso', $id_curso);
        $consulta->bindParam(':id', $datos['id_op3_p10']);
        $consulta->bindParam(':id_preg', $datos['id_pregunta10']);
        $consulta->bindParam(':op', $datos['enun_op3_p10']);
        $consulta->bindParam(':cor', $datos['escor_op3_p10']);
        $consulta->execute();
        //Creacion del JSON con los datos
        $jsonDatos = json_encode($datos);
        
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

    //Cerramos la consulta, la conexion y salimos del programa
    $consulta = null;
    $conexion = null;
}

die();
?>