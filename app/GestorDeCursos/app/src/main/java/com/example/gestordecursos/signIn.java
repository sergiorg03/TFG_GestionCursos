package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class signIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
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