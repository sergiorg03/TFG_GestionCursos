package com.example.gestordecursos;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase para la creacion de las funciones de validaciones y otras varias.
 */
public class FuncionesVarias {

    //IP conexion PC sobremesa
    private String IP;
    private String URL;
    private final char[] LETRAS = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

    public FuncionesVarias (){
        this.IP = "192.168.135.37"; // Se deberá cambiar el valor de esta variable por el valor de la IP del propio ordenador para el correcto funcionamiento de la APP.
        this.URL = "http://"+this.IP+"/tfg/app/API/";
        //System.out.println("IP: "+IP + " URL: "+ URL);
    }

    public FuncionesVarias (String IP){
        this.IP = IP;
        this.URL = "http://"+IP+"/tfg/app/API/";
        //System.out.println("IP: "+IP + " URL: "+ URL);
    }

    /**
     * Metodo GET
     * @return -- Devuelve el valor de la IP
     */
    public String getIP(){
        return this.IP;
    }

    /**
     * Metodo SET
     * @return -- Devuelve la URL
     */
    public void setURL(String URL){
        this.URL = URL;
    }

    /**
     * Metodo GET
     * Metodo para obtener la URL en las diferentes clases
     * @return -- Devuelve la URL
     */
    public String getURL(){
        return this.URL;
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
                }else{
                    if (claseAnterior.equalsIgnoreCase("gestoresalumnos")){
                        clase = gestoresAlumnos.class;
                    }else{
                        if (claseAnterior.equalsIgnoreCase("notasalumnos")){
                            clase = notasAlumnos.class;
                        }
                    }
                }
            }
        }

        return clase;
    }

    /**
     * Metodo que devuelve el nombre de un archivo
     * @param ruta -- ruta del archivo del que sacar el nombre
     * @return -- Nombre sin espacios en blanco
     */
    public String nombreCurso(String ruta){

        System.out.println("FV > nombreCurso--> nombre--> "+ quitarEspaciosEnBlanco(ruta));
        String [] nombre = ruta.split("/");
        return quitarEspaciosEnBlanco(nombre[nombre.length-1]);
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
        final String cadenaADevolver = "N/A";
        if (contieneTexto(cadena) && !cadena.isEmpty()){
            return cadena;
        }
        return cadenaADevolver;
    }
}