package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 *
 * Clase para mostrar los cursos de los gestores (editar, borrar)
 *
 */
public class EditorCursos extends AppCompatActivity {

    FuncionesVarias fv = new FuncionesVarias();
    String dni;
    String clase;
    Class claseAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_cursos);

        dni = getIntent().getStringExtra("dni");
        clase = getIntent().getStringExtra("clase");
        claseAnterior = fv.obtenerClase(clase);
    }
}