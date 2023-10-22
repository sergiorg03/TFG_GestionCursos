package com.example.gestordecursos;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase para la creacion de las funciones de validaciones y otras varias.
 */
public class FuncionesVarias {

    final char[] LETRAS = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

    /**
     * Funcion que comprueba si el DNI esta bien formado o no
     * @param dni -- DNI a comprobar
     * @return -- True si el DNI esta bien formado, False si no.
     */
    public boolean formatoDNI(String dni){
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

    /**
     * Funcion que comprueba si el formato del email es correcto
     * @param email -- email a comprobar
     * @return -- True si el formato del email introducido es correcto, False si no.
     */
    public boolean formatoEmail(String email){
        boolean es_correcto = false;
        String expr_email = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        Pattern p = Pattern.compile(email); // Obtenemos el patron del email
        Matcher matcher = p.matcher(expr_email); // Comprobamos que el patron del email se corresponde con la expresion regular
        es_correcto = matcher.matches();

        return es_correcto;
    }

    /**
     * Funcion para mostrar un mensaje
     * @param c -- Clase en la que vamos a mostrar el mensaje
     * @param mensaje -- mensaje a mostrar
     */
    public void mostrarMensaje(Context c, String mensaje) {
        Toast.makeText(c, mensaje, Toast.LENGTH_LONG).show();
    }
}