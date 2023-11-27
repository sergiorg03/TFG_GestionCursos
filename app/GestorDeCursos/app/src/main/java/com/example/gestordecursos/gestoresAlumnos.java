package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.util.List;

/**
 *
 * Clase para ver los DNI de los alumnos
 *
 */
public class gestoresAlumnos extends AppCompatActivity {

    FuncionesVarias fv = new FuncionesVarias();
    String dni_profesor;
    String clase;
    Class claseAnterior;

    //TextViews
    TextView tv_dni1;
    TextView tv_dni2;
    TextView tv_dni3;
    TextView tv_dni4;
    TextView tv_dni5;
    TextView tv_dni6;
    TextView tv_dni7;
    TextView tv_dni8;
    TextView tv_dni9;
    TextView tv_dni10;
    TextView tv_dni11;
    TextView tv_dni12;
    TextView tv_dni13;
    TextView tv_dni14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestores_alumnos);
        dni_profesor = getIntent().getStringExtra("dni_g");
        clase = getIntent().getStringExtra("clase");
        claseAnterior = fv.obtenerClase(clase);

        inicializarVariables();
        getDNIs();
    }

    /**
     * Metodo para inicializar las variables
     */
    public void inicializarVariables(){
        tv_dni1     = findViewById(R.id.tv_dni1);
        tv_dni2     = findViewById(R.id.tv_dni2);
        tv_dni3     = findViewById(R.id.tv_dni3);
        tv_dni4     = findViewById(R.id.tv_dni4);
        tv_dni5     = findViewById(R.id.tv_dni5);
        tv_dni6     = findViewById(R.id.tv_dni6);
        tv_dni7     = findViewById(R.id.tv_dni7);
        tv_dni8     = findViewById(R.id.tv_dni8);
        tv_dni9     = findViewById(R.id.tv_dni9);
        tv_dni10    = findViewById(R.id.tv_dni10);
        tv_dni11    = findViewById(R.id.tv_dni11);
        tv_dni12    = findViewById(R.id.tv_dni12);
        tv_dni13    = findViewById(R.id.tv_dni13);
        tv_dni14    = findViewById(R.id.tv_dni14);
    }

    /**
     * Metodo para ir a la pantalla anterior (cursosGestores)
     * @param v
     */
    public void volver(View v){
        Intent i = new Intent(this, claseAnterior);
        i.putExtra("dni", dni_profesor);
        startActivity(i);
        finish();
    }

    /**
     * Metodo para ir a la pantalla de perfil
     * @param v
     */
    public void perfil(View v){
        Intent i = new Intent(this, Perfil.class);
        i.putExtra("dni", dni_profesor);
        i.putExtra("clase", "cursosgestores");
        startActivity(i);
        finish();
    }

    /**
     * Metodo para ir a la pantalla de perfil
     * @param dni_a -- DNI del alumno a modificar.
     */
    public void perfil_alumno(String dni_a){
        Intent i = new Intent(this, Perfil.class);
        i.putExtra("dni_g", dni_profesor);
        i.putExtra("dni_a", dni_a);
        i.putExtra("clase", "gestoresalumnos");
        startActivity(i);
        finish();
    }

    /**
     * Interfaz para la obtencion de los DNIs
     */
    public interface ConsultarDatos{
        void onConsultaExitosa(String[] dnis);
        void onConsultaError(VolleyError error);
    }

    /**
     *Metodo que muestra los DNI
     */
    public void getDNIs(){

        List<String> dnis = getDNIAlumnos(new ConsultarDatos(){

            @Override
            public void onConsultaExitosa(String[] dnis) {

            }

            @Override
            public void onConsultaError(VolleyError error) {

            }
        });
    }

    public List<String> getDNIAlumnos(ConsultarDatos cd){
        List<String> dnis = null;
        final String URL = fv.getURL()+"getAllUsers.php";

        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest sr = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray ja = new JSONArray(response);
                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject jo = ja.getJSONObject(i);
                                String dni = jo.getString("dni");
                                dnis.add(dni);
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Se produjo un error a la hora de obtener los datos de los alumnos. ");
                        cd.onConsultaError(error);
                    }
                });

        rq.add(sr);
        return dnis;
    }
}