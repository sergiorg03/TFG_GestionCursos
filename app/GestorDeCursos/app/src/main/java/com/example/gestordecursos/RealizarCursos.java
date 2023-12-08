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
    String clase;
    Class claseAnterior;
    int numeroPreguntasTest;
    int lineasSalto;
    ArrayList<Integer> listaNotas;
    float nota;

    TextView pregunta;
    // Opciones pregunta
    RadioButton op1;
    RadioButton op2;
    RadioButton op3;
    RadioButton op4;


    // RadioGroups
    RadioGroup grupo1;

    // Opciones correctas de cada pregunta
    int bd_opCor;
    // Opcion seleccionada
    int opSelec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_cursos);

        dni = getIntent().getStringExtra("dni");
        id_curso = getIntent().getStringExtra("idCurso");
        clase = getIntent().getStringExtra("clase");
        claseAnterior = fv.obtenerClase(clase);
        inicializarVars();
    }

    /**
     * Metodo para inicializar variables
     */
    public void inicializarVars(){
        // Asignamos los TextView de la pregunta a su recurso correspodiente
        pregunta = findViewById(R.id.pregunta);

        // Asignamos los RadioButton a su recurso correspondiente
        op1  = findViewById(R.id.op1);
        op2  = findViewById(R.id.op2);
        op3  = findViewById(R.id.op3);
        op4  = findViewById(R.id.op4);

        grupo1  = findViewById(R.id.grupo);
        bd_opCor = -1;
        opSelec = -1;

        numeroPreguntasTest = (getIntent().getStringExtra("numeroPreguntas") == null) ? obtenerNumeroPreguntasTest(): Integer.parseInt(getIntent().getStringExtra("numeroPreguntas"));
        lineasSalto = getIntent().getIntExtra("lineasSalto", 0);
        System.out.println("NumeroPreguntasTest: "+ numeroPreguntasTest);
        System.out.println("Lineas a saltar: "+ lineasSalto);
        System.out.println("iniciarVars: devolucion intent: "+ getIntent().getIntegerArrayListExtra("listaNotas"));
        listaNotas = (getIntent().getIntegerArrayListExtra("listaNotas") == null)? new ArrayList<Integer>(): getIntent().getIntegerArrayListExtra("listaNotas");
        nota = -1;

        // Mostramos las preguntas
        cargarPreguntas();
    }

    /**
     * Metodo para volver a la clase anterior
     * @param v
     */
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
        //fv.mostrarMensaje(this, "Comprobar respuestas. ");
        obtenerNotaTest(listaNotas);
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
                try{
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
                        // Opcion 3
                        String id_op4 = preguntas.get(test).get(3)[0];
                        String enun_op4 = preguntas.get(test).get(3)[1];
                        String esCorrecta_op4 = preguntas.get(test).get(3)[2];

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

                        pregunta.setText(enunciado_preg);
                        op1.setText(enun_op1);
                        op2.setText(enun_op2);
                        op3.setText(enun_op3);
                        op4.setText(enun_op4);
                        bd_opCor = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3, esCorrecta_op4);
                    }
                }catch(IndexOutOfBoundsException i){
                    lineasSalto += 1;
                    cargarPreguntas();
                }
            }

            @Override
            public void onConsultaError(VolleyError e) {
                //fv.mostrarMensaje(RealizarCursos.this, "No se pudieron obtener tests para este curso. ");
                pregunta.setText("Fin del test. ");
                op1.setText("N/A");
                op2.setText("N/A");
                op3.setText("N/A");
                op4.setText("N/A");
                findViewById(R.id.nextQuestion).setOnClickListener(null);
                comprobarRespuestas(findViewById(R.id.comprobarRespuestas).getRootView());
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
    public int opcionCorrecta(String op1, String op2, String op3, String op4){
        int respuesta = 0;
        if (op1.equalsIgnoreCase("1")){
            respuesta = 1;
        }else{
            if (op2.equalsIgnoreCase("1")){
                respuesta = 2;
            }else {
                if (op3.equalsIgnoreCase("1")){
                    respuesta = 3;
                }else{
                    respuesta = (op4.equalsIgnoreCase("1"))? 4: 0;
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
        final String URL = fv.getURL()+"getTest.php?idCurso="+id_curso.trim()+"&lineasSalto="+lineasSalto;

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

        if (grupo1.getCheckedRadioButtonId() == op1.getId()){
            opSelec = 1;
        }else{
            if (grupo1.getCheckedRadioButtonId() == op2.getId()){
                opSelec = 2;
            }else{
                if (grupo1.getCheckedRadioButtonId() == op3.getId()){
                    opSelec = 3;
                }else{
                    if (grupo1.getCheckedRadioButtonId() == op4.getId()){
                        opSelec = 4;
                    }
                }
            }
        }
    }

    /**
     * Metodo utilizado para calcular las notas de los alumnos al realizar el test
     */
    public int calcularNotas(){
        int correcto = 0;
        System.out.println("RealizarCursos: calcularNotas: bd_opCor: "+ bd_opCor +" == "+ opSelec);
        if(bd_opCor == opSelec) {
            correcto = correcto + 1;
        }
        System.out.println("RealizarCursos: calcularNotas: correcto: "+ correcto);
        return correcto;
    }

    /**
     * Metodo para añadir la nota del curso obtenida por el usuario en la base de datos
     *
     */
    public void addMarks(){

        //final String URL = "http://"+getString(R.string.ip)+"/tfg/app/API/setMarks.php";
        final String URL = fv.getURL()+"setMarks.php";

        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest sr = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        fv.mostrarMensaje(RealizarCursos.this,"Su nota fue guardada correctamente. ");
                        volver(findViewById(R.id.Back).getRootView());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error POST");
                        error.printStackTrace();
                        error.getMessage();
                        //updateMarks(puntuacion);

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
                    jsonBody.put("puntuacion", nota);
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
     * @param //puntuacion
     */
    /*public void updateMarks(String puntuacion){
        // final String URL = "http://"+getString(R.string.ip)+"/tfg/app/API/setMarks.php";
        final String URL = fv.getURL()+"setMarks.php";

        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest sr = new StringRequest(Request.Method.PUT, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        fv.mostrarMensaje(RealizarCursos.this,"Su nota fue guardada correctamente. ");
                        volver(findViewById(R.id.Back).getRootView());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error PUT");
                        //fv.guardadoLogs(error.toString(), "RealizarCursos_updateMarks");
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
                    jsonBody.put("puntuacion", puntuacion);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return jsonBody.toString().getBytes();
            }
        };
        // Añadimos la query a la cola
        rq.add(sr);
    }*/

    public interface ConsultarDatosNumeroPregs{
        void onConsultaExitosa(int numeroPregs);
        void onConsultaError(VolleyError e);
    }

    public int obtenerNumeroPreguntasTest(){
        int numeroPreguntas = 0;

        getNumPreg(new ConsultarDatosNumeroPregs(){
            @Override
            public void onConsultaExitosa(int numeroPregs) {
                 numeroPreguntasTest = numeroPregs;
            }

            @Override
            public void onConsultaError(VolleyError e) {
                numeroPreguntasTest = 0;
                e.printStackTrace();
            }
        });

        return numeroPreguntas;
    }

    /**
     * Metodo que realiza la peticion a la API para obtener el numero de preguntas que tiene el test
     * @param cd -- Interfaz utilizada para la obtencion del numero de preguntas
     *
     */
    public void getNumPreg(ConsultarDatosNumeroPregs cd){
        final String URL = fv.getURL()+"getNumberOfQuestions.php?id_curso="+id_curso;

        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest sr = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray ja = new JSONArray(response);
                            JSONObject json = ja.getJSONObject(0);
                            System.out.println("RealizarCursos: getNumPreg: "+ json.getString("numeroPreguntas"));
                            cd.onConsultaExitosa(Integer.parseInt(json.getString("numeroPreguntas")));
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
                });

        rq.add(sr);
    }

    /**
     * Metodo que crea la nueva activity mostrando la siguiente pregunta
     * @param numeroPreg
     */
    public void mostrarPregunta(int numeroPreg){
        Intent i = new Intent(this, RealizarCursos.class);
        i.putExtra("dni", dni);
        i.putExtra("idCurso", id_curso);
        i.putExtra("clase", clase);
        i.putExtra("lineasSalto", numeroPreg);
        i.putExtra("numeroPreguntas", String.valueOf(numeroPreguntasTest));
        i.putIntegerArrayListExtra("listaNotas", listaNotas);
        startActivity(i);
        finish();
    }

    /**
     * Metodo que muestra la siguiente pregunta
     * @param v -- Vista del boton pulsado
     */
    public void siguientePregunta_rc(View v){
        System.out.println("lineasSalto: "+lineasSalto);
        getSelectedItem();
        int esCorrecto = calcularNotas();
        if (listaNotas.size() == 0){
            for (int i = 0; i < numeroPreguntasTest; i++) {
                listaNotas.add(0);
            }
        }

        int numeroPreg = lineasSalto+1;
        if(numeroPreg != numeroPreguntasTest+1) {
            listaNotas.set(lineasSalto, esCorrecto);
            mostrarPregunta(numeroPreg);
        }else{
            fv.mostrarMensaje(this, "Ha completado el curso correctamente. ");
            obtenerNotaTest(listaNotas);
        }
    }

    /**
     * Metodo que muestra la pregunta anterior
     * @param v -- View del boton pulsado
     */
    public void anteriorPregunta_rc(View v){
        int numeroPreg = (lineasSalto-1 <= 0)?0 : lineasSalto-1 ;

        mostrarPregunta(numeroPreg);

    }

    public void obtenerNotaTest(ArrayList<Integer> notas){
        int sumaNotas = 0;
        for (int i = 0; i < notas.size(); i++) {
            if (notas.get(i) == 1){
                sumaNotas = sumaNotas+1;
            }
        }
        System.out.println("RealizarCursos: obtenerNotaTest: numeroPreguntasTest: "+numeroPreguntasTest);
        if (numeroPreguntasTest != 0) {
            int resultado = ((10 * sumaNotas) / numeroPreguntasTest);

            nota = (Math.round(resultado * 100) / 100);
            System.out.println("RealizarCursos: ObtenerRespuestas: nota: " + nota);
            addMarks();
        }else{
            fv.mostrarMensaje(this, "Se produjo un error. Vuelva a intentarlo más tarde. ");
        }
    }
}