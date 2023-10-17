/**
 * @author sergio Rodríguez Geniz
 */

package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView usuario = null;
    TextView contra = null;
    PeticionesHTTP peticion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.user);
        contra = findViewById(R.id.pass);
    }

    public void pantallaInfo(View view) {
        Intent i = new Intent(this, PantallaInfo.class); // Creamos la instancia de la clase intent para pasar a otra pantalla
        startActivity(i); // Cambiamos de pantalla

        finish(); // Terminamos la pantalla Main
    }

    public void logIn(View v){
        String us = String.valueOf(usuario);
        String pass = String.valueOf(contra);
        // Hacemos una peticion http para ver si el usuario existe y la contraseña es correcta
        // Obtenemos de la peticion realizada el perfil del usuario
        this.peticion = new PeticionesHTTP("checkLogin.php?usuario="+us+"&contra="+pass);
        String datos = this.peticion.obtenerDatosServidor("GET");
        System.out.println(datos);

        /*Intent i = new Intent(this, ); // Creamos la instancia de la clase intent para pasar a otra pantalla
        startActivity(i); // Cambiamos de pantalla
        */
        finish(); // Terminamos la pantalla Main
    }
}