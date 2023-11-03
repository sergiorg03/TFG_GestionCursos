package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 *
 * Clase que muestra el perfil de la persona
 *
 */
public class Perfil extends AppCompatActivity {

    FuncionesVarias fv = new FuncionesVarias();
    String dni;
    String nombreClase;
    Class claseAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        dni = getIntent().getStringExtra("dni");
        nombreClase = getIntent().getStringExtra("clase");
        claseAnterior = fv.obtenerClase(nombreClase);
    }

    /**
     * Metodo para volver a la clase anterior
     * @param v
     */
    public void volver(View v){
        Intent i = new Intent(this, claseAnterior);
        i.putExtra("dni", dni);
        startActivity(i);
        finish();
    }

    /**
     * Método para modificar el perfil
     * @param v
     */
    public void updateProfile(View v){

    }

    /**
     * Metodo para mostrar los datos del usuario
     */
    public void mostrarDatos(){
    }
}