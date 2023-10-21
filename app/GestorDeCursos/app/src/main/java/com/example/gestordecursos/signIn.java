package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.List;

public class signIn extends AppCompatActivity {

    Spinner perfiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ArrayAdapter<String> contenidoSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.perfiles));
        perfiles = findViewById(R.id.perfil);
        perfiles.setAdapter(contenidoSpinner);
    }

    /**
     * Boton para volver a la pantalla de login
     * @param v -- View que se le pasa para finalizar la activity actual
     */
    public void volver(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}