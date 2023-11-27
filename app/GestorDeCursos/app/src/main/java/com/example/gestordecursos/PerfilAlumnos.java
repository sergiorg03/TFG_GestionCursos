package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PerfilAlumnos extends AppCompatActivity {

    FuncionesVarias fv = new FuncionesVarias();
    String dni_g;
    String dni_a;
    String clase;
    Class claseAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_alumnos);

        dni_g = getIntent().getStringExtra("dni_g");
        dni_a = getIntent().getStringExtra("dni_a");
        clase = getIntent().getStringExtra("clase");
        System.out.println("SRG: PerfilAlumnos: clase: "+clase);
        claseAnterior = fv.obtenerClase(clase);
    }

    /**
     * Metodo para ir a la pantalla anterior (cursosGestores)
     * @param v
     */
    public void volver(View v){
        Intent i = new Intent(this, claseAnterior);
        i.putExtra("dni", dni_g);
        i.putExtra("clase", "cursosGestores");
        startActivity(i);
        finish();
    }


}