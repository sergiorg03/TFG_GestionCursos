package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 *
 * Clase para mostrar la puntuacion del alumno en los test realizados
 *
 */
public class perfilAlumno extends AppCompatActivity {

    FuncionesVarias fv = new FuncionesVarias();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_alumno);
    }


}