package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 *
 * Clase que muestra el perfil de la persona
 *
 */
public class Perfil extends AppCompatActivity {

    Class claseAnterior;
    String dni;
    TextView tv_dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        dni = getIntent().getStringExtra("dni");
        tv_dni = findViewById(R.id.dni_perfil);
        tv_dni.setText(dni.toUpperCase());

        mostrarDatos();
    }

    /**
     * Metodo para volver a la clase anterior
     * @param v
     */
    public void volver(View v){
        Intent i = new Intent(this, claseAnterior);
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