package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

    // Variables XML
    //Preguntas
    EditText et_p1;
    EditText et_p2;
    EditText et_p3;
    EditText et_p4;
    EditText et_p5;
    EditText et_p6;
    EditText et_p7;
    EditText et_p8;
    EditText et_p9;
    EditText et_p10;

    // Opciones
    EditText tv_ec_op1_p1;
    EditText tv_ec_op2_p1;
    EditText tv_ec_op3_p1;
    EditText tv_ec_op1_p2;
    EditText tv_ec_op2_p2;
    EditText tv_ec_op3_p2;
    EditText tv_ec_op1_p3;
    EditText tv_ec_op2_p3;
    EditText tv_ec_op3_p3;
    EditText tv_ec_op1_p4;
    EditText tv_ec_op2_p4;
    EditText tv_ec_op3_p4;
    EditText tv_ec_op1_p5;
    EditText tv_ec_op2_p5;
    EditText tv_ec_op3_p5;
    EditText tv_ec_op1_p6;
    EditText tv_ec_op2_p6;
    EditText tv_ec_op3_p6;
    EditText tv_ec_op1_p7;
    EditText tv_ec_op2_p7;
    EditText tv_ec_op3_p7;
    EditText tv_ec_op1_p8;
    EditText tv_ec_op2_p8;
    EditText tv_ec_op3_p8;
    EditText tv_ec_op1_p9;
    EditText tv_ec_op2_p9;
    EditText tv_ec_op3_p9;
    EditText tv_ec_op1_p10;
    EditText tv_ec_op2_p10;
    EditText tv_ec_op3_p10;

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

    // Variables para la obtencion de opcion correcta de la BD
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
        // Preguntas
        et_p1   = findViewById(R.id.tv_ec_pregunta1);
        et_p2   = findViewById(R.id.tv_ec_pregunta2);
        et_p3   = findViewById(R.id.tv_ec_pregunta3);
        et_p4   = findViewById(R.id.tv_ec_pregunta4);
        et_p5   = findViewById(R.id.tv_ec_pregunta5);
        et_p6   = findViewById(R.id.tv_ec_pregunta6);
        et_p7   = findViewById(R.id.tv_ec_pregunta7);
        et_p8   = findViewById(R.id.tv_ec_pregunta8);
        et_p9   = findViewById(R.id.tv_ec_pregunta9);
        et_p10  = findViewById(R.id.tv_ec_pregunta10);
        // Opciones
        tv_ec_op1_p1    = findViewById(R.id.tv_ec_op1_p1);
        tv_ec_op2_p1    = findViewById(R.id.tv_ec_op2_p1);
        tv_ec_op3_p1    = findViewById(R.id.tv_ec_op3_p1);
        tv_ec_op1_p2    = findViewById(R.id.tv_ec_op1_p2);
        tv_ec_op2_p2    = findViewById(R.id.tv_ec_op2_p2);
        tv_ec_op3_p2    = findViewById(R.id.tv_ec_op3_p2);
        tv_ec_op1_p3    = findViewById(R.id.tv_ec_op1_p3);
        tv_ec_op2_p3    = findViewById(R.id.tv_ec_op2_p3);
        tv_ec_op3_p3    = findViewById(R.id.tv_ec_op3_p3);
        tv_ec_op1_p4    = findViewById(R.id.tv_ec_op1_p4);
        tv_ec_op2_p4    = findViewById(R.id.tv_ec_op2_p4);
        tv_ec_op3_p4    = findViewById(R.id.tv_ec_op3_p4);
        tv_ec_op1_p5    = findViewById(R.id.tv_ec_op1_p5);
        tv_ec_op2_p5    = findViewById(R.id.tv_ec_op2_p5);
        tv_ec_op3_p5    = findViewById(R.id.tv_ec_op3_p5);
        tv_ec_op1_p6    = findViewById(R.id.tv_ec_op1_p6);
        tv_ec_op2_p6    = findViewById(R.id.tv_ec_op2_p6);
        tv_ec_op3_p6    = findViewById(R.id.tv_ec_op3_p6);
        tv_ec_op1_p7    = findViewById(R.id.tv_ec_op1_p7);
        tv_ec_op2_p7    = findViewById(R.id.tv_ec_op2_p7);
        tv_ec_op3_p7    = findViewById(R.id.tv_ec_op3_p7);
        tv_ec_op1_p8    = findViewById(R.id.tv_ec_op1_p8);
        tv_ec_op2_p8    = findViewById(R.id.tv_ec_op2_p8);
        tv_ec_op3_p8    = findViewById(R.id.tv_ec_op3_p8);
        tv_ec_op1_p9    = findViewById(R.id.tv_ec_op1_p9);
        tv_ec_op2_p9    = findViewById(R.id.tv_ec_op2_p9);
        tv_ec_op3_p9    = findViewById(R.id.tv_ec_op3_p9);
        tv_ec_op1_p10   = findViewById(R.id.tv_ec_op1_p10);
        tv_ec_op2_p10   = findViewById(R.id.tv_ec_op2_p10);
        tv_ec_op3_p10   = findViewById(R.id.tv_ec_op3_p10);
        // Grupos para la eleccion de la respuesta correcta
        grupo1  = findViewById(R.id.ec_grupo1);
        grupo2  = findViewById(R.id.ec_grupo2);
        grupo3  = findViewById(R.id.ec_grupo3);
        grupo4  = findViewById(R.id.ec_grupo4);
        grupo5  = findViewById(R.id.ec_grupo5);
        grupo6  = findViewById(R.id.ec_grupo6);
        grupo7  = findViewById(R.id.ec_grupo7);
        grupo8  = findViewById(R.id.ec_grupo8);
        grupo9  = findViewById(R.id.ec_grupo9);
        grupo10 = findViewById(R.id.ec_grupo10);
        // Asignamos los RadioButton a su rec
        op1_p1  = findViewById(R.id.ec_op1_p1);
        op2_p1  = findViewById(R.id.ec_op2_p1);
        op3_p1  = findViewById(R.id.ec_op3_p1);
        op1_p2  = findViewById(R.id.ec_op1_p2);
        op2_p2  = findViewById(R.id.ec_op2_p2);
        op3_p2  = findViewById(R.id.ec_op3_p2);
        op1_p3  = findViewById(R.id.ec_op1_p3);
        op2_p3  = findViewById(R.id.ec_op2_p3);
        op3_p3  = findViewById(R.id.ec_op3_p3);
        op1_p4  = findViewById(R.id.ec_op1_p4);
        op2_p4  = findViewById(R.id.ec_op2_p4);
        op3_p4  = findViewById(R.id.ec_op3_p4);
        op1_p5  = findViewById(R.id.ec_op1_p5);
        op2_p5  = findViewById(R.id.ec_op2_p5);
        op3_p5  = findViewById(R.id.ec_op3_p5);
        op1_p6  = findViewById(R.id.ec_op1_p6);
        op2_p6  = findViewById(R.id.ec_op2_p6);
        op3_p6  = findViewById(R.id.ec_op3_p6);
        op1_p7  = findViewById(R.id.ec_op1_p7);
        op2_p7  = findViewById(R.id.ec_op2_p7);
        op3_p7  = findViewById(R.id.ec_op3_p7);
        op1_p8  = findViewById(R.id.ec_op1_p8);
        op2_p8  = findViewById(R.id.ec_op2_p8);
        op3_p8  = findViewById(R.id.ec_op3_p8);
        op1_p9  = findViewById(R.id.ec_op1_p9);
        op2_p9  = findViewById(R.id.ec_op2_p9);
        op3_p9  = findViewById(R.id.ec_op3_p9);
        op1_p10 = findViewById(R.id.ec_op1_p10);
        op2_p10 = findViewById(R.id.ec_op2_p10);
        op3_p10 = findViewById(R.id.ec_op3_p10);

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

        //Mostramos las preguntas
        cargarPreguntas(id_curso);
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

                    switch (id_preg){
                        case 1:
                            et_p1.setText(enunciado_preg);
                            tv_ec_op1_p1.setText(enun_op1);
                            tv_ec_op2_p1.setText(enun_op2);
                            tv_ec_op3_p1.setText(enun_op3);
                            op1_p1.setText("Opcion 1");
                            op2_p1.setText("Opcion 2");
                            op3_p1.setText("Opcion 3");
                            bd_opCor_p1 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
                            seleccionarOpcionCor(bd_opCor_p1, op1_p1, op2_p1, op3_p1);
                            break;
                        case 2:
                            et_p2.setText(enunciado_preg);
                            tv_ec_op1_p2.setText(enun_op1);
                            tv_ec_op2_p2.setText(enun_op2);
                            tv_ec_op3_p2.setText(enun_op3);
                            op1_p2.setText("Opcion 1");
                            op2_p2.setText("Opcion 2");
                            op3_p2.setText("Opcion 3");
                            bd_opCor_p2 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
                            seleccionarOpcionCor(bd_opCor_p2, op1_p2, op2_p2, op3_p2);
                            break;
                        case 3:
                            et_p3.setText(enunciado_preg);
                            tv_ec_op1_p3.setText(enun_op1);
                            tv_ec_op2_p3.setText(enun_op2);
                            tv_ec_op3_p3.setText(enun_op3);
                            op1_p3.setText("Opcion 1");
                            op2_p3.setText("Opcion 2");
                            op3_p3.setText("Opcion 3");
                            bd_opCor_p3 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
                            seleccionarOpcionCor(bd_opCor_p3, op1_p3, op2_p3, op3_p3);
                            break;
                        case 4:
                            et_p4.setText(enunciado_preg);
                            tv_ec_op1_p4.setText(enun_op1);
                            tv_ec_op2_p4.setText(enun_op2);
                            tv_ec_op3_p4.setText(enun_op3);
                            op1_p4.setText("Opcion 1");
                            op2_p4.setText("Opcion 2");
                            op3_p4.setText("Opcion 3");
                            bd_opCor_p4 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
                            seleccionarOpcionCor(bd_opCor_p4, op1_p4, op2_p4, op3_p4);
                            break;
                        case 5:
                            et_p5.setText(enunciado_preg);
                            tv_ec_op1_p5.setText(enun_op1);
                            tv_ec_op2_p5.setText(enun_op2);
                            tv_ec_op3_p5.setText(enun_op3);
                            op1_p5.setText("Opcion 1");
                            op2_p5.setText("Opcion 2");
                            op3_p5.setText("Opcion 3");
                            bd_opCor_p5 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
                            seleccionarOpcionCor(bd_opCor_p5, op1_p5, op2_p5, op3_p5);
                            break;
                        case 6:
                            et_p6.setText(enunciado_preg);
                            tv_ec_op1_p6.setText(enun_op1);
                            tv_ec_op2_p6.setText(enun_op2);
                            tv_ec_op3_p6.setText(enun_op3);
                            op1_p6.setText("Opcion 1");
                            op2_p6.setText("Opcion 2");
                            op3_p6.setText("Opcion 3");
                            bd_opCor_p6 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
                            seleccionarOpcionCor(bd_opCor_p6, op1_p6, op2_p6, op3_p6);
                            break;
                        case 7:
                            et_p7.setText(enunciado_preg);
                            tv_ec_op1_p7.setText(enun_op1);
                            tv_ec_op2_p7.setText(enun_op2);
                            tv_ec_op3_p7.setText(enun_op3);
                            op1_p7.setText("Opcion 1");
                            op2_p7.setText("Opcion 2");
                            op3_p7.setText("Opcion 3");
                            bd_opCor_p7 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
                            seleccionarOpcionCor(bd_opCor_p7, op1_p7, op2_p7, op3_p7);
                            break;
                        case 8:
                            et_p8.setText(enunciado_preg);
                            tv_ec_op1_p8.setText(enun_op1);
                            tv_ec_op2_p8.setText(enun_op2);
                            tv_ec_op3_p8.setText(enun_op3);
                            op1_p8.setText("Opcion 1");
                            op2_p8.setText("Opcion 2");
                            op3_p8.setText("Opcion 3");
                            bd_opCor_p8 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
                            seleccionarOpcionCor(bd_opCor_p8, op1_p8, op2_p8, op3_p8);
                            break;
                        case 9:
                            et_p9.setText(enunciado_preg);
                            tv_ec_op1_p9.setText(enun_op1);
                            tv_ec_op2_p9.setText(enun_op2);
                            tv_ec_op3_p9.setText(enun_op3);
                            op1_p9.setText("Opcion 1");
                            op2_p9.setText("Opcion 2");
                            op3_p9.setText("Opcion 3");
                            bd_opCor_p9 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
                            seleccionarOpcionCor(bd_opCor_p9, op1_p9, op2_p9, op3_p9);
                            break;
                        case 10:
                            et_p10.setText(enunciado_preg);
                            tv_ec_op1_p10.setText(enun_op1);
                            tv_ec_op2_p10.setText(enun_op2);
                            tv_ec_op3_p10.setText(enun_op3);
                            op1_p10.setText("Opcion 1");
                            op2_p10.setText("Opcion 2");
                            op3_p10.setText("Opcion 3");
                            bd_opCor_p10 = opcionCorrecta(esCorrecta_op1, esCorrecta_op2, esCorrecta_op3);
                            seleccionarOpcionCor(bd_opCor_p10, op1_p10, op2_p10, op3_p10);
                            break;
                    }
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
     */
    public void seleccionarOpcionCor(int opcion, RadioButton r1, RadioButton r2, RadioButton r3){
        if (opcion == 1){
            r1.setChecked(true);
            System.out.println("EditorCursos: seleccionarOpcionCor: op1 correcta. ");
        }else if(opcion == 2){
            System.out.println("EditorCursos: seleccionarOpcionCor: op2 correcta. ");
            r2.setChecked(true);
        }else if(opcion == 3) {
            r3.setChecked(true);
            System.out.println("EditorCursos: seleccionarOpcionCor: op3 correcta. ");
        }
    }

    /**
     * Metodo para obtener las preguntas de la base de datos
     * @param cd
     * @return -- Mapa con las preguntas y sus opciones
     */
    public Map<String[], List<String[]>> getPreguntas(ConsultarDatos cd){

        // final String URL = "http://"+getString(R.string.ip)+"/tfg/app/API/getTest.php?idCurso="+id_curso;
        final String URL = fv.getURL()+"getTest.php?idCurso="+id_curso.trim();

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
    public int opcionCorrecta(String op1, String op2, String op3){
        int respuesta = -1;
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
     * Metodo para añadir o modificar los tests
     * @param v
     */
    public void add_modif_test(View v){
        System.out.println("Existe el curso--> "+existe);
        if (!this.existe) {
            crearTest();
        }else{
            modificarTest();
        }
    }

    /**
     * Metodo para crear los test
     */
    public void crearTest(){
        //final String URL = "http://"+getString(R.string.ip)+"/tfg/app/API/addTest.php";
        final String URL = fv.getURL()+"addTest.php";

        Map<String[], List<String[]>> datosNuevos = obtenerNuevosDatos();

        List<String[]> preguntas = new ArrayList<>();
        for (Map.Entry<String[], List<String[]>> entry : datosNuevos.entrySet()) {
            String[] datos = entry.getKey();
            System.out.println(datos[0]+"-->"+datos[1]);
            preguntas.add(datos);
        }
        for (int i = 0; i < preguntas.size(); i++) {
            System.out.println("EditorCursos: crearTest: "+preguntas.get(i)[0] +" ---------> "+ preguntas.get(i)[1]);
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
                        fv.mostrarMensaje(EditorCursos.this, "Se añadio el test correctamente. ");
                        Intent i = new Intent(EditorCursos.this, claseAnterior);
                        i.putExtra("dni", dni);
                        startActivity(i);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //fv.guardadoLogs(error.toString(), "editorCursos_crearTest");
                        fv.mostrarMensaje(EditorCursos.this, "No se pudo insertar el test, pruebe a insertarlo de nuevo en unos minutos. ");
                    }
                }
        ){
            public String getBodyContentType() {
                return "application/json";
            }

            // Metodo getBody
            @Override
            public byte[] getBody() {
                JSONObject json = new JSONObject();
                try {
                    json.put("id_curso", id_curso); // Id del curso
                    // datos pregunta 1
                    json.put("id_pregunta1", preguntas.get(0)[0]);
                    json.put("enunciado_pregunta1", preguntas.get(0)[1]);
                    // datos pregunta 2
                    json.put("id_pregunta2", preguntas.get(1)[0]);
                    json.put("enunciado_pregunta2", preguntas.get(1)[1]);
                    // datos pregunta 3
                    json.put("id_pregunta3", preguntas.get(2)[0]);
                    json.put("enunciado_pregunta3", preguntas.get(2)[1]);
                    // datos pregunta 4
                    json.put("id_pregunta4", preguntas.get(3)[0]);
                    json.put("enunciado_pregunta4", preguntas.get(3)[1]);
                    // datos pregunta 5
                    json.put("id_pregunta5", preguntas.get(4)[0]);
                    json.put("enunciado_pregunta5", preguntas.get(4)[1]);
                    // datos pregunta 6
                    json.put("id_pregunta6", preguntas.get(5)[0]);
                    json.put("enunciado_pregunta6", preguntas.get(5)[1]);
                    // datos pregunta 7
                    json.put("id_pregunta7", preguntas.get(6)[0]);
                    json.put("enunciado_pregunta7", preguntas.get(6)[1]);
                    // datos pregunta 8
                    json.put("id_pregunta8", preguntas.get(7)[0]);
                    json.put("enunciado_pregunta8", preguntas.get(7)[1]);
                    // datos pregunta 9
                    json.put("id_pregunta9", preguntas.get(8)[0]);
                    json.put("enunciado_pregunta9", preguntas.get(8)[1]);
                    // datos pregunta 10
                    json.put("id_pregunta10", preguntas.get(9)[0]);
                    json.put("enunciado_pregunta10", preguntas.get(9)[1]);

                    // Datos Opciones
                    // Pregunta 1
                    // Opcion 1
                    json.put("id_op1_p1", opciones.get(0)[0]);
                    json.put("enun_op1_p1", opciones.get(0)[3]);
                    json.put("escor_op1_p1", opciones.get(0)[4]);
                    // Opcion 1
                    json.put("id_op1_p1", opciones.get(0)[0]);
                    json.put("enun_op1_p1", opciones.get(0)[3]);
                    json.put("escor_op1_p1", opciones.get(0)[4]);
                    // Opcion 2
                    json.put("id_op2_p1", opciones.get(1)[0]);
                    json.put("enun_op2_p1", opciones.get(1)[3]);
                    json.put("escor_op2_p1", opciones.get(1)[4]);
                    // Opcion 2
                    json.put("id_op2_p1", opciones.get(2)[0]);
                    json.put("enun_op2_p1", opciones.get(2)[3]);
                    json.put("escor_op2_p1", opciones.get(2)[4]);

                    // Pregunta 1
                    // Opcion 1
                    json.put("id_op1_p1", opciones.get(0)[0]);
                    json.put("enun_op1_p1", opciones.get(0)[3]);
                    json.put("escor_op1_p1", opciones.get(0)[4]);
                    // Opcion 2
                    json.put("id_op2_p1", opciones.get(1)[0]);
                    json.put("enun_op2_p1", opciones.get(1)[3]);
                    json.put("escor_op2_p1", opciones.get(1)[4]);
                    // Opcion 3
                    json.put("id_op3_p1", opciones.get(2)[0]);
                    json.put("enun_op3_p1", opciones.get(2)[3]);
                    json.put("escor_op3_p1", opciones.get(2)[4]);
                    // Pregunta 2
                    // Opcion 1
                    json.put("id_op1_p2", opciones.get(3)[0]);
                    json.put("enun_op1_p2", opciones.get(3)[3]);
                    json.put("escor_op1_p2", opciones.get(3)[4]);
                    // Opcion 2
                    json.put("id_op2_p2", opciones.get(4)[0]);
                    json.put("enun_op2_p2", opciones.get(4)[3]);
                    json.put("escor_op2_p2", opciones.get(4)[4]);
                    // Opcion 3
                    json.put("id_op3_p2", opciones.get(5)[0]);
                    json.put("enun_op3_p2", opciones.get(5)[3]);
                    json.put("escor_op3_p2", opciones.get(5)[4]);
                    // Pregunta 3
                    // Opcion 1
                    json.put("id_op1_p3", opciones.get(6)[0]);
                    json.put("enun_op1_p3", opciones.get(6)[3]);
                    json.put("escor_op1_p3", opciones.get(6)[4]);
                    // Opcion 2
                    json.put("id_op2_p3", opciones.get(7)[0]);
                    json.put("enun_op2_p3", opciones.get(7)[3]);
                    json.put("escor_op2_p3", opciones.get(7)[4]);
                    // Opcion 3
                    json.put("id_op3_p3", opciones.get(8)[0]);
                    json.put("enun_op3_p3", opciones.get(8)[3]);
                    json.put("escor_op3_p3", opciones.get(8)[4]);
                    // Pregunta 4
                    // Opcion 1
                    json.put("id_op1_p4", opciones.get(9)[0]);
                    json.put("enun_op1_p4", opciones.get(9)[3]);
                    json.put("escor_op1_p4", opciones.get(9)[4]);
                    // Opcion 2
                    json.put("id_op2_p4", opciones.get(10)[0]);
                    json.put("enun_op2_p4", opciones.get(10)[3]);
                    json.put("escor_op2_p4", opciones.get(10)[4]);
                    // Opcion 3
                    json.put("id_op3_p4", opciones.get(11)[0]);
                    json.put("enun_op3_p4", opciones.get(11)[3]);
                    json.put("escor_op3_p4", opciones.get(11)[4]);
                    // Pregunta 5
                    // Opcion 1
                    json.put("id_op1_p5", opciones.get(12)[0]);
                    json.put("enun_op1_p5", opciones.get(12)[3]);
                    json.put("escor_op1_p5", opciones.get(12)[4]);
                    // Opcion 2
                    json.put("id_op2_p5", opciones.get(13)[0]);
                    json.put("enun_op2_p5", opciones.get(13)[3]);
                    json.put("escor_op2_p5", opciones.get(13)[4]);
                    // Opcion 3
                    json.put("id_op3_p5", opciones.get(14)[0]);
                    json.put("enun_op3_p5", opciones.get(14)[3]);
                    json.put("escor_op3_p5", opciones.get(14)[4]);
                    // Pregunta 6
                    // Opcion 1
                    json.put("id_op1_p6", opciones.get(15)[0]);
                    json.put("enun_op1_p6", opciones.get(15)[3]);
                    json.put("escor_op1_p6", opciones.get(15)[4]);
                    // Opcion 2
                    json.put("id_op2_p6", opciones.get(16)[0]);
                    json.put("enun_op2_p6", opciones.get(16)[3]);
                    json.put("escor_op2_p6", opciones.get(16)[4]);
                    // Opcion 3
                    json.put("id_op3_p6", opciones.get(17)[0]);
                    json.put("enun_op3_p6", opciones.get(17)[3]);
                    json.put("escor_op3_p6", opciones.get(17)[4]);
                    // Pregunta 7
                    // Opcion 1
                    json.put("id_op1_p7", opciones.get(18)[0]);
                    json.put("enun_op1_p7", opciones.get(18)[3]);
                    json.put("escor_op1_p7", opciones.get(18)[4]);
                    // Opcion 2
                    json.put("id_op2_p7", opciones.get(19)[0]);
                    json.put("enun_op2_p7", opciones.get(19)[3]);
                    json.put("escor_op2_p7", opciones.get(19)[4]);
                    // Opcion 3
                    json.put("id_op3_p7", opciones.get(20)[0]);
                    json.put("enun_op3_p7", opciones.get(20)[3]);
                    json.put("escor_op3_p7", opciones.get(20)[4]);
                    // Pregunta 8
                    // Opcion 1
                    json.put("id_op1_p8", opciones.get(21)[0]);
                    json.put("enun_op1_p8", opciones.get(21)[3]);
                    json.put("escor_op1_p8", opciones.get(21)[4]);
                    // Opcion 2
                    json.put("id_op2_p8", opciones.get(22)[0]);
                    json.put("enun_op2_p8", opciones.get(22)[3]);
                    json.put("escor_op2_p8", opciones.get(22)[4]);
                    // Opcion 3
                    json.put("id_op3_p8", opciones.get(23)[0]);
                    json.put("enun_op3_p8", opciones.get(23)[3]);
                    json.put("escor_op3_p8", opciones.get(23)[4]);
                    // Pregunta 9
                    // Opcion 1
                    json.put("id_op1_p9", opciones.get(24)[0]);
                    json.put("enun_op1_p9", opciones.get(24)[3]);
                    json.put("escor_op1_p9", opciones.get(24)[4]);
                    // Opcion 2
                    json.put("id_op2_p9", opciones.get(25)[0]);
                    json.put("enun_op2_p9", opciones.get(25)[3]);
                    json.put("escor_op2_p9", opciones.get(25)[4]);
                    // Opcion 3
                    json.put("id_op3_p9", opciones.get(26)[0]);
                    json.put("enun_op3_p9", opciones.get(26)[3]);
                    json.put("escor_op3_p9", opciones.get(26)[4]);
                    // Pregunta 10
                    // Opcion 1
                    json.put("id_op1_p10", opciones.get(27)[0]);
                    json.put("enun_op1_p10", opciones.get(27)[3]);
                    json.put("escor_op1_p10", opciones.get(27)[4]);
                    // Opcion 2
                    json.put("id_op2_p10", opciones.get(28)[0]);
                    json.put("enun_op2_p10", opciones.get(28)[3]);
                    json.put("escor_op2_p10", opciones.get(28)[4]);
                    // Opcion 3
                    json.put("id_op3_p10", opciones.get(29)[0]);
                    json.put("enun_op3_p10", opciones.get(29)[3]);
                    json.put("escor_op3_p10", opciones.get(29)[4]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return json.toString().getBytes();
            }
        };

        // Añadimos la peticion a la cola
        rq.add(sr);
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
        /*for (int i = 0; i < preguntas.size(); i++) {
            System.out.println("EditorCursos: modificarTest: preguntas "+ preguntas.get(i)[0] + " ---> "+preguntas.get(i)[1]);
        }
        for (int i = 0; i < opciones.size(); i++) {
            System.out.println("EditorCursos: modificarTest: opciones "+
                                opciones.get(i)[0] + " ---> " +
                                opciones.get(i)[1] + " ---> " +
                                opciones.get(i)[2] + " ---> " +
                                opciones.get(i)[3] + " ---> " +
                                opciones.get(i)[4]);
        }*/

            RequestQueue rq = Volley.newRequestQueue(this);

            StringRequest sr = new StringRequest(Request.Method.PUT, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            fv.mostrarMensaje(EditorCursos.this, "Se modifico el test correctamente. ");
                            Intent i = new Intent(EditorCursos.this, claseAnterior);
                            i.putExtra("dni", dni);
                            startActivity(i);
                            finish();
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

                @Override
                public byte[] getBody() {
                    JSONObject json = new JSONObject();
                    try {
                        json.put("id_curso", id_curso); // Id del curso
                        // datos pregunta 1
                        json.put("id_pregunta1", preguntas.get(0)[0]);
                        json.put("enunciado_pregunta1", preguntas.get(0)[1]);
                        // datos pregunta 2
                        json.put("id_pregunta2", preguntas.get(1)[0]);
                        json.put("enunciado_pregunta2", preguntas.get(1)[1]);
                        // datos pregunta 3
                        json.put("id_pregunta3", preguntas.get(2)[0]);
                        json.put("enunciado_pregunta3", preguntas.get(2)[1]);
                        // datos pregunta 4
                        json.put("id_pregunta4", preguntas.get(3)[0]);
                        json.put("enunciado_pregunta4", preguntas.get(3)[1]);
                        // datos pregunta 5
                        json.put("id_pregunta5", preguntas.get(4)[0]);
                        json.put("enunciado_pregunta5", preguntas.get(4)[1]);
                        // datos pregunta 6
                        json.put("id_pregunta6", preguntas.get(5)[0]);
                        json.put("enunciado_pregunta6", preguntas.get(5)[1]);
                        // datos pregunta 7
                        json.put("id_pregunta7", preguntas.get(6)[0]);
                        json.put("enunciado_pregunta7", preguntas.get(6)[1]);
                        // datos pregunta 8
                        json.put("id_pregunta8", preguntas.get(7)[0]);
                        json.put("enunciado_pregunta8", preguntas.get(7)[1]);
                        // datos pregunta 9
                        json.put("id_pregunta9", preguntas.get(8)[0]);
                        json.put("enunciado_pregunta9", preguntas.get(8)[1]);
                        // datos pregunta 10
                        json.put("id_pregunta10", preguntas.get(9)[0]);
                        json.put("enunciado_pregunta10", preguntas.get(9)[1]);

                        // Datos Opciones
                        // Pregunta 1
                        // Opcion 1
                        json.put("id_op1_p1", opciones.get(0)[0]);
                        json.put("enun_op1_p1", opciones.get(0)[3]);
                        json.put("escor_op1_p1", opciones.get(0)[4]);
                        // Opcion 1
                        json.put("id_op1_p1", opciones.get(0)[0]);
                        json.put("enun_op1_p1", opciones.get(0)[3]);
                        json.put("escor_op1_p1", opciones.get(0)[4]);
                        // Opcion 2
                        json.put("id_op2_p1", opciones.get(1)[0]);
                        json.put("enun_op2_p1", opciones.get(1)[3]);
                        json.put("escor_op2_p1", opciones.get(1)[4]);
                        // Opcion 2
                        json.put("id_op2_p1", opciones.get(2)[0]);
                        json.put("enun_op2_p1", opciones.get(2)[3]);
                        json.put("escor_op2_p1", opciones.get(2)[4]);

                        // Pregunta 1
                        // Opcion 1
                        json.put("id_op1_p1", opciones.get(0)[0]);
                        json.put("enun_op1_p1", opciones.get(0)[3]);
                        json.put("escor_op1_p1", opciones.get(0)[4]);
                        // Opcion 2
                        json.put("id_op2_p1", opciones.get(1)[0]);
                        json.put("enun_op2_p1", opciones.get(1)[3]);
                        json.put("escor_op2_p1", opciones.get(1)[4]);
                        // Opcion 3
                        json.put("id_op3_p1", opciones.get(2)[0]);
                        json.put("enun_op3_p1", opciones.get(2)[3]);
                        json.put("escor_op3_p1", opciones.get(2)[4]);
                        // Pregunta 2
                        // Opcion 1
                        json.put("id_op1_p2", opciones.get(3)[0]);
                        json.put("enun_op1_p2", opciones.get(3)[3]);
                        json.put("escor_op1_p2", opciones.get(3)[4]);
                        // Opcion 2
                        json.put("id_op2_p2", opciones.get(4)[0]);
                        json.put("enun_op2_p2", opciones.get(4)[3]);
                        json.put("escor_op2_p2", opciones.get(4)[4]);
                        // Opcion 3
                        json.put("id_op3_p2", opciones.get(5)[0]);
                        json.put("enun_op3_p2", opciones.get(5)[3]);
                        json.put("escor_op3_p2", opciones.get(5)[4]);
                        // Pregunta 3
                        // Opcion 1
                        json.put("id_op1_p3", opciones.get(6)[0]);
                        json.put("enun_op1_p3", opciones.get(6)[3]);
                        json.put("escor_op1_p3", opciones.get(6)[4]);
                        // Opcion 2
                        json.put("id_op2_p3", opciones.get(7)[0]);
                        json.put("enun_op2_p3", opciones.get(7)[3]);
                        json.put("escor_op2_p3", opciones.get(7)[4]);
                        // Opcion 3
                        json.put("id_op3_p3", opciones.get(8)[0]);
                        json.put("enun_op3_p3", opciones.get(8)[3]);
                        json.put("escor_op3_p3", opciones.get(8)[4]);
                        // Pregunta 4
                        // Opcion 1
                        json.put("id_op1_p4", opciones.get(9)[0]);
                        json.put("enun_op1_p4", opciones.get(9)[3]);
                        json.put("escor_op1_p4", opciones.get(9)[4]);
                        // Opcion 2
                        json.put("id_op2_p4", opciones.get(10)[0]);
                        json.put("enun_op2_p4", opciones.get(10)[3]);
                        json.put("escor_op2_p4", opciones.get(10)[4]);
                        // Opcion 3
                        json.put("id_op3_p4", opciones.get(11)[0]);
                        json.put("enun_op3_p4", opciones.get(11)[3]);
                        json.put("escor_op3_p4", opciones.get(11)[4]);
                        // Pregunta 5
                        // Opcion 1
                        json.put("id_op1_p5", opciones.get(12)[0]);
                        json.put("enun_op1_p5", opciones.get(12)[3]);
                        json.put("escor_op1_p5", opciones.get(12)[4]);
                        // Opcion 2
                        json.put("id_op2_p5", opciones.get(13)[0]);
                        json.put("enun_op2_p5", opciones.get(13)[3]);
                        json.put("escor_op2_p5", opciones.get(13)[4]);
                        // Opcion 3
                        json.put("id_op3_p5", opciones.get(14)[0]);
                        json.put("enun_op3_p5", opciones.get(14)[3]);
                        json.put("escor_op3_p5", opciones.get(14)[4]);
                        // Pregunta 6
                        // Opcion 1
                        json.put("id_op1_p6", opciones.get(15)[0]);
                        json.put("enun_op1_p6", opciones.get(15)[3]);
                        json.put("escor_op1_p6", opciones.get(15)[4]);
                        // Opcion 2
                        json.put("id_op2_p6", opciones.get(16)[0]);
                        json.put("enun_op2_p6", opciones.get(16)[3]);
                        json.put("escor_op2_p6", opciones.get(16)[4]);
                        // Opcion 3
                        json.put("id_op3_p6", opciones.get(17)[0]);
                        json.put("enun_op3_p6", opciones.get(17)[3]);
                        json.put("escor_op3_p6", opciones.get(17)[4]);
                        // Pregunta 7
                        // Opcion 1
                        json.put("id_op1_p7", opciones.get(18)[0]);
                        json.put("enun_op1_p7", opciones.get(18)[3]);
                        json.put("escor_op1_p7", opciones.get(18)[4]);
                        // Opcion 2
                        json.put("id_op2_p7", opciones.get(19)[0]);
                        json.put("enun_op2_p7", opciones.get(19)[3]);
                        json.put("escor_op2_p7", opciones.get(19)[4]);
                        // Opcion 3
                        json.put("id_op3_p7", opciones.get(20)[0]);
                        json.put("enun_op3_p7", opciones.get(20)[3]);
                        json.put("escor_op3_p7", opciones.get(20)[4]);
                        // Pregunta 8
                        // Opcion 1
                        json.put("id_op1_p8", opciones.get(21)[0]);
                        json.put("enun_op1_p8", opciones.get(21)[3]);
                        json.put("escor_op1_p8", opciones.get(21)[4]);
                        // Opcion 2
                        json.put("id_op2_p8", opciones.get(22)[0]);
                        json.put("enun_op2_p8", opciones.get(22)[3]);
                        json.put("escor_op2_p8", opciones.get(22)[4]);
                        // Opcion 3
                        json.put("id_op3_p8", opciones.get(23)[0]);
                        json.put("enun_op3_p8", opciones.get(23)[3]);
                        json.put("escor_op3_p8", opciones.get(23)[4]);
                        // Pregunta 9
                        // Opcion 1
                        json.put("id_op1_p9", opciones.get(24)[0]);
                        json.put("enun_op1_p9", opciones.get(24)[3]);
                        json.put("escor_op1_p9", opciones.get(24)[4]);
                        // Opcion 2
                        json.put("id_op2_p9", opciones.get(25)[0]);
                        json.put("enun_op2_p9", opciones.get(25)[3]);
                        json.put("escor_op2_p9", opciones.get(25)[4]);
                        // Opcion 3
                        json.put("id_op3_p9", opciones.get(26)[0]);
                        json.put("enun_op3_p9", opciones.get(26)[3]);
                        json.put("escor_op3_p9", opciones.get(26)[4]);
                        // Pregunta 10
                        // Opcion 1
                        json.put("id_op1_p10", opciones.get(27)[0]);
                        json.put("enun_op1_p10", opciones.get(27)[3]);
                        json.put("escor_op1_p10", opciones.get(27)[4]);
                        // Opcion 2
                        json.put("id_op2_p10", opciones.get(28)[0]);
                        json.put("enun_op2_p10", opciones.get(28)[3]);
                        json.put("escor_op2_p10", opciones.get(28)[4]);
                        // Opcion 3
                        json.put("id_op3_p10", opciones.get(29)[0]);
                        json.put("enun_op3_p10", opciones.get(29)[3]);
                        json.put("escor_op3_p10", opciones.get(29)[4]);


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
     * Metodo para obtener los nuevos datos del test
     * @return
     */
    public Map<String[], List<String[]>> obtenerNuevosDatos() {

        if(grupo1.getCheckedRadioButtonId() == -1 || grupo2.getCheckedRadioButtonId() == -1 || grupo3.getCheckedRadioButtonId() == -1 || grupo4.getCheckedRadioButtonId() == -1 ||
           grupo5.getCheckedRadioButtonId() == -1 || grupo6.getCheckedRadioButtonId() == -1 || grupo7.getCheckedRadioButtonId() == -1 || grupo8.getCheckedRadioButtonId() == -1 ||
           grupo9.getCheckedRadioButtonId() == -1 || grupo10.getCheckedRadioButtonId() == -1) {
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
            nuevosDatos.put(datosPreguntas, opciones);

            // Pregunta 2 con sus respectivas opciones
            opciones = new ArrayList<>();
            datosPreguntas = listaEnunciadosPreguntas.get(1);
            datosOpciones = listaOpcionesPregunta.get(3);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(4);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(5);
            opciones.add(datosOpciones);
            nuevosDatos.put(datosPreguntas, opciones);

            // Pregunta 3 con sus respectivas opciones
            opciones = new ArrayList<>();
            datosPreguntas = listaEnunciadosPreguntas.get(2);
            datosOpciones = listaOpcionesPregunta.get(6);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(7);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(8);
            opciones.add(datosOpciones);
            nuevosDatos.put(datosPreguntas, opciones);

            // Pregunta 4 con sus respectivas opciones
            opciones = new ArrayList<>();
            datosPreguntas = listaEnunciadosPreguntas.get(3);
            datosOpciones = listaOpcionesPregunta.get(9);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(10);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(11);
            opciones.add(datosOpciones);
            nuevosDatos.put(datosPreguntas, opciones);

            // Pregunta 5 con sus respectivas opciones
            opciones = new ArrayList<>();
            datosPreguntas = listaEnunciadosPreguntas.get(4);
            datosOpciones = listaOpcionesPregunta.get(12);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(13);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(14);
            opciones.add(datosOpciones);
            nuevosDatos.put(datosPreguntas, opciones);

            // Pregunta 6 con sus respectivas opciones
            opciones = new ArrayList<>();
            datosPreguntas = listaEnunciadosPreguntas.get(5);
            datosOpciones = listaOpcionesPregunta.get(15);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(16);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(17);
            opciones.add(datosOpciones);
            nuevosDatos.put(datosPreguntas, opciones);

            // Pregunta 7 con sus respectivas opciones
            opciones = new ArrayList<>();
            datosPreguntas = listaEnunciadosPreguntas.get(6);
            datosOpciones = listaOpcionesPregunta.get(18);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(19);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(20);
            opciones.add(datosOpciones);
            nuevosDatos.put(datosPreguntas, opciones);

            // Pregunta 8 con sus respectivas opciones
            opciones = new ArrayList<>();
            datosPreguntas = listaEnunciadosPreguntas.get(7);
            datosOpciones = listaOpcionesPregunta.get(21);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(22);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(23);
            opciones.add(datosOpciones);
            nuevosDatos.put(datosPreguntas, opciones);

            // Pregunta 9 con sus respectivas opciones
            opciones = new ArrayList<>();
            datosPreguntas = listaEnunciadosPreguntas.get(8);
            datosOpciones = listaOpcionesPregunta.get(24);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(25);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(26);
            opciones.add(datosOpciones);
            nuevosDatos.put(datosPreguntas, opciones);

            // Pregunta 9 con sus respectivas opciones
            opciones = new ArrayList<>();
            datosPreguntas = listaEnunciadosPreguntas.get(9);
            datosOpciones = listaOpcionesPregunta.get(27);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(28);
            opciones.add(datosOpciones);
            datosOpciones = listaOpcionesPregunta.get(29);
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
        String[] datosPreguntas2 = new String[2];
        String[] datosPreguntas3 = new String[2];
        String[] datosPreguntas4 = new String[2];
        String[] datosPreguntas5 = new String[2];
        String[] datosPreguntas6 = new String[2];
        String[] datosPreguntas7 = new String[2];
        String[] datosPreguntas8 = new String[2];
        String[] datosPreguntas9 = new String[2];
        String[] datosPreguntas10 = new String[2];

        // Añadimos las preguntas a la lista
        datosPreguntas1[0] = "1"; // ID
        datosPreguntas1[1] = fv.textoFinal(et_p1.getText().toString());
        listaEnunciadosPreguntas.add(datosPreguntas1);
        System.out.println("EditorCursos: obtenerPreguntas: textoFinal: ");

        datosPreguntas2[0] = "2"; // ID
        datosPreguntas2[1] = fv.textoFinal(et_p2.getText().toString());
        listaEnunciadosPreguntas.add(datosPreguntas2);

        datosPreguntas3[0] = "3"; // ID
        datosPreguntas3[1] = fv.textoFinal(et_p3.getText().toString());
        listaEnunciadosPreguntas.add(datosPreguntas3);

        datosPreguntas4[0] = "4"; // ID
        datosPreguntas4[1] = fv.textoFinal(et_p4.getText().toString());
        listaEnunciadosPreguntas.add(datosPreguntas4);

        datosPreguntas5[0] = "5"; // ID
        datosPreguntas5[1] = fv.textoFinal(et_p5.getText().toString());
        listaEnunciadosPreguntas.add(datosPreguntas5);

        datosPreguntas6[0] = "6"; // ID
        datosPreguntas6[1] = fv.textoFinal(et_p6.getText().toString());
        listaEnunciadosPreguntas.add(datosPreguntas6);

        datosPreguntas7[0] = "7"; // ID
        datosPreguntas7[1] = fv.textoFinal(et_p7.getText().toString());
        listaEnunciadosPreguntas.add(datosPreguntas7);

        datosPreguntas8[0] = "8"; // ID
        datosPreguntas8[1] = fv.textoFinal(et_p8.getText().toString());
        listaEnunciadosPreguntas.add(datosPreguntas8);

        datosPreguntas9[0] = "9"; // ID
        datosPreguntas9[1] = fv.textoFinal(et_p9.getText().toString());
        listaEnunciadosPreguntas.add(datosPreguntas9);

        datosPreguntas10[0] = "10"; // ID
        datosPreguntas10[1] = fv.textoFinal(et_p10.getText().toString());
        listaEnunciadosPreguntas.add(datosPreguntas10);

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
        datosOpciones[1] = "1"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op1_p1.getText().toString()); // OPCION
        datosOpciones[4] = op1_p1.isChecked() ? "1": "0"; // CORRECTA?
        System.out.println("EditorCursos: ObtenerOpciones: id: "+datosOpciones[0]);
        System.out.println("EditorCursos: ObtenerOpciones: preg "+datosOpciones[1]);
        System.out.println("EditorCursos: ObtenerOpciones: curso " +datosOpciones[2]);
        System.out.println("EditorCursos: ObtenerOpciones: texto "+datosOpciones[3]);
        System.out.println("EditorCursos: ObtenerOpciones: correcto? "+datosOpciones[4]);
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "2"; // ID
        datosOpciones[1] = "1"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op2_p1.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op2_p1); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "3"; // ID
        datosOpciones[1] = "1"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op3_p1.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op3_p1); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        // Pregunta 2
        datosOpciones[0] = "1"; // ID
        datosOpciones[1] = "2"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op1_p2.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op1_p2); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "2"; // ID
        datosOpciones[1] = "2"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op2_p2.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op2_p2); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "3"; // ID
        datosOpciones[1] = "2"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op3_p2.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op3_p2); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        // Pregunta 3
        datosOpciones[0] = "1"; // ID
        datosOpciones[1] = "3"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op1_p3.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op1_p3); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "2"; // ID
        datosOpciones[1] = "3"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op2_p3.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op2_p3); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "3"; // ID
        datosOpciones[1] = "3"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op3_p3.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op3_p3); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        // Pregunta 4
        datosOpciones[0] = "1"; // ID
        datosOpciones[1] = "4"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op1_p4.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op1_p4); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "2"; // ID
        datosOpciones[1] = "4"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op2_p4.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op2_p4); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "3"; // ID
        datosOpciones[1] = "4"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op3_p4.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op3_p4); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        // Pregunta 5
        datosOpciones[0] = "1"; // ID
        datosOpciones[1] = "5"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op1_p5.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op1_p5); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "2"; // ID
        datosOpciones[1] = "5"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op2_p5.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op2_p5); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "3"; // ID
        datosOpciones[1] = "5"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op3_p5.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op3_p5); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        // Pregunta 6
        datosOpciones[0] = "1"; // ID
        datosOpciones[1] = "6"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op1_p6.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op1_p6); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "2"; // ID
        datosOpciones[1] = "6"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op2_p6.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op2_p6); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "3"; // ID
        datosOpciones[1] = "6"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op3_p6.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op3_p6); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        // Pregunta 7
        datosOpciones[0] = "1"; // ID
        datosOpciones[1] = "7"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op1_p7.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op1_p7); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "2"; // ID
        datosOpciones[1] = "7"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op2_p7.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op2_p7); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "3"; // ID
        datosOpciones[1] = "7"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op3_p7.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op3_p7); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        // Pregunta 8
        datosOpciones[0] = "1"; // ID
        datosOpciones[1] = "8"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op1_p8.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op1_p8); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "2"; // ID
        datosOpciones[1] = "8"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op2_p8.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op2_p8); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "3"; // ID
        datosOpciones[1] = "8"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op3_p8.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op3_p8); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        // Pregunta 9
        datosOpciones[0] = "1"; // ID
        datosOpciones[1] = "9"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op1_p9.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op1_p9); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "2"; // ID
        datosOpciones[1] = "9"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op2_p9.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op2_p9); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "3"; // ID
        datosOpciones[1] = "9"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op3_p9.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op3_p9); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        // Pregunta 10
        datosOpciones[0] = "1"; // ID
        datosOpciones[1] = "10"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op1_p10.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op1_p10); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "2"; // ID
        datosOpciones[1] = "10"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op2_p10.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op2_p10); // CORRECTA?
        listaOpciones.add(datosOpciones);
        datosOpciones = new String [5];
        datosOpciones[0] = "3"; // ID
        datosOpciones[1] = "10"; // ID_PREGUNTA
        datosOpciones[2] = id_curso; // ID_CURSO
        datosOpciones[3] = fv.textoFinal(tv_ec_op3_p10.getText().toString().trim()); // OPCION
        datosOpciones[4] = obtenerOpcionSeleccionada(op3_p10); // CORRECTA?
        listaOpciones.add(datosOpciones);

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
}