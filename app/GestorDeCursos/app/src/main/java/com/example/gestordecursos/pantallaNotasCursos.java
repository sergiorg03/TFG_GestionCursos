package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para mostrar las notas obtenidas por un alumno
 */
public class pantallaNotasCursos extends AppCompatActivity {

    FuncionesVarias fv = new FuncionesVarias();
    String dni;
    String curso;
    String clase;
    Class claseAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_notas_cursos);

        dni = getIntent().getStringExtra("dni");
        curso = getIntent().getStringExtra("curso");
        clase = getIntent().getStringExtra("clase");
        claseAnterior = fv.obtenerClase(clase);

        iniciarVars();
    }

    /**
     * Metodo para inicializar las variables
     */
    public void iniciarVars(){
        // textviews


        obtenerDatos();
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

    public interface ConsultarDatos{
        void onConsultaExitosa();
        void onConsultaError(VolleyError e);
    }

    /**
     * Metodo que muestra las notas de los alumnos
     */
    public List<String[]> mostrarDatos(ConsultarDatos cd){
        final String URL = fv.getURL()+"getAllMarksForStudent.php?dni="+dni.trim()+"&id_curso="+curso.trim();

        RequestQueue rq = Volley.newRequestQueue(this);

        List<String[]> lista = new ArrayList<>();

        StringRequest sr = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray ja = new JSONArray(response);

                            for (int i = 0; i < ja.length(); i++) {

                            }
                            /**
                             *
                             * TERMINAR
                             *
                             */
                            cd.onConsultaExitosa();
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                cd.onConsultaError(error);
            }
        });

        rq.add(sr);
        return lista;
    }

    public void obtenerDatos(){
        List<String[]> listaNotas = mostrarDatos(new ConsultarDatos() {
            @Override
            public void onConsultaExitosa() {
                
            }

            @Override
            public void onConsultaError(VolleyError e) {
                fv.mostrarMensaje(pantallaNotasCursos.this, "No se pudieron obtener datos. ");
                e.printStackTrace();
            }
        });
    }
}