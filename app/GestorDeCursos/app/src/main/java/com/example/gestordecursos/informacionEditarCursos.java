package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class informacionEditarCursos extends AppCompatActivity {

    FuncionesVarias fv = new FuncionesVarias();
    String dni;
    String id_curso;
    String clase;
    Class claseAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_editar_cursos);
        dni = getIntent().getStringExtra("dni");
        id_curso = getIntent().getStringExtra("id_curso");
        clase = getIntent().getStringExtra("clase");
        claseAnterior = fv.obtenerClase(clase);
    }

    /**
     * Metodo para ir a la clase EditorCursos
     * @param v
     */
    public void irEditarCurso(View v){
        Intent i = new Intent(this, EditorCursos.class);
        i.putExtra("dni", dni);
        i.putExtra("id_curso", id_curso);
        i.putExtra("clase", clase);
        startActivity(i);
        finish();
    }
}