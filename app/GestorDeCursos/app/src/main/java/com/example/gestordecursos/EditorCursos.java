package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.AuthFailureError;
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
 * Clase para mostrar los cursos de los gestores (editar)
 *
 */
public class EditorCursos extends AppCompatActivity {

    FuncionesVarias fv = new FuncionesVarias();
    String dni;
    String id_curso;
    String clase;
    Class claseAnterior;
    boolean existe;
    int id_pregunta;

    // Variables XML
    //Preguntas
    EditText et_p1;

    // Opciones
    EditText tv_ec_op1;
    EditText tv_ec_op2;
    EditText tv_ec_op3;
    EditText tv_ec_op4;

    // RadioGroups
    RadioGroup grupo1;

    // Opciones pregunta
    RadioButton op1;
    RadioButton op2;
    RadioButton op3;
    RadioButton op4;

    // Variables para la obtencion de opcion correcta de la BD
    int bd_opCor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_cursos);

        dni = getIntent().getStringExtra("dni");
        id_curso = getIntent().getStringExtra("id_curso");
        clase = getIntent().getStringExtra("clase");
        claseAnterior = fv.obtenerClase(clase);

        inicializarVariables();
    }

    /**
     * Metodo para inicializar las variables de la clase
     */
    public void inicializarVariables(){
        // Variables
        id_pregunta = (getIntent().getIntExtra("id_pregunta", 1));// == null)? 1: Integer.parseInt(getIntent().getStringExtra("id_pregunta"));
        // Preguntas
        et_p1   = findViewById(R.id.tv_ec_pregunta);

        // Opciones
        tv_ec_op1    = findViewById(R.id.tv_ec_op1);
        tv_ec_op2    = findViewById(R.id.tv_ec_op2);
        tv_ec_op3    = findViewById(R.id.tv_ec_op3);
        tv_ec_op4    = findViewById(R.id.tv_ec_op4);
        // Grupos para la eleccion de la respuesta correcta
        grupo1  = findViewById(R.id.ec_grupo1);
        // Asignamos los RadioButton a su rec
        op1  = findViewById(R.id.ec_op1);
        op2  = findViewById(R.id.ec_op2);
        op3  = findViewById(R.id.ec_op3);
        op4  = findViewById(R.id.ec_op4);

        bd_opCor = -1;

        //Mostramos las preguntas
        cargarPreguntas(id_curso);
    }

    /**
     * Metodo set
     * @param id_pregunta -- nuevo id de la pregunta
     */
    public void setId_pregunta(int id_pregunta){
        this.id_pregunta = id_pregunta;
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
     * Metodo para dirigirnos al perfil del usuario
     * @param v
     */
    public void perfil(View v){
        Intent i = new Intent(this, Perfil.class);
        i.putExtra("dni", dni);
        i.putExtra("clase", clase);
        startActivity(i);
        finish();
    }

    public interface ConsultarDatos{
        void onConsultaExitosa(Map<String[], List<String[]>> preguntas);
        void onConsultaError(VolleyError e);
    }

    public void cargarPreguntas(String id_curso){
        Map<String[], List<String[]>> preguntas = getPreguntas(new ConsultarDatos() {
            @Override
            public void onConsultaExitosa(Map<String[], List<String[]>> preguntas) {
                existe = true;
                for (String[] test: preguntas.keySet()) {

                    //Var para obtener el id de la pregunta y el enunciado
                    int id_preg = Integer.parseInt(test[0]);
                    String enunciado_preg = test[1];

                    // Variables para obtener las opciones de las preguntas
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
                    // Opcion 4
                    String id_op4 = preguntas.get(test).get(3)[0];
                    String enun_op4 = preguntas.get(test).get(3)[1];
                    String esCorrecta_op4 = preguntas.get(test).get(3)[2];

                    et_p1.setText(enunciado_preg);
                    tv_ec_op1.setText(enun_op1);
                    tv_ec_op2.setText(enun_op2);
                    tv_ec_op3.setText(enun_op3);
                    tv_ec_op4.setText(enun_op4);
                    op1.setText("Opcion 1");
                    op2.setText("Opcion 2");
                    op3.setText("Opcion 3");
                    op4.setText("Opcion 4");
                    bd_opCor = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3, esCorrecta_op4);
                    System.out.println("Opcion correcta: "+bd_opCor);
                    seleccionarOpcionCor(bd_opCor, op1, op2, op3, op4);
                }
            }

            @Override
            public void onConsultaError(VolleyError e) {
                //fv.mostrarMensaje(EditorCursos.this, "No se pudieron recopilar datos. ");
                existe = false; // No existe el test
                System.out.println("No existe test");
            }
        });
    }

    /**
     * Metodo para mostrar la opcion correcta obtenida de la Base de datos
     * @param opcion -- Opcion correcta
     * @param r1 -- RadioButton de la opcion 1
     * @param r2 -- RadioButton de la opcion 2
     * @param r3 -- RadioButton de la opcion 3
     * @param r4 -- RadioButton de la opcion 4
     */
    public void seleccionarOpcionCor(int opcion, RadioButton r1, RadioButton r2, RadioButton r3, RadioButton r4){
        if (opcion == 1){
            r1.setChecked(true);
            System.out.println("EditorCursos: seleccionarOpcionCor: op1 correcta. ");
        }else if(opcion == 2){
            System.out.println("EditorCursos: seleccionarOpcionCor: op2 correcta. ");
            r2.setChecked(true);
        }else if(opcion == 3) {
            r3.setChecked(true);
            System.out.println("EditorCursos: seleccionarOpcionCor: op3 correcta. ");
        }else if(opcion == 4){
            r4.setChecked(true);
            System.out.println("EditorCursos: seleccionarOpcionCor: op4 correcta. ");
        }
    }

    /**
     * Metodo para obtener las preguntas de la base de datos
     * @param cd
     * @return -- Mapa con las preguntas y sus opciones
     */
    public Map<String[], List<String[]>> getPreguntas(ConsultarDatos cd){

        final String URL = fv.getURL()+"getTest.php?idCurso="+id_curso.trim()+"&lineasSalto="+id_pregunta;

        RequestQueue rq = Volley.newRequestQueue(this);

        Map<String[], List<String[]>> test = new HashMap<>();

        StringRequest sr = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray ja = new JSONArray(response);

                            for (int i = 0; i < ja.length(); i = i + 2) {
                                JSONArray ja_preguntas = ja.getJSONArray(i);
                                JSONArray ja_opciones = ja.getJSONArray(i + 1);

                                String[] pregunta = new String[2];
                                for (int j = 0; j < ja_preguntas.length(); j++) {
                                    JSONObject json = ja_preguntas.getJSONObject(j);

                                    pregunta[0] = json.getString("id_pregunta");
                                    pregunta[1] = json.getString("enunciado_preguntas");

                                }

                                List<String[]> Lista_opciones = new ArrayList<>();
                                for (int j = 0; j < ja_opciones.length(); j++) {
                                    JSONObject json = ja_opciones.getJSONObject(j);
                                    String[] opciones = new String[3];
                                    opciones[0] = json.getString("id_respuesta");
                                    opciones[1] = json.getString("opcion_respuesta");
                                    opciones[2] = json.getString("esCorrecta_respuesta");
                                    Lista_opciones.add(opciones);
                                    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa ");
                                    System.out.println(json.getString("id_respuesta"));
                                    System.out.println(json.getString("opcion_respuesta"));
                                    System.out.println(json.getString("esCorrecta_respuesta"));

                                }
                                test.put(pregunta, Lista_opciones);
                            }

                            // Añadimos el mapa con los valores
                            cd.onConsultaExitosa(test);
                        } catch (JSONException jsone) {
                            jsone.printStackTrace();
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

        // Añadimos la StringRequest a la cola
        rq.add(sr);

        //Devolvemos el test.
        return test;
    }

    /**
     * Metodo para asignar la opcion correcta
     * @param op1
     * @param op2
     * @param op3
     * @return -- Opcion correcta
     */
    public int opcionCorrecta(String op1, String op2, String op3, String op4){
        int respuesta = -1;
        if (op1.equalsIgnoreCase("1")){
            respuesta = 1;
        }else{
            if (op2.equalsIgnoreCase("1")){
                respuesta = 2;
            }else {
                if (op3.equalsIgnoreCase("1")){
                    respuesta = 3;
                }else{
                    if (op4.equalsIgnoreCase("1")){
                        respuesta = 4;
                    }
                }
            }
        }
        System.out.println(respuesta);
        return respuesta;
    }

    /**
     * Metodo para añadir o modificar los tests
     * @param v
     */
    public void add_modif_test(View v){

        // Creamos la pantalla de alerta
        AlertDialog.Builder adb = new AlertDialog.Builder(EditorCursos.this);
        adb.setTitle("Salir de la edicion de cursos. ")
                .setMessage("¿Desea guardar la pregunta de test mostrada? \nSi no, se perderá la pregunta. ")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { // El usuario ha pulsado el boton SI
                        System.out.println("Se guarda la pregunta. ");
                        crearOModifTest();
                        editorCurso();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editorCurso();
                    }
                }).show();

    }

    public void editorCurso(){
        Intent i = new Intent(EditorCursos.this, claseAnterior);
        i.putExtra("dni", dni);
        startActivity(i);
        finish();
    }

    /**
     * Metodo para crear los test
     */
    public void crearTest(){
        //final String URL = "http://"+getString(R.string.ip)+"/tfg/app/API/addTest.php";
        final String URL = fv.getURL()+"addTest.php";

        if (obtenerNuevosDatos() != null) {
            Map<String[], List<String[]>> datosNuevos = obtenerNuevosDatos();

            List<String[]> preguntas = new ArrayList<>();
            for (Map.Entry<String[], List<String[]>> entry : datosNuevos.entrySet()) {
                String[] datos = entry.getKey();
                System.out.println(datos[0] + "-->" + datos[1]);
                preguntas.add(datos);
            }
            for (int i = 0; i < preguntas.size(); i++) {
                System.out.println("EditorCursos: crearTest: " + preguntas.get(i)[0] + " ---------> " + preguntas.get(i)[1]);
            }
            List<String[]> opciones = new ArrayList<>();
            for (List<String[]> valores : datosNuevos.values()) {
                opciones.addAll(valores);
            }

            RequestQueue rq = Volley.newRequestQueue(this);

            StringRequest sr = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            fv.mostrarMensaje(EditorCursos.this, "Se añadio la pregunta al test correctamente. ");
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //fv.guardadoLogs(error.toString(), "editorCursos_crearTest");
                            modificarTest();
                            //fv.mostrarMensaje(EditorCursos.this, "No se pudo insertar el test, pruebe a insertarlo de nuevo en unos minutos. ");
                        }
                    }
            ) {
                public String getBodyContentType() {
                    return "application/json";
                }

                // Metodo getBody
                @Override
                public byte[] getBody() {
                    JSONObject json = new JSONObject();
                    try {
                        json.put("id_curso", id_curso); // Id del curso
                        // datos pregunta
                        json.put("id_pregunta", id_pregunta);
                        json.put("enunciado_pregunta", preguntas.get(0)[1]);

                        // Datos Opciones

                        // Pregunta
                        // Opcion 1
                        json.put("id_op1", opciones.get(0)[0]);
                        json.put("enun_op1", opciones.get(0)[3]);
                        json.put("escor_op1", opciones.get(0)[4]);
                        // Opcion 2
                        json.put("id_op2", opciones.get(1)[0]);
                        json.put("enun_op2", opciones.get(1)[3]);
                        json.put("escor_op2", opciones.get(1)[4]);
                        // Opcion 3
                        json.put("id_op3", opciones.get(2)[0]);
                        json.put("enun_op3", opciones.get(2)[3]);
                        json.put("escor_op3", opciones.get(2)[4]);
                        // Opcion 4
                        json.put("id_op4", opciones.get(3)[0]);
                        json.put("enun_op4", opciones.get(3)[3]);
                        json.put("escor_op4", opciones.get(3)[4]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return json.toString().getBytes();
                }
            };

            // Añadimos la peticion a la cola
            rq.add(sr);
        }
    }


    /**
     * Metodo para modificar el test de un curso
     */
    public void modificarTest(){
        //final String URL = "http://"+getString(R.string.ip)+"/tfg/app/API/updateTest.php";
        final String URL = fv.getURL()+"updateTest.php";

        if (obtenerNuevosDatos() != null) {

            Map<String[], List<String[]>> datosNuevos = obtenerNuevosDatos();

            List<String[]> preguntas = new ArrayList<>(datosNuevos.keySet());
            List<String[]> opciones = new ArrayList<>();
            for (List<String[]> valores : datosNuevos.values()) {
                opciones.addAll(valores);
            }

            /**
             * Trazas
             */
        for (int i = 0; i < preguntas.size(); i++) {
            System.out.println("EditorCursos: modificarTest: preguntas "+ preguntas.get(i)[0] + " ---> "+preguntas.get(i)[1]);
        }
        for (int i = 0; i < opciones.size(); i++) {
            System.out.println("EditorCursos: modificarTest: opciones "+
                                opciones.get(i)[0] + " ---> " +
                                opciones.get(i)[1] + " ---> " +
                                opciones.get(i)[2] + " ---> " +
                                opciones.get(i)[3] + " ---> " +
                                opciones.get(i)[4]);
        }

            RequestQueue rq = Volley.newRequestQueue(this);

            StringRequest sr = new StringRequest(Request.Method.PUT, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            fv.mostrarMensaje(EditorCursos.this, "Se modifico la pregunta del test correctamente. ");
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //fv.guardadoLogs(error.toString(), "editorCursos_modificarTest");
                            fv.mostrarMensaje(EditorCursos.this, "No se pudo actualizar el test. ");
                        }
                    }
            ) {
                public String getBodyContentType() {
                    return "application/json";
                }

                /*@Override
                public byte[] getBody() {
                    JSONObject json = new JSONObject();
                    try {
                        json.put("id_curso", id_curso); // Id del curso
                        // datos pregunta
                        json.put("id_pregunta", id_pregunta);
                        json.put("enunciado_pregunta", preguntas.get(0)[1]);

                        // Datos Opciones
                        System.out.println("asdasdbasdoiJHASDPIJaspjdpASJNDPKJsndpjNASIJDN --> "+ opciones.get(0)[4]);
                        System.out.println("asdasdbasdoiJHASDPIJaspjdpASJNDPKJsndpjNASIJDN --> "+ opciones.get(1)[4]);
                        System.out.println("asdasdbasdoiJHASDPIJaspjdpASJNDPKJsndpjNASIJDN --> "+ opciones.get(2)[4]);
                        System.out.println("asdasdbasdoiJHASDPIJaspjdpASJNDPKJsndpjNASIJDN --> "+ opciones.get(3)[4]);
                        // Pregunta
                        // Opcion 1
                        json.put("id_op1", opciones.get(0)[0]);
                        json.put("enun_op1", opciones.get(0)[3]);
                        json.put("escor_op1", opciones.get(0)[4]);
                        // Opcion 2
                        json.put("id_op2", opciones.get(1)[0]);
                        json.put("enun_op2", opciones.get(1)[3]);
                        json.put("escor_op2", opciones.get(1)[4]);
                        // Opcion 3
                        json.put("id_op3", opciones.get(2)[0]);
                        json.put("enun_op3", opciones.get(2)[3]);
                        json.put("escor_op3", opciones.get(2)[4]);
                        // Opcion 4
                        json.put("id_op4", opciones.get(3)[0]);
                        json.put("enun_op4", opciones.get(3)[3]);
                        json.put("escor_op4", opciones.get(3)[4]);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return json.toString().getBytes();
                }*/

                @Override
                public byte[] getBody() {
                    JSONObject json = new JSONObject();
                    try {
                        json.put("id_curso", id_curso); // Id del curso
                        json.put("id_pregunta", id_pregunta); // Id de la pregunta
                        json.put("enunciado_pregunta", fv.textoFinal(et_p1.getText().toString())); // Enunciado pregunta
                        json.put("id_op1", "1"); // Opcion 1
                        json.put("enun_op1", fv.textoFinal(tv_ec_op1.getText().toString())); // enunciado 1
                        json.put("escor_op1", (op1.isChecked()? 1: 0)); // Correcto 1
                        json.put("id_op2", "2");
                        json.put("enun_op2", fv.textoFinal(tv_ec_op2.getText().toString()));
                        json.put("escor_op2", (op2.isChecked()? 1: 0));
                        json.put("id_op3", "3");
                        json.put("enun_op3", fv.textoFinal(tv_ec_op3.getText().toString()));
                        json.put("escor_op3", (op3.isChecked()? 1: 0));
                        json.put("id_op4", "4");
                        json.put("enun_op4", fv.textoFinal(tv_ec_op4.getText().toString()));
                        json.put("escor_op4", (op4.isChecked()? 1: 0));
                        System.out.println("EditorCursos: modificarTest: datos a enviar a la API: ");
                        System.out.println("id_curso --> " + id_curso);
                        System.out.println("id_pregunta --> " + id_pregunta);
                        System.out.println("enunciado_pregunta --> " + fv.textoFinal(et_p1.getText().toString()));
                        System.out.println("id_op1 --> " + "1");
                        System.out.println("enun_op1 --> " + fv.textoFinal(tv_ec_op1.getText().toString()));
                        System.out.println("escor_op1 --> " + (op1.isChecked()? 1: 0));
                        System.out.println("id_op2 --> " + "2");
                        System.out.println("enun_op2 --> " + fv.textoFinal(tv_ec_op2.getText().toString()));
                        System.out.println("escor_op2 --> " + (op2.isChecked()? 1: 0));
                        System.out.println("id_op3 --> " + "3");
                        System.out.println("enun_op3 --> " + fv.textoFinal(tv_ec_op3.getText().toString()));
                        System.out.println("escor_op3 --> " + (op3.isChecked()? 1: 0));
                        System.out.println("id_op4 --> " + "4");
                        System.out.println("enun_op4 --> " + fv.textoFinal(tv_ec_op4.getText().toString()));
                        System.out.println("escor_op4 --> " + (op4.isChecked()? 1: 0));
                    }catch (JSONException j){
                        j.printStackTrace();
                    }
                    return json.toString().getBytes();
                }
            };

            // Añadimos la peticion a la cola
            rq.add(sr);
        }
    }

    /**
     * Metodo para obtener los nuevos datos del test
     * @return
     */
    public Map<String[], List<String[]>> obtenerNuevosDatos() {
        System.out.println("radiobuttonchecked = "+ grupo1.getCheckedRadioButtonId());
        System.out.println("op1 = "+ op1.isChecked());
        System.out.println("op2 = "+ op2.isChecked());
        System.out.println("op3 = "+ op3.isChecked());
        System.out.println("op4 = "+ op4.isChecked());
        if(grupo1.getCheckedRadioButtonId() == -1) {
            System.out.println("Hay alguna respuesta no indicada. ");
            fv.mostrarMensaje(EditorCursos.this, "Debe seleccionar una opción como correcta. ");
            return null;
        }else{
            Map<String[], List<String[]>> nuevosDatos = new HashMap<>(); // Preguntas y lsita de opciones
            List<String[]> listaOpcionesPregunta = obtenerOpciones(); // Lista para las opciones de las preguntas
            List<String[]> listaEnunciadosPreguntas = obtenerPreguntas(); // lista de todas las preguntas

            // Pregunta 1 con sus respectivas opciones
            List<String[]> opciones = new ArrayList<>();
            String[] datosPreguntas = listaEnunciadosPreguntas.get(0);
            String[] datosOpciones = listaOpcionesPregunta.get(0);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(1);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(2);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(3);
            opciones.add(datosOpciones);
            nuevosDatos.put(datosPreguntas, opciones);

            return nuevosDatos;
        }
    }

    /**
     * Metodo para obtener una lista de todas las preguntas
     * @return -- Devuelve la lista de preguntas
     */
    public List<String[]> obtenerPreguntas(){
        List<String[]> listaEnunciadosPreguntas = new ArrayList<>();
        String[] datosPreguntas1 = new String[2];

        // Añadimos las preguntas a la lista
        datosPreguntas1[0] = String.valueOf(id_pregunta); // ID
        datosPreguntas1[1] = fv.textoFinal(et_p1.getText().toString());
        listaEnunciadosPreguntas.add(datosPreguntas1);
        System.out.println("EditorCursos: obtenerPreguntas: textoFinal: ");

        return listaEnunciadosPreguntas;
    }

    /**
     * Metodo que obtiene una lista de opciones
     * @return -- devuelve una lista con todos los datos de las opciones.
     */
    public List<String[]> obtenerOpciones(){
        List<String[]> listaOpciones = new ArrayList<>();
        String [] datosOpciones = new String [5];
        // Pregunta 1
        datosOpciones[0] = "1"; // ID
        datosOpciones[1] = String.valueOf(id_pregunta); // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op1.getText().toString()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op1); // CORRECTA?
        /*System.out.println("EditorCursos: ObtenerOpciones: id: "+datosOpciones[0]);
        System.out.println("EditorCursos: ObtenerOpciones: preg "+datosOpciones[1]);
        System.out.println("EditorCursos: ObtenerOpciones: curso " +datosOpciones[2]);
        System.out.println("EditorCursos: ObtenerOpciones: texto "+datosOpciones[3]);
        System.out.println("EditorCursos: ObtenerOpciones: correcto? "+datosOpciones[4]);*/
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "2"; // ID
        datosOpciones[1] = String.valueOf(id_pregunta); // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op2.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op2); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "3"; // ID
        datosOpciones[1] = String.valueOf(id_pregunta); // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op3.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op3); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "4"; // ID
        datosOpciones[1] = String.valueOf(id_pregunta); // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op4.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op4); // CORRECTA?
        listaOpciones.add(datosOpciones);

        /**
         * Trazas
         */
        for (int i = 0; i < listaOpciones.size(); i++) {
            System.out.println("EditorCursos: ObtenerOpciones: id:    "    +listaOpciones.get(i)[0]);
            System.out.println("EditorCursos: ObtenerOpciones: preg:  "    +listaOpciones.get(i)[1]);
            System.out.println("EditorCursos: ObtenerOpciones: curso: "    +listaOpciones.get(i)[2]);
            System.out.println("EditorCursos: ObtenerOpciones: texto: "    +listaOpciones.get(i)[3]);
            System.out.println("EditorCursos: ObtenerOpciones: correcto: " +listaOpciones.get(i)[4]);
        }

        //Devolvemos la lista de opciones
        return listaOpciones;
    }

    /**
     * Metodo que devuelve la opcion correcta nueva
     * @param r -- {Radiobutton} Parametro que pasamos para comprobar si esta seleccionado o no
     * @return -- Devuelve 1 si está seleccionado, 0 si no
     */
    public String obtenerOpcionSeleccionada(RadioButton r){
        return r.isChecked()? "1": "0";
    }

    /**
     * Metodo que te muestra la siguiente pregunta
     * @param v -- View del botón pulsado.
     */
    public void siguientePregunta(View v){
        crearOModifTest();
        int pregunta = id_pregunta+1;
        mostrarPregunta(pregunta);
    }

    /**
     * Metodo que muestra la pantalla con la siguiente pregunta
     * @param preg -- Numero de pregunta a obtener
     */
    public void mostrarPregunta(int preg){
        Intent i = new Intent(this, EditorCursos.class);
        i.putExtra("dni", dni);
        i.putExtra("clase", clase);
        System.out.println("EditorCursos: mostrarPregunta: pregunta: "+preg);
        i.putExtra("id_pregunta", preg);
        i.putExtra("id_curso", id_curso);
        startActivity(i);
        finish();
    }

    /**
     * Metodo que te muestra la pregunta anterior.
     * @param v -- View del botón pulsado.
     */
    public void anteriorPregunta(View v){
        int pregunta = (id_pregunta-1 == -1)? 0: id_pregunta-1;
        mostrarPregunta(pregunta);
    }

    /**
     * Metodo que añade o modifica el test
     */
    public void crearOModifTest(){
        /*if (!this.existe) {
            crearTest();
        }else{
            modificarTest();
        }*/
        crearTest();
    }
}