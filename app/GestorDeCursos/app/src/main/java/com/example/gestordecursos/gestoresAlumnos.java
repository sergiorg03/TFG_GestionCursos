package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 *
 * Clase para ver los DNI de los alumnos
 *
 */
public class gestoresAlumnos extends AppCompatActivity {

    FuncionesVarias fv = new FuncionesVarias();
    String dni_profesor;
    String clase;
    Class claseAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestores_alumnos);
        dni_profesor = getIntent().getStringExtra("dni_g");
        clase = getIntent().getStringExtra("clase");
        claseAnterior = fv.obtenerClase(clase);
    }
}