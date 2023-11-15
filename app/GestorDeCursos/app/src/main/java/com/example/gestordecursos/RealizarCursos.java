package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    // Variables de la clase
    FuncionesVarias fv = new FuncionesVarias();
    String dni;
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

    // RadioGroups
    RadioGroup grupo1;
    RadioGroup grupo2;
    RadioGroup grupo3;
    RadioGroup grupo4;
    RadioGroup grupo5;
    RadioGroup grupo6;
    RadioGroup grupo7;
    RadioGroup grupo8;
    RadioGroup grupo9;
    RadioGroup grupo10;

    // Opciones correctas de cada pregunta
    int bd_opCor_p1;
    int bd_opCor_p2;
    int bd_opCor_p3;
    int bd_opCor_p4;
    int bd_opCor_p5;
    int bd_opCor_p6;
    int bd_opCor_p7;
    int bd_opCor_p8;
    int bd_opCor_p9;
    int bd_opCor_p10;

    int opSelec_p1;
    int opSelec_p2;
    int opSelec_p3;
    int opSelec_p4;
    int opSelec_p5;
    int opSelec_p6;
    int opSelec_p7;
    int opSelec_p8;
    int opSelec_p9;
    int opSelec_p10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_cursos);

        dni = getIntent().getStringExtra("dni");
        id_curso = getIntent().getStringExtra("idCurso");

        inicializarVars();
    }

    /**
     * Metodo para inicializar variables
     */
    public void inicializarVars(){
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

        grupo1  = findViewById(R.id.grupo1);
        grupo2  = findViewById(R.id.grupo2);
        grupo3  = findViewById(R.id.grupo3);
        grupo4  = findViewById(R.id.grupo4);
        grupo5  = findViewById(R.id.grupo5);
        grupo6  = findViewById(R.id.grupo6);
        grupo7  = findViewById(R.id.grupo7);
        grupo8  = findViewById(R.id.grupo8);
        grupo9  = findViewById(R.id.grupo9);
        grupo10 = findViewById(R.id.grupo10);

        // Mostramos las preguntas
        cargarPreguntas();

        bd_opCor_p1     = -1;
        bd_opCor_p2     = -1;
        bd_opCor_p3     = -1;
        bd_opCor_p4     = -1;
        bd_opCor_p5     = -1;
        bd_opCor_p6     = -1;
        bd_opCor_p7     = -1;
        bd_opCor_p8     = -1;
        bd_opCor_p9     = -1;
        bd_opCor_p10    = -1;

        opSelec_p1  = -1;
        opSelec_p2  = -1;
        opSelec_p3  = -1;
        opSelec_p4  = -1;
        opSelec_p5  = -1;
        opSelec_p6  = -1;
        opSelec_p7  = -1;
        opSelec_p8  = -1;
        opSelec_p9  = -1;
        opSelec_p10 = -1;
    }

    /**
     * Metodo para volver a la clase anterior
     * @param v
     */
    public void volver(View v){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("dni", dni);
        startActivity(i);
        finish();
    }

    /**
     * Metodo que comprueba la puntuación obtenida al realizar el curso
     * @param v
     */
    public void comprobarRespuestas(View v){
        //fv.mostrarMensaje(this, "Comprobar respuestas. ");
        getSelectedItem();
        int nota = calcularNotas();
        fv.mostrarMensaje(this, "Su puntuació ha sido: "+nota);
        addMarks(String.valueOf(nota));
    }

    public interface ConsultarDatos{
        void onConsultaExitosa(Map<String[], List<String[]>> preguntas);
        void onConsultaError(VolleyError e);
    }

    /**
     * Metodo para mostrar las preguntas del test
     */
    public void cargarPreguntas(){
        // Preguntas --> pregunta[id_pregunta, enunciado_preguntas] : [id_respuesta, opcion_respuesta, esCorrecta_respuesta]
        Map<String[], List<String[]>> preguntas = getPreguntas(new ConsultarDatos() {
            @Override
            public void onConsultaExitosa(Map<String[], List<String[]>> preguntas) {

                for (String[] test: preguntas.keySet()) {
                    //System.out.println("Clave: " + test[0]+test[1] + " Valor: " + preguntas.get(test));

                    int id_preg = Integer.parseInt(test[0]);
                    String enunciado_preg = test[1];

                    // Opcion 1
                    String id_op1 = preguntas.get(test).get(0)[0];
                    String enun_op1 = preguntas.get(test).get(0)[1];
                    String esCorrecta_op1 = preguntas.get(test).get(0)[2];
                    // Opcion 2
                    String id_op2 = preguntas.get(test).get(1)[0];
                    String enun_op2 = preguntas.get(test).get(1)[1];
                    String esCorrecta_op2 = preguntas.get(test).get(1)[2];
                    // Opcion 3
                    String id_op3 = preguntas.get(test).get(2)[0];
                    String enun_op3 = preguntas.get(test).get(2)[1];
                    String esCorrecta_op3 = preguntas.get(test).get(2)[2];

                    /**
                     * Trazas
                     */
                    /*System.out.println("Pregunta: ");
                    System.out.println("id--> "+ id_preg);
                    System.out.println("enunciado--> "+ enunciado_preg);
                    System.out.println("Opciones: ");
                    // Opcion 1
                    System.out.println("Opcion 1-->");
                    System.out.println("id_op1-->"+ id_op1);
                    System.out.println("enun_op1-->"+ enun_op1);
                    System.out.println("esCorrecta_op1-->"+ esCorrecta_op1);
                    // Opcion 2
                    System.out.println("Opcion 2-->");
                    System.out.println("id_op2-->"+ id_op2);
                    System.out.println("enun_op2-->"+ enun_op2);
                    System.out.println("esCorrecta_op2-->"+ esCorrecta_op2);
                    // Opcion 3
                    System.out.println("Opcion 3-->");
                    System.out.println("id_op3-->"+ id_op3);
                    System.out.println("enun_op3-->"+ enun_op3);
                    System.out.println("esCorrecta_op3-->"+ esCorrecta_op3);*/

                    switch (id_preg){
                        case 1:
                            pregunta1.setText(enunciado_preg);
                            op1_p1.setText(enun_op1);
                            op2_p1.setText(enun_op2);
                            op3_p1.setText(enun_op3);
                            bd_opCor_p1 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
                            break;
                        case 2:
                            pregunta2.setText(enunciado_preg);
                            op1_p2.setText(enun_op1);
                            op2_p2.setText(enun_op2);
                            op3_p2.setText(enun_op3);
                            bd_opCor_p2 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
                            break;
                        case 3:
                            pregunta3.setText(enunciado_preg);
                            op1_p3.setText(enun_op1);
                            op2_p3.setText(enun_op2);
                            op3_p3.setText(enun_op3);
                            bd_opCor_p3 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
                            break;
                        case 4:
                            pregunta4.setText(enunciado_preg);
                            op1_p4.setText(enun_op1);
                            op2_p4.setText(enun_op2);
                            op3_p4.setText(enun_op3);
                            bd_opCor_p4 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
                            break;
                        case 5:
                            pregunta5.setText(enunciado_preg);
                            op1_p5.setText(enun_op1);
                            op2_p5.setText(enun_op2);
                            op3_p5.setText(enun_op3);
                            bd_opCor_p5 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
                            break;
                        case 6:
                            pregunta6.setText(enunciado_preg);
                            op1_p6.setText(enun_op1);
                            op2_p6.setText(enun_op2);
                            op3_p6.setText(enun_op3);
                            bd_opCor_p6 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
                            break;
                        case 7:
                            pregunta7.setText(enunciado_preg);
                            op1_p7.setText(enun_op1);
                            op2_p7.setText(enun_op2);
                            op3_p7.setText(enun_op3);
                            bd_opCor_p7 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
                            break;
                        case 8:
                            pregunta8.setText(enunciado_preg);
                            op1_p8.setText(enun_op1);
                            op2_p8.setText(enun_op2);
                            op3_p8.setText(enun_op3);
                            bd_opCor_p8 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
                            break;
                        case 9:
                            pregunta9.setText(enunciado_preg);
                            op1_p9.setText(enun_op1);
                            op2_p9.setText(enun_op2);
                            op3_p9.setText(enun_op3);
                            bd_opCor_p9 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
                            break;
                        case 10:
                            pregunta10.setText(enunciado_preg);
                            op1_p10.setText(enun_op1);
                            op2_p10.setText(enun_op2);
                            op3_p10.setText(enun_op3);
                            bd_opCor_p10 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
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

    /**
     * Metodo para asignar la opcion correcta
     * @param op1
     * @param op2
     * @param op3
     * @return -- Opcion correcta
     */
    public int opcionCorrecta(String op1, String op2, String op3){
        int respuesta = 0;
        if (op1.equalsIgnoreCase("1")){
            respuesta = 1;
        }else{
            if (op2.equalsIgnoreCase("1")){
                respuesta = 2;
            }else {
                if (op3.equalsIgnoreCase("1")){
                    respuesta = 3;
                }
            }
        }
        System.out.println(respuesta);
        return respuesta;
    }

    /**
     * Metodo para obtener las preguntas
     * @param cd -- ConsultarDatos
     * @return --
     */
    public Map<String[], List<String[]>> getPreguntas(ConsultarDatos cd){

        // final String URL = "http://"+getString(R.string.ip)+"/tfg/app/API/getTest.php?idCurso="+id_curso;
        final String URL = fv.getURL()+"getTest.php?idCurso="+id_curso;

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

    /**
     * Metodo que obtiene la opcion seleccionada por el usuario
     */
    public void getSelectedItem(){

        if (grupo1.getCheckedRadioButtonId() == op1_p1.getId()){
            opSelec_p1 = 1;
        }else{
            if (grupo1.getCheckedRadioButtonId() == op2_p1.getId()){
                opSelec_p1 = 2;
            }else{
                if (grupo1.getCheckedRadioButtonId() == op3_p1.getId()){
                    opSelec_p1 = 3;
                }
            }
        }
        // System.out.println("Metodo getSelectecItem opSelec_p1-->"+ opSelec_p1);

        if (grupo2.getCheckedRadioButtonId() == op1_p2.getId()){
            opSelec_p2 = 1;
        }else{
            if (grupo2.getCheckedRadioButtonId() == op2_p2.getId()){
                opSelec_p2 = 2;
            }else{
                if (grupo2.getCheckedRadioButtonId() == op3_p2.getId()){
                    opSelec_p2 = 3;
                }
            }
        }

        if (grupo3.getCheckedRadioButtonId() == op1_p3.getId()){
            opSelec_p3 = 1;
        }else{
            if (grupo3.getCheckedRadioButtonId() == op2_p3.getId()){
                opSelec_p3 = 2;
            }else{
                if (grupo3.getCheckedRadioButtonId() == op3_p3.getId()){
                    opSelec_p3 = 3;
                }
            }
        }

        if (grupo4.getCheckedRadioButtonId() == op1_p4.getId()){
            opSelec_p4 = 1;
        }else{
            if (grupo4.getCheckedRadioButtonId() == op2_p4.getId()){
                opSelec_p4 = 2;
            }else{
                if (grupo4.getCheckedRadioButtonId() == op3_p4.getId()){
                    opSelec_p4 = 3;
                }
            }
        }

        if (grupo5.getCheckedRadioButtonId() == op1_p5.getId()){
            opSelec_p5 = 1;
        }else{
            if (grupo5.getCheckedRadioButtonId() == op2_p5.getId()){
                opSelec_p5 = 2;
            }else{
                if (grupo5.getCheckedRadioButtonId() == op3_p5.getId()){
                    opSelec_p5 = 3;
                }
            }
        }

        if (grupo6.getCheckedRadioButtonId() == op1_p6.getId()){
            opSelec_p6 = 1;
        }else{
            if (grupo6.getCheckedRadioButtonId() == op2_p6.getId()){
                opSelec_p6 = 2;
            }else{
                if (grupo6.getCheckedRadioButtonId() == op3_p6.getId()){
                    opSelec_p6 = 3;
                }
            }
        }

        if (grupo7.getCheckedRadioButtonId() == op1_p7.getId()){
            opSelec_p7 = 1;
        }else{
            if (grupo7.getCheckedRadioButtonId() == op2_p7.getId()){
                opSelec_p7 = 2;
            }else{
                if (grupo7.getCheckedRadioButtonId() == op3_p7.getId()){
                    opSelec_p7 = 3;
                }
            }
        }

        if (grupo8.getCheckedRadioButtonId() == op1_p8.getId()){
            opSelec_p8 = 1;
        }else{
            if (grupo8.getCheckedRadioButtonId() == op2_p8.getId()){
                opSelec_p8 = 2;
            }else{
                if (grupo8.getCheckedRadioButtonId() == op3_p8.getId()){
                    opSelec_p8 = 3;
                }
            }
        }

        if (grupo9.getCheckedRadioButtonId() == op1_p9.getId()){
            opSelec_p9 = 1;
        }else{
            if (grupo9.getCheckedRadioButtonId() == op2_p9.getId()){
                opSelec_p9 = 2;
            }else{
                if (grupo9.getCheckedRadioButtonId() == op3_p9.getId()){
                    opSelec_p9 = 3;
                }
            }
        }

        if (grupo10.getCheckedRadioButtonId() == op1_p10.getId()){
            opSelec_p10 = 1;
        }else{
            if (grupo10.getCheckedRadioButtonId() == op2_p10.getId()){
                opSelec_p10 = 2;
            }else{
                if (grupo10.getCheckedRadioButtonId() == op3_p10.getId()){
                    opSelec_p10 = 3;
                }
            }
        }
    }

    /**
     * Metodo utilizado para calcular las notas de los alumnos al realizar el test
     */
    public int calcularNotas(){
        int suma = 0;

        if(bd_opCor_p1 == opSelec_p1) {
            suma = suma + 1;
        }
        if(bd_opCor_p2 == opSelec_p2){
            suma = suma + 1;
        }
        if(bd_opCor_p3 == opSelec_p3){
            suma = suma + 1;
        }
        if(bd_opCor_p4 == opSelec_p4){
            suma = suma + 1;
        }
        if(bd_opCor_p5 == opSelec_p5){
            suma = suma + 1;
        }
        if(bd_opCor_p6 == opSelec_p6){
            suma = suma + 1;
        }
        if(bd_opCor_p7 == opSelec_p7){
            suma = suma + 1;
        }
        if(bd_opCor_p8 == opSelec_p8){
            suma = suma + 1;
        }
        if(bd_opCor_p9 == opSelec_p9){
            suma = suma + 1;
        }
        if(bd_opCor_p10 == opSelec_p10){
            suma = suma + 1;
        }
        /*System.out.println("Metodo calcular notas: ");
        System.out.println(bd_opCor_p1+" --> opSelect: "+opSelec_p1);
        System.out.println(bd_opCor_p2+" --> opSelect: "+opSelec_p2);
        System.out.println(bd_opCor_p3+" --> opSelect: "+opSelec_p3);
        System.out.println(bd_opCor_p4+" --> opSelect: "+opSelec_p4);
        System.out.println(bd_opCor_p5+" --> opSelect: "+opSelec_p5);
        System.out.println(bd_opCor_p6+" --> opSelect: "+opSelec_p6);
        System.out.println(bd_opCor_p7+" --> opSelect: "+opSelec_p7);
        System.out.println(bd_opCor_p8+" --> opSelect: "+opSelec_p8);
        System.out.println(bd_opCor_p9+" --> opSelect: "+opSelec_p9);
        System.out.println(bd_opCor_p10+" --> opSelect: "+opSelec_p10);*/
        return suma;
    }

    /**
     * Metodo para añadir la nota del curso obtenida por el usuario en la base de datos
     * @param puntuacion
     */
    public void addMarks(String puntuacion){

        //final String URL = "http://"+getString(R.string.ip)+"/tfg/app/API/setMarks.php";
        final String URL = fv.getURL()+"setMarks.php";

        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest sr = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        fv.mostrarMensaje(RealizarCursos.this,"Nota guardada correctamente. ");
                        volver(findViewById(R.id.Back).getRootView());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error POST");
                        error.printStackTrace();
                        error.getMessage();
                        updateMarks(puntuacion);

                    }
                }){
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody() {
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("dni", dni);
                    jsonBody.put("id_curso", id_curso);
                    jsonBody.put("puntuacion", puntuacion);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return jsonBody.toString().getBytes();
            }
        };
        // Añadimos la query a la cola
        rq.add(sr);
    }

    /**
     * Metodo para modificar la nota obtenida por el usuario en el curso
     * @param puntuacion
     */
    public void updateMarks(String puntuacion){
        // final String URL = "http://"+getString(R.string.ip)+"/tfg/app/API/setMarks.php";
        final String URL = fv.getURL()+"setMarks.php";

        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest sr = new StringRequest(Request.Method.PUT, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        fv.mostrarMensaje(RealizarCursos.this,"Nota actualizada correctamente. ");
                        volver(findViewById(R.id.Back).getRootView());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error PUT");
                        error.printStackTrace();
                        fv.mostrarMensaje(RealizarCursos.this, "No se pudo  actualizar su nota. ");
                    }
                }){
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody() {
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("dni", dni);
                    jsonBody.put("id_curso", id_curso);
                    jsonBody.put("puntuacion", puntuacion);;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return jsonBody.toString().getBytes();
            }
        };
        // Añadimos la query a la cola
        rq.add(sr);
    }
}