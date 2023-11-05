package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
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

public class RealizarCursos extends AppCompatActivity {


    FuncionesVarias fv = new FuncionesVarias();
    String dni;
    String nombreClase;
    Class claseAnterior;
    String id_curso;
    TextView pregunta1;
    TextView pregunta2;
    TextView pregunta3;
    TextView pregunta4;
    TextView pregunta5;
    TextView pregunta6;
    TextView pregunta7;
    TextView pregunta8;
    TextView pregunta9;
    TextView pregunta10;
    // Opciones pregunta 1
    RadioButton op1_p1;
    RadioButton op2_p1;
    RadioButton op3_p1;
    // Opciones pregunta 2
    RadioButton op1_p2;
    RadioButton op2_p2;
    RadioButton op3_p2;
    // Opciones pregunta 3
    RadioButton op1_p3;
    RadioButton op2_p3;
    RadioButton op3_p3;
    // Opciones pregunta 4
    RadioButton op1_p4;
    RadioButton op2_p4;
    RadioButton op3_p4;
    // Opciones pregunta 5
    RadioButton op1_p5;
    RadioButton op2_p5;
    RadioButton op3_p5;
    // Opciones pregunta 6
    RadioButton op1_p6;
    RadioButton op2_p6;
    RadioButton op3_p6;
    // Opciones pregunta 7
    RadioButton op1_p7;
    RadioButton op2_p7;
    RadioButton op3_p7;
    // Opciones pregunta 8
    RadioButton op1_p8;
    RadioButton op2_p8;
    RadioButton op3_p8;
    // Opciones pregunta 9
    RadioButton op1_p9;
    RadioButton op2_p9;
    RadioButton op3_p9;
    // Opciones pregunta 10
    RadioButton op1_p10;
    RadioButton op2_p10;
    RadioButton op3_p10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_cursos);

        dni = getIntent().getStringExtra("dni");
        nombreClase = getIntent().getStringExtra("clase");
        claseAnterior = fv.obtenerClase(nombreClase);
        id_curso = getIntent().getStringExtra("idCurso");

        // Asignamos los TextView de la pregunta a su recurso correspodiente
        pregunta1   = findViewById(R.id.pregunta1);
        pregunta2   = findViewById(R.id.pregunta2);
        pregunta3   = findViewById(R.id.pregunta3);
        pregunta4   = findViewById(R.id.pregunta4);
        pregunta5   = findViewById(R.id.pregunta5);
        pregunta6   = findViewById(R.id.pregunta6);
        pregunta7   = findViewById(R.id.pregunta7);
        pregunta8   = findViewById(R.id.pregunta8);
        pregunta9   = findViewById(R.id.pregunta9);
        pregunta10  = findViewById(R.id.pregunta10);

        // Asignamos los RadioButton a su recurso correspondiente
        op1_p1  = findViewById(R.id.op1_p1);
        op2_p1  = findViewById(R.id.op2_p1);
        op3_p1  = findViewById(R.id.op3_p1);
        op1_p2  = findViewById(R.id.op1_p2);
        op2_p2  = findViewById(R.id.op2_p2);
        op3_p2  = findViewById(R.id.op3_p2);
        op1_p3  = findViewById(R.id.op1_p3);
        op2_p3  = findViewById(R.id.op2_p3);
        op3_p3  = findViewById(R.id.op3_p3);
        op1_p4  = findViewById(R.id.op1_p4);
        op2_p4  = findViewById(R.id.op2_p4);
        op3_p4  = findViewById(R.id.op3_p4);
        op1_p5  = findViewById(R.id.op1_p5);
        op2_p5  = findViewById(R.id.op2_p5);
        op3_p5  = findViewById(R.id.op3_p5);
        op1_p6  = findViewById(R.id.op1_p6);
        op2_p6  = findViewById(R.id.op2_p6);
        op3_p6  = findViewById(R.id.op3_p6);
        op1_p7  = findViewById(R.id.op1_p7);
        op2_p7  = findViewById(R.id.op2_p7);
        op3_p7  = findViewById(R.id.op3_p7);
        op1_p8  = findViewById(R.id.op1_p8);
        op2_p8  = findViewById(R.id.op2_p8);
        op3_p8  = findViewById(R.id.op3_p8);
        op1_p9  = findViewById(R.id.op1_p9);
        op2_p9  = findViewById(R.id.op2_p9);
        op3_p9  = findViewById(R.id.op3_p9);
        op1_p10 = findViewById(R.id.op1_p10);
        op2_p10 = findViewById(R.id.op2_p10);
        op3_p10 = findViewById(R.id.op3_p10);

        // Mostramos las preguntas
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

                for (String[] test: preguntas.keySet()) {
                    System.out.println("Clave: " + test[0]+test[1] + " Valor: " + preguntas.get(test));

                    int id_preg = Integer.parseInt(test[0]);
                    String enunciado_preg = test[1];
                    System.out.println("Pregunta: ");
                    System.out.println("id--> "+ id_preg);
                    System.out.println("enunciado--> "+ enunciado_preg);

                    System.out.println("Opciones: ");
                    // Opcion 1
                    System.out.println("Opcion 1-->");
                    String id_op1 = preguntas.get(test).get(0)[0];
                    String enun_op1 = preguntas.get(test).get(0)[1];
                    String esCorrecta_op1 = preguntas.get(test).get(0)[2];
                    System.out.println("id_op1-->"+ id_op1);
                    System.out.println("enun_op1-->"+ enun_op1);
                    System.out.println("esCorrecta_op1-->"+ esCorrecta_op1);
                    // Opcion 2
                    System.out.println("Opcion 2-->");
                    String id_op2 = preguntas.get(test).get(1)[0];
                    String enun_op2 = preguntas.get(test).get(1)[1];
                    String esCorrecta_op2 = preguntas.get(test).get(1)[2];
                    System.out.println("id_op2-->"+ id_op2);
                    System.out.println("enun_op2-->"+ enun_op2);
                    System.out.println("esCorrecta_op2-->"+ esCorrecta_op2);
                    // Opcion 3
                    System.out.println("Opcion 3-->");
                    String id_op3 = preguntas.get(test).get(2)[0];
                    String enun_op3 = preguntas.get(test).get(2)[1];
                    String esCorrecta_op3 = preguntas.get(test).get(2)[2];
                    System.out.println("id_op3-->"+ id_op3);
                    System.out.println("enun_op3-->"+ enun_op3);
                    System.out.println("esCorrecta_op3-->"+ esCorrecta_op3);

                    switch (id_preg){
                        case 1:
                            pregunta1.setText(enunciado_preg);
                            op1_p1.setText(enun_op1);
                            op2_p1.setText(enun_op2);
                            op3_p1.setText(enun_op3);
                            break;
                        case 2:
                            pregunta2.setText(enunciado_preg);
                            op1_p2.setText(enun_op1);
                            op2_p2.setText(enun_op2);
                            op3_p2.setText(enun_op3);
                            break;
                        case 3:
                            pregunta3.setText(enunciado_preg);
                            op1_p3.setText(enun_op1);
                            op2_p3.setText(enun_op2);
                            op3_p3.setText(enun_op3);
                            break;
                        case 4:
                            pregunta4.setText(enunciado_preg);
                            op1_p4.setText(enun_op1);
                            op2_p4.setText(enun_op2);
                            op3_p4.setText(enun_op3);
                            break;
                        case 5:
                            pregunta5.setText(enunciado_preg);
                            op1_p5.setText(enun_op1);
                            op2_p5.setText(enun_op2);
                            op3_p5.setText(enun_op3);
                            break;
                        case 6:
                            pregunta6.setText(enunciado_preg);
                            op1_p6.setText(enun_op1);
                            op2_p6.setText(enun_op2);
                            op3_p6.setText(enun_op3);
                            break;
                        case 7:
                            pregunta7.setText(enunciado_preg);
                            op1_p7.setText(enun_op1);
                            op2_p7.setText(enun_op2);
                            op3_p7.setText(enun_op3);
                            break;
                        case 8:
                            pregunta8.setText(enunciado_preg);
                            op1_p8.setText(enun_op1);
                            op2_p8.setText(enun_op2);
                            op3_p8.setText(enun_op3);
                            break;
                        case 9:
                            pregunta9.setText(enunciado_preg);
                            op1_p9.setText(enun_op1);
                            op2_p9.setText(enun_op2);
                            op3_p9.setText(enun_op3);
                            break;
                        case 10:
                            pregunta10.setText(enunciado_preg);
                            op1_p10.setText(enun_op1);
                            op2_p10.setText(enun_op2);
                            op3_p10.setText(enun_op3);
                            break;
                    }
                }
            }

            @Override
            public void onConsultaError(VolleyError e) {
                fv.mostrarMensaje(RealizarCursos.this, "No se pudieron obtener tests para este curso. ");
            }
        });
    }

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
                                    /*
                                    System.out.println(" ");
                                    System.out.println(" ");
                                    System.out.println(" ");
                                    System.out.println("------------------ TRAZAS ----------------");
                                    System.out.println("id_pregunta--> "+pregunta[0]);
                                    System.out.println("enunciado_pregunta--> "+pregunta[1]);
                                    System.out.println(" ");
                                    System.out.println(" ");
                                    System.out.println(" ");
                                     */
                                }

                                List<String[]> Lista_opciones = new ArrayList<>();
                                for (int j = 0; j < ja_opciones.length(); j++) {
                                    JSONObject json = ja_opciones.getJSONObject(j);
                                    String [] opciones = new String[3];
                                    opciones[0] = json.getString("id_respuesta");
                                    opciones[1] = json.getString("opcion_respuesta");
                                    opciones[2] = json.getString("esCorrecta_respuesta");
                                    Lista_opciones.add(opciones);
                                    /*
                                    System.out.println(" ");
                                    System.out.println(" ");
                                    System.out.println(" ");
                                    System.out.println("------------------ TRAZAS ----------------");
                                    System.out.println("id_respuesta--> "+opciones[0]);
                                    System.out.println("opcionRespuesta--> "+opciones[1]);
                                    System.out.println("esCorrecta--> "+opciones[2]);
                                    System.out.println(" ");
                                    System.out.println(" ");
                                    System.out.println(" ");
                                    */
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