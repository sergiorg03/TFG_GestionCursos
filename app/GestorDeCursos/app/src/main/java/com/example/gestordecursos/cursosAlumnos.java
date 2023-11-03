package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 *
 * Clase para mostrar los cursos que los alumnos pueden realizar
 *
 *
 */
public class cursosAlumnos extends AppCompatActivity {

    // Variables
    FuncionesVarias fv = new FuncionesVarias();
    String dni;

    // TextViews
    TextView tv_curso1;
    TextView tv_curso2;
    TextView tv_curso3;
    TextView tv_curso4;
    TextView tv_curso5;
    TextView tv_curso6;
    TextView tv_curso7;
    TextView tv_curso8;
    TextView tv_curso9;
    TextView tv_curso10;
    TextView tv_curso11;
    TextView tv_curso12;
    TextView tv_curso13;
    TextView tv_curso14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos_alumnos);
        dni = getIntent().getStringExtra("dni");

        tv_curso1  = findViewById(R.id.tv_curso1);
        tv_curso2  = findViewById(R.id.tv_curso2);
        tv_curso3  = findViewById(R.id.tv_curso3);
        tv_curso4  = findViewById(R.id.tv_curso4);
        tv_curso5  = findViewById(R.id.tv_curso5);
        tv_curso6  = findViewById(R.id.tv_curso6);
        tv_curso7  = findViewById(R.id.tv_curso7);
        tv_curso8  = findViewById(R.id.tv_curso8);
        tv_curso9  = findViewById(R.id.tv_curso9);
        tv_curso10 = findViewById(R.id.tv_curso10);
        tv_curso11 = findViewById(R.id.tv_curso11);
        tv_curso12 = findViewById(R.id.tv_curso12);
        tv_curso13 = findViewById(R.id.tv_curso13);
        tv_curso14 = findViewById(R.id.tv_curso14);

        // asignamos el texto
        tv_curso1.setText("");
        tv_curso2.setText("");
        tv_curso3.setText("");
        tv_curso4.setText("");
        tv_curso5.setText("");
        tv_curso6.setText("");
        tv_curso7.setText("");
        tv_curso8.setText("");
        tv_curso9.setText("");
        tv_curso10.setText("");
        tv_curso11.setText("");
        tv_curso12.setText("");
        tv_curso13.setText("");
        tv_curso14.setText("");

        setListeners();
    }

    /**
     * Funcion para salir de la pantalla a la pantalla de LogIn
     * @param v
     */
    public void salir(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    /**
     * Metodo que me lleva a la clase para poder ver las notas de cada curso
     * @param v
     */
    public void verNotas(View v){
        Intent i = new Intent(this, notasAlumnos.class);
        i.putExtra("dni", dni);
        startActivity(i);
        finish();
    }

    /**
     * Metodo para ir a la clase de perfil
     * @param v
     */
    public void perfil(View v){
        Intent i = new Intent(this, Perfil.class);
        i.putExtra("dni", dni);
        i.putExtra("clase", "cursosAlumnos");
        startActivity(i);
        finish();
    }

    public void verCurso(View v){

    }

    public void setListeners(){
        tv_curso1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tv_curso2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tv_curso3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tv_curso4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tv_curso5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tv_curso6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tv_curso7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tv_curso8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tv_curso9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tv_curso10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tv_curso11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tv_curso12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tv_curso13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tv_curso14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}