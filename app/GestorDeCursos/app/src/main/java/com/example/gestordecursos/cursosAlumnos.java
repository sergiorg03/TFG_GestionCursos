package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 *
 * Clase para mostrar los cursos que los alumnos pueden realizar
 *
 *
 */
public class cursosAlumnos extends AppCompatActivity {

    FuncionesVarias fv = new FuncionesVarias();
    String dni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos_alumnos);
        dni = getIntent().getStringExtra("dni");

    }

    /**
     * Funcion para salir de la pantalla a la pantalla de LogIn
     * @param v
     */
    public void salir(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    /**
     * Metodo que me lleva a la clase para poder ver las notas de cada curso
     * @param v
     */
    public void verNotas(View v){
        Intent i = new Intent(this, notasAlumnos.class);
        i.putExtra("dni", dni);
        startActivity(i);
        finish();
    }

    /**
     * Metodo para ir a la clase de perfil
     * @param v
     */
    public void perfil(View v){
        Intent i = new Intent(this, Perfil.class);
        i.putExtra("dni", dni);
        i.putExtra("clase", "cursosAlumnos");
        startActivity(i);
        finish();
    }
}