package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

/**
 *
 * Clase para mostrar la puntuacion del alumno en los test realizados
 *
 */
public class notasAlumnos extends AppCompatActivity {

    FuncionesVarias fv = new FuncionesVarias();
    String dni;
    EditText ET_dni;
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
    TextView tv_nota1;
    TextView tv_nota2;
    TextView tv_nota3;
    TextView tv_nota4;
    TextView tv_nota5;
    TextView tv_nota6;
    TextView tv_nota7;
    TextView tv_nota8;
    TextView tv_nota9;
    TextView tv_nota10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_alumnos);
        dni = getIntent().getStringExtra("dni");
        ET_dni = findViewById(R.id.dni_PA);
        ET_dni.setText(dni);

        // TextView de los nombre de los cursos
        tv_curso1 = findViewById(R.id.tv_nombre1);
        tv_curso2 = findViewById(R.id.tv_nombre2);
        tv_curso3 = findViewById(R.id.tv_nombre3);
        tv_curso4 = findViewById(R.id.tv_nombre4);
        tv_curso5 = findViewById(R.id.tv_nombre5);
        tv_curso6 = findViewById(R.id.tv_nombre6);
        tv_curso7 = findViewById(R.id.tv_nombre7);
        tv_curso8 = findViewById(R.id.tv_nombre8);
        tv_curso9 = findViewById(R.id.tv_nombre9);
        tv_curso10 = findViewById(R.id.tv_nombre10);

        // TextView de las notas de los cursos
        tv_nota1 = findViewById(R.id.tv_nota1);
        tv_nota2 = findViewById(R.id.tv_nota2);
        tv_nota3 = findViewById(R.id.tv_nota3);
        tv_nota4 = findViewById(R.id.tv_nota4);
        tv_nota5 = findViewById(R.id.tv_nota5);
        tv_nota6 = findViewById(R.id.tv_nota6);
        tv_nota7 = findViewById(R.id.tv_nota7);
        tv_nota8 = findViewById(R.id.tv_nota8);
        tv_nota9 = findViewById(R.id.tv_nota9);
        tv_nota10 = findViewById(R.id.tv_nota10);

        // Reseteamos los textos a mostrar
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
        tv_nota1.setText("");
        tv_nota2.setText("");
        tv_nota3.setText("");
        tv_nota4.setText("");
        tv_nota5.setText("");
        tv_nota6.setText("");
        tv_nota7.setText("");
        tv_nota8.setText("");
        tv_nota9.setText("");
        tv_nota10.setText("");

        // Reseteamos los Hints a mostrar
        tv_curso1.setHint("");
        tv_curso2.setHint("");
        tv_curso3.setHint("");
        tv_curso4.setHint("");
        tv_curso5.setHint("");
        tv_curso6.setHint("");
        tv_curso7.setHint("");
        tv_curso8.setHint("");
        tv_curso9.setHint("");
        tv_curso10.setHint("");
        tv_nota1.setHint("");
        tv_nota2.setHint("");
        tv_nota3.setHint("");
        tv_nota4.setHint("");
        tv_nota5.setHint("");
        tv_nota6.setHint("");
        tv_nota7.setHint("");
        tv_nota8.setHint("");
        tv_nota9.setHint("");
        tv_nota10.setHint("");
    }

    /**
     * Metodo para volver a la clase anterior
     * @param v
     */
    public void volver(View v){
        Intent i = new Intent(this, cursosAlumnos.class);
        startActivity(i);
        finish();
    }

    public interface ConsultarDatos{
        void onConsultaExitosa(List<String[]> a);
        void onConsultaError(VolleyError ve);
    }

    /**
     * Metodo para obtener la puntuacion de la persona en los diferentes cursos existentes
     * @param v
     */
    public void getMarks(View v){
        String dni_us = this.dni; // Obtenemos el DNI
         if (fv.contieneTexto(dni_us)){
            // Hacemos la peticion para obtener las puntuaciones de los cursos de la persona
             List<String[]> datosCursos = obtenerDatos(new ConsultarDatos(){

                 @Override
                 public void onConsultaExitosa(List<String[]> notasCursos) {

                     Map<String, String> cursoConNota = new HashMap<>();
                     int i = 1;
                    // Añadimos los cursos con sus respectivas notas
                     for (String[] curso : notasCursos) {
                         /*
                         System.out.println(curso[0]);
                         System.out.println(curso[1]);
                         */

                         cursoConNota.put(curso[0], curso[1]);
                     }

                     for (Map.Entry<String, String> entry : cursoConNota.entrySet()) {
                         String key = entry.getKey();
                         // System.out.println("Clave: " + key + " Valor: " + cursoConNota.get(key));
                         switch (i) {
                             case 1:
                                 tv_nota1.setText(key);
                                 tv_curso1.setText(cursoConNota.get(key));
                                 break;
                             case 2:
                                 tv_nota2.setText(key);
                                 tv_curso2.setText(cursoConNota.get(key));
                                 break;
                             case 3:
                                 tv_nota3.setText(key);
                                 tv_curso3.setText(cursoConNota.get(key));
                                 break;
                             case 4:
                                 tv_nota4.setText(key);
                                 tv_curso4.setText(cursoConNota.get(key));
                                 break;
                             case 5:
                                 tv_nota5.setText(key);
                                 tv_curso5.setText(cursoConNota.get(key));
                                 break;
                             case 6:
                                 tv_nota6.setText(key);
                                 tv_curso6.setText(cursoConNota.get(key));
                                 break;
                             case 7:
                                 tv_nota7.setText(key);
                                 tv_curso7.setText(cursoConNota.get(key));
                                 break;
                             case 8:
                                 tv_nota8.setText(key);
                                 tv_curso8.setText(cursoConNota.get(key));
                                 break;
                             case 9:
                                 tv_nota9.setText(key);
                                 tv_curso9.setText(cursoConNota.get(key));
                                 break;
                             case 10:
                                 tv_nota10.setText(key);
                                 tv_curso10.setText(cursoConNota.get(key));
                                 break;
                         }
                         i++;
                     }
                 }

                 @Override
                 public void onConsultaError(VolleyError error) {
                     error.printStackTrace();
                 }
             });
         }else{
             fv.mostrarMensaje(this, "Debe introducir su DNI para mostrar la puntuacion en los cursos. ");
         }
    }

    public List<String[]> obtenerDatos(ConsultarDatos cd){
        String dni_usu = this.dni;

        // url de la API a consulta
        final String URL = "http://" + getString(R.string.ip) + "/tfg/app/API/getMarks.php?dni=" + dni_usu;

        RequestQueue rq = Volley.newRequestQueue(this);

        List<String[]> notasCursos = new ArrayList<String[]>();

        StringRequest sr = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray ja = new JSONArray(response);
                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject json = ja.getJSONObject(i);
                                /*String dniPersona = json.getString("dniPersona");
                                String idCurso = json.getString("idCurso");*/
                                String puntuacion = json.getString("puntuacion");
                                String nombreCurso = json.getString("enunciado");
                                // Creamos un array para guardar los datos
                                String[] datos = new String[2];
                                /*datos[0] = dniPersona;
                                datos[1] = idCurso;*/
                                datos[0] = puntuacion;
                                datos[1] = nombreCurso;
                                // Guardamos los datos del curso en la lista
                                notasCursos.add(datos);
                            }
                            cd.onConsultaExitosa(notasCursos);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                cd.onConsultaError(error);
            }
        });
        // Añadimos la request a la cola
        rq.add(sr);
        return notasCursos;
    }
}