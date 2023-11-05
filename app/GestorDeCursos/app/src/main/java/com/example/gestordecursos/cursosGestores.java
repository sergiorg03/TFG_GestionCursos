package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 *
 * Clase para mostrar los cursos en el perfil gestor
 *
 */
public class cursosGestores extends AppCompatActivity {

    FuncionesVarias fv = new FuncionesVarias();
    String dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos_gestores);
        dni = getIntent().getStringExtra("dni");
    }

    /**
     * Memtodo para volver a la clase anterior (LogIn)
     * @param v
     */
    public void salir(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void perfil(View v){
        Intent i = new Intent(this, Perfil.class);
        i.putExtra("dni", dni);
        i.putExtra("clase", "cursosgestores");
        startActivity(i);
        finish();
    }
}