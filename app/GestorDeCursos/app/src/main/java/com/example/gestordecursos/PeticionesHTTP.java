package com.example.gestordecursos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PeticionesHTTP {

    private final String urlPeticion = "http://localhost/tfg/app/API/";
    private String urlScript = "";

    public PeticionesHTTP(){
    }

    public PeticionesHTTP(String nombreArchivo){
        this.urlScript = this.urlPeticion+nombreArchivo;
    }

    public String obtenerDatosServidor(String requestMethod){
        String cadenaADevolver = "";
        try {
            // Creamos la conexion con el servidor
            URL url = new URL(urlScript);
            HttpURLConnection conection = (HttpURLConnection) url.openConnection();

            // Configuramos el metodo de de solicitud hacia el servidor
            conection.setRequestMethod(requestMethod);

            // Obtenemos la respuesta
            //int codigoHTTP = conection.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(conection.getInputStream()));
            String respuesta;
            StringBuilder sb = new StringBuilder();

            // Vamos obteniendo los datos
            while ((respuesta = br.readLine()) != null){
                sb.append(respuesta);
            }

            // Guardamos el valor de la cadena devuelta en la variable que devolveremos
            cadenaADevolver = sb.toString();

            // Cerramos las conexiones
            sb = null;
            br.close();
            conection = null;
            url = null;

        }catch (MalformedURLException murle){
            cadenaADevolver = "No se pudo obtener datos";
        } catch (IOException e) {
            cadenaADevolver = "Ocurrio un error a la hora de crear la conexion http";
        }

        return cadenaADevolver;
    }
}