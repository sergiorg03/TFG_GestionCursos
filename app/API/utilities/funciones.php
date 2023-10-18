<?php

    /***
     * Funcion que convierte una cadena recibida en un hash MD5
     * $cadena {String} -- Parametro recibido a convertir en md5
     * return {String} -- Devuelve el hash MD5 de la cadena pasada por parametro
     */
    function cadToMD5($cadena):String
    {
        return md5($cadena);
    }

    
?>