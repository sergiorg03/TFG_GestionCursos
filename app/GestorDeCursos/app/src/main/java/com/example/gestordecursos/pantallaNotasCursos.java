package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Clase para mostrar las notas obtenidas por un alumno
 */
public class pantallaNotasCursos extends AppCompatActivity {

    FuncionesVarias fv = new FuncionesVarias();
    String dni;
    String clase;
    Class claseAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_notas_cursos);

        dni = getIntent().getStringExtra("dni");
        clase = getIntent().getStringExtra("clase");
        claseAnterior = fv.obtenerClase(clase);
    }

    /**
     * Metodo para volver a la clase anterior
     * @param v
     */
    public void volver(View v){
        Intent i = new Intent(this, cursosAlumnos.class);
        i.putExtra("dni", dni);
        startActivity(i);
        finish();
    }

    /**
     * Metodo para ir al perfil
     * @param v -- View del boton pulsado
     */
    public void perfil(View v){
        Intent i = new Intent(this, Perfil.class);
        i.putExtra("dni", dni);
        i.putExtra("clase", "notasalumnos");
        startActivity(i);
        finish();
    }


}