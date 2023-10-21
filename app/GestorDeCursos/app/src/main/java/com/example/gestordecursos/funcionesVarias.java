package com.example.gestordecursos;

/**
 * Clase para la creacion de las funciones de validaciones y otras varias.
 */
public class funcionesVarias {

    final char[] LETRAS = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

    /**
     * Funcion que comprueba si el DNI esta bien formado o no
     * @param dni -- DNI a comprobar
     * @return -- True si el DNI esta bien formado, False si no.
     */
    public boolean formatoDNI(String dni)
    {
        boolean correcto = false;

        String numeroDNI = dni.substring(0, 8);
        char letraDni = dni.charAt(8);

        if (esNumerico(numeroDNI)){
            if (letraDni == LETRAS[(Integer.parseInt(numeroDNI) % 23)]){
                correcto = true;
            }
        }
        return correcto;
    }

    /**
     * Funcion que comprueba si una cadena pasada es un numero o no
     * @param numero -- Cadena a comprobar si es numerico
     * @return -- True si es un numero, False si no
     */
    public boolean esNumerico(String numero){
        boolean lo_es = false;

        try{
            Integer.parseInt(numero);
            lo_es = true;
        }catch (NumberFormatException nfe){}

        return lo_es;
    }


}