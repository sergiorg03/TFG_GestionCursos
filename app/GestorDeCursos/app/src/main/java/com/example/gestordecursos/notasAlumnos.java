package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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
    ImageButton marks;
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
    String idCurso1;
    String idCurso2;
    String idCurso3;
    String idCurso4;
    String idCurso5;
    String idCurso6;
    String idCurso7;
    String idCurso8;
    String idCurso9;
    String idCurso10;

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

        // Llamamos al método para obtener los cursos nada más entrar en la clase
        marks = findViewById(R.id.marks);
        getMarks(marks.getRootView());
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
                                 idCurso1 = key;
                                 tv_curso1.setText(cursoConNota.get(key));
                                 break;
                             case 2:
                                 idCurso2 = key;
                                 tv_curso2.setText(cursoConNota.get(key));
                                 break;
                             case 3:
                                 idCurso3 = key;
                                 tv_curso3.setText(cursoConNota.get(key));
                                 break;
                             case 4:
                                 idCurso4 = key;
                                 tv_curso4.setText(cursoConNota.get(key));
                                 break;
                             case 5:
                                 idCurso5 = key;
                                 tv_curso5.setText(cursoConNota.get(key));
                                 break;
                             case 6:
                                 idCurso6 = key;
                                 tv_curso6.setText(cursoConNota.get(key));
                                 break;
                             case 7:
                                 idCurso7 = key;
                                 tv_curso7.setText(cursoConNota.get(key));
                                 break;
                             case 8:
                                 idCurso8 = key;
                                 tv_curso8.setText(cursoConNota.get(key));
                                 break;
                             case 9:
                                 idCurso9 = key;
                                 tv_curso9.setText(cursoConNota.get(key));
                                 break;
                             case 10:
                                 idCurso10 = key;
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

    /**
     * Metodo que realiza una llamada a la API para obtener los cursos.
     * @param cd
     * @return
     */
    public List<String[]> obtenerDatos(ConsultarDatos cd){
        String dni_usu = this.dni;

        // url de la API a consulta
        // final String URL = "http://" + getString(R.string.ip) + "/tfg/app/API/getMarks.php?dni=" + dni_usu;
        final String URL = fv.getURL()+"getMarks.php?dni=" + dni_usu.trim();

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
                                String idCurso = json.getString("idCurso");
                                String nombreCurso = json.getString("enunciado");
                                // Creamos un array para guardar los datos
                                String[] datos = new String[2];
                                /*datos[0] = dniPersona;
                                datos[1] = idCurso;*/
                                datos[0] = idCurso;
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

    /**
     * Metodo que dependiendo el boton pulsado nos envia a la pantalla de notas de un curso enviando el ID como parametro
     * @param v -- View del boton pulsado
     */
    public void verNotaCurso(View v){
        switch (v.getId()){
            case R.id.iv_nota1:
                pantallaNotasCurso(idCurso1);
                break;
            case R.id.iv_nota2:
                pantallaNotasCurso(idCurso2);
                break;
            case R.id.iv_nota3:
                pantallaNotasCurso(idCurso3);
                break;
            case R.id.iv_nota4:
                pantallaNotasCurso(idCurso4);
                break;
            case R.id.iv_nota5:
                pantallaNotasCurso(idCurso5);
                break;
            case R.id.iv_nota6:
                pantallaNotasCurso(idCurso6);
                break;
            case R.id.iv_nota7:
                pantallaNotasCurso(idCurso7);
                break;
            case R.id.iv_nota8:
                pantallaNotasCurso(idCurso8);
                break;
            case R.id.iv_nota9:
                pantallaNotasCurso(idCurso9);
                break;
            case R.id.iv_nota10:
                pantallaNotasCurso(idCurso10);
                break;
        }
    }

    /**
     * Metodo que me lleva a la pantalla para ver las notas de un curso
     * @param idCurso -- id del curso a buscar
     */
    public void pantallaNotasCurso(String idCurso){
        Intent i = new Intent(this, pantallaNotasCursos.class);
        i.putExtra("dni", dni);
        i.putExtra("clase", "notasAlumnos");
        i.putExtra("curso", idCurso);
        startActivity(i);
        finish();
    }
}