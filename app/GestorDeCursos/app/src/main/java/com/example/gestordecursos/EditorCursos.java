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
                existe = false;
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
            System.out.println("EditorCursos: seleccionarOpcionCor: op1 correcta. "+r1.isSelected());
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

        final String URL = "http://"+getString(R.string.ip)+"/tfg/app/API/getTest.php?idCurso="+id_curso;

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
        if (!this.existe) {
            crearTest();
        }else modificarTest();
    }

    /**
     * Metodo para crear los test
     */
    public void crearTest(){

    }

    /**
     * Metodo para modificar el test de un curso
     */
    public void modificarTest(){

    }
}