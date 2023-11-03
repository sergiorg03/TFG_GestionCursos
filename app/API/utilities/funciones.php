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

    /**
     * Funcion que comprueba si una cadena es un hash MD% o no
     * $md5 {String} -- Cadena a comrpobar si es un hash MD%
     * return {boolean} -- True si la cadena introducida es un HASH MD5, flase si no lo es
     */
    function isMd5($md5): bool
    {
        
        return preg_match('/^[a-f0-9A-F]{32}$/', $md5);
        
    }
?>