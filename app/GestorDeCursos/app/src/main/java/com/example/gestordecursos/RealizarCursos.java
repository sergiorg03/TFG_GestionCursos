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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RealizarCursos extends AppCompatActivity {


    FuncionesVarias fv = new FuncionesVarias();
    String dni;
    String nombreClase;
    Class claseAnterior;
    String id_curso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_cursos);

        dni = getIntent().getStringExtra("dni");
        nombreClase = getIntent().getStringExtra("clase");
        claseAnterior = fv.obtenerClase(nombreClase);
        id_curso = getIntent().getStringExtra("idCurso");

        cargarPreguntas();
    }

    public void volver(View v){
        Intent i = new Intent(this, claseAnterior);
        i.putExtra("dni", dni);
        startActivity(i);
        finish();
    }

    /**
     * Metodo que comprueba la puntuación obtenida al realizar el curso
     * @param v
     */
    public void comprobarRespuestas(View v){
        fv.mostrarMensaje(this, "Comprobar respuestas. ");
        // Llamar metodo addMarks();
    }

    public interface ConsultarDatos{
        void onConsultaExitosa(Map<String[], List<String[]>> preguntas);
        void onConsultaError(VolleyError e);
    }

    public void cargarPreguntas(){
        // Preguntas --> pregunta[id_pregunta, enunciado_preguntas] : [id_respuesta, opcion_respuesta, esCorrecta_respuesta]
        Map<String[], List<String[]>> preguntas = getPreguntas(new ConsultarDatos() {
            @Override
            public void onConsultaExitosa(Map<String[], List<String[]>> preguntas) {

            }

            @Override
            public void onConsultaError(VolleyError e) {

            }
        });
    }
    /*public void mostrarTodosCursos(){

        List<String []> cursos = getCursos(new cursosAlumnos.ConsultarDatos() {

            @Override
            public void onConsultaExitosa(List<String[]> listaCursos) {

                Map<String, String[]> todoDatosCurso = new HashMap<>();
                int i = 1;
                // Añadimos los cursos con sus respectivas notas
                for (String[] curso : listaCursos) {

                    String [] datosCurso = new String[]{curso[1], curso[2]};
                    todoDatosCurso.put(curso[0], datosCurso);
                }

                for (Map.Entry<String, String[]> entry : todoDatosCurso.entrySet()) {
                    String key = entry.getKey();
                    switch (i) {
                        case 1:
                            id_curso1 = key;
                            tv_curso1.setText(todoDatosCurso.get(key)[0]);
                            ruta_pdf_curso1 = todoDatosCurso.get(key)[1];
                            System.out.println("Id: "+ id_curso1);
                            System.out.println("Nombre curso: "+ tv_curso1.getText().toString());
                            System.out.println("ruta: "+ ruta_pdf_curso1);
                            break;
                        case 2:
                            id_curso2 = key;
                            tv_curso2.setText(todoDatosCurso.get(key)[0]);
                            ruta_pdf_curso2 = todoDatosCurso.get(key)[1];
                            break;

                    }
                    i++;
                }
            }

            @Override
            public void onConsultaError(VolleyError ve) {
                fv.mostrarMensaje(cursosAlumnos.this, "No se pudieron recopilar datos. ");
            }
        });
    }*/

    public Map<String[], List<String[]>> getPreguntas(ConsultarDatos cd){

        final String URL = "http://"+getString(R.string.ip)+"/tfg/app/API/getTest.php?idCurso="+id_curso;

        RequestQueue rq = Volley.newRequestQueue(this);

        Map<String[], List<String[]>> test = new HashMap();

        StringRequest sr = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray ja = new JSONArray(response);

                            for (int i = 0; i < ja.length(); i = i+2) {
                                JSONArray ja_preguntas = ja.getJSONArray(i);
                                JSONArray ja_opciones = ja.getJSONArray(i+1);

                                String [] pregunta = new String[2];
                                for (int j = 0; j < ja_preguntas.length(); j++) {
                                    JSONObject json = ja_preguntas.getJSONObject(j);

                                    pregunta[0] = json.getString("id_pregunta");
                                    pregunta[1] = json.getString("enunciado_preguntas");
                                }

                                List<String[]> Lista_opciones = new ArrayList<>();
                                for (int j = 0; j < ja_opciones.length(); j++) {
                                    JSONObject json = ja_opciones.getJSONObject(j);
                                    String [] opciones = new String[3];
                                    opciones[0] = json.getString("id_respuesta");
                                    opciones[1] = json.getString("opcion_respuesta");
                                    opciones[2] = json.getString("esCorrecta_respuesta");
                                    Lista_opciones.add(opciones);
                                }
                                test.put(pregunta, Lista_opciones);
                            }

                            // Añadimos el mapa con los valores
                            cd.onConsultaExitosa(test);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        cd.onConsultaError(error);
                    }
                }
        );

        // Añadimos la request a la cola
        rq.add(sr);

        return test;
    }
}