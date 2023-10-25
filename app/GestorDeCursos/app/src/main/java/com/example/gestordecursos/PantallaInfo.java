package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PantallaInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_info);
    }

    /**
     * Metodo para volver a la clase anterior
     */
    public void volver(View v){
        System.out.println(v.toString());
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}