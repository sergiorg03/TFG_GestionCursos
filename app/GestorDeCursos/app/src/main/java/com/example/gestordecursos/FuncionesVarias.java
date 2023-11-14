package com.example.gestordecursos;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase para la creacion de las funciones de validaciones y otras varias.
 */
public class FuncionesVarias {

    private final String IP = "192.168.135.37";
    private final String URL = "http://" + getIP() + "/tfg/app/API/";
    final char[] LETRAS = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

    /**
     * Metodo get
     * @return -- Devuelve el valor de la IP
     */
    public String getIP(){
        return IP;
    }

    /**
     * Funcion que comprueba si el DNI esta bien formado o no
     * @param dni -- DNI a comprobar
     * @return -- True si el DNI esta bien formado, False si no.
     */
    public boolean formatoDNI(String dni){
        boolean correcto = false;

        if(dni.length() == 9) {
            String numeroDNI = dni.substring(0, 8);
            char letraDni = Character.toUpperCase(dni.charAt(8));

            if (esNumerico(numeroDNI)) {
                if (letraDni == LETRAS[(Integer.parseInt(numeroDNI) % 23)]) {
                    correcto = true;
                }
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
        try{
            Integer.parseInt(numero);
            return true;
        }catch (NumberFormatException nfe){
            return false;
        }
    }

    /**
     * Funcion que comprueba si el formato del email es correcto
     * @param email -- email a comprobar
     * @return -- True si el formato del email introducido es correcto, False si no.
     */
    public boolean formatoEmail(String email){
        boolean es_correcto = false;
        String expr_email = "^[a-zA-Z0-9._%+-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,}$";

        Pattern p = Pattern.compile(expr_email); // Compilamos la expresion regular del email
        Matcher matcher = p.matcher(email); // Comprobamos que el patron del email se corresponde con la expresion regular
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

    /**
     * Metodo que comprueba si una cadena introducida por parametro tiene texto o no.
     * @param cadena -- Cadena a comprobar
     * @return -- True si  la cadena introducida tiene texto, False si no.
     */
    public boolean contieneTexto(String cadena){
        boolean contiene = (cadena == null || cadena == "" || cadena.isEmpty())? false : true;
        return contiene;
    }

    /**
     * Metodo que devuelve la clase a la que se corresponde una cadena de texto
     * @param claseAnterior -- Cadena a comprobar la clase a la que corresponde
     * @return -- Clase correspondiente
     */
    public Class obtenerClase(String claseAnterior){
        Class clase = null;

        if (claseAnterior.equalsIgnoreCase("cursosalumnos")){
            clase = cursosAlumnos.class;
        }else{
            if (claseAnterior.equalsIgnoreCase("cursosgestores")){
                clase = cursosGestores.class;
            }else {
                if (claseAnterior.equalsIgnoreCase("editorcursos")){
                    clase = EditorCursos.class;
                }
            }
        }

        return clase;
    }

    public String nombreCurso(Uri ruta){

        System.out.println("FV > nombreCurso--> nombre--> "+ quitarEspaciosEnBlanco(ruta.getLastPathSegment()));

        return quitarEspaciosEnBlanco(ruta.getLastPathSegment());
    }

    /**
     * Metodo que quita los espacios en blanco de una cadena
     * @param cadena -- Cadena a quitar los espacios en blanco
     * @return -- Devuelve la cadena sin espacios en blanco
     */
    public String quitarEspaciosEnBlanco(String cadena) {
        String cadenaADevolver = "";
        cadena = cadena.trim();

        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == ' '){
                cadenaADevolver = cadenaADevolver+"_";
            }else cadenaADevolver = cadenaADevolver+cadena.charAt(i);
        }

        return cadenaADevolver;
    }

    /**
     * Metodo que comprueba si una cadena introducida por parametro tiene texto y si no, devuelve una cadena vacia estandar
     * @param cadena -- Cadena a comprobar si tiene o no texto
     * @return -- Devuelve la cadena introducida si está contiene texto, si no, devuelve "Texto por defecto"
     */
    public String textoFinal(String cadena){
        final String cadenaADevolver = "Texto por defecto";
        if (!cadena.isEmpty()){
            return cadena;
        }
        return cadenaADevolver;
    }
}