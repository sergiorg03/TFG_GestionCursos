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

import java.util.ArrayList;
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
        dni_profesor = (getIntent().getStringExtra("dni_g") == null)? getIntent().getStringExtra("dni") : getIntent().getStringExtra("dni_g");
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

        tv_dni1 .setVisibility(View.INVISIBLE);
        tv_dni2 .setVisibility(View.INVISIBLE);
        tv_dni3 .setVisibility(View.INVISIBLE);
        tv_dni4 .setVisibility(View.INVISIBLE);
        tv_dni5 .setVisibility(View.INVISIBLE);
        tv_dni6 .setVisibility(View.INVISIBLE);
        tv_dni7 .setVisibility(View.INVISIBLE);
        tv_dni8 .setVisibility(View.INVISIBLE);
        tv_dni9 .setVisibility(View.INVISIBLE);
        tv_dni10.setVisibility(View.INVISIBLE);
        tv_dni11.setVisibility(View.INVISIBLE);
        tv_dni12.setVisibility(View.INVISIBLE);
        tv_dni13.setVisibility(View.INVISIBLE);
        tv_dni14.setVisibility(View.INVISIBLE);
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
        Intent i = new Intent(this, PerfilAlumnos.class);
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
        void onConsultaExitosa(List<String> dnis);
        void onConsultaError(VolleyError error);
    }

    /**
     *Metodo que obtiene los DNIs y los muestra los DNI
     */
    public void getDNIs(){

        List<String> dnis = getDNIAlumnos(new ConsultarDatos() {
            @Override
            public void onConsultaExitosa(List<String> lista_dnis) {
                for (int i = 0; i < lista_dnis.size(); i++) {
                    switch (i){
                        case 0:
                            tv_dni1.setText(lista_dnis.get(i));
                            tv_dni1.setVisibility(View.VISIBLE);
                            setListeners(tv_dni1);
                            System.out.println("gestoresAlumnos: getDNIs: tv_DNI1: "+ tv_dni1.getText().toString());
                            break;
                        case 1:
                            tv_dni2.setText(lista_dnis.get(i));
                            tv_dni2.setVisibility(View.VISIBLE);
                            setListeners(tv_dni2);
                            break;
                        case 2:
                            tv_dni3.setText(lista_dnis.get(i));
                            tv_dni3.setVisibility(View.VISIBLE);
                            setListeners(tv_dni3);
                            break;
                        case 3:
                            tv_dni4.setText(lista_dnis.get(i));
                            tv_dni4.setVisibility(View.VISIBLE);
                            setListeners(tv_dni4);
                            break;
                        case 4:
                            tv_dni5.setText(lista_dnis.get(i));
                            tv_dni5.setVisibility(View.VISIBLE);
                            setListeners(tv_dni5);
                            break;
                        case 5:
                            tv_dni6.setText(lista_dnis.get(i));
                            tv_dni6.setVisibility(View.VISIBLE);
                            setListeners(tv_dni6);
                            break;
                        case 6:
                            tv_dni7.setText(lista_dnis.get(i));
                            tv_dni7.setVisibility(View.VISIBLE);
                            setListeners(tv_dni7);
                            break;
                        case 7:
                            tv_dni8.setText(lista_dnis.get(i));
                            tv_dni8.setVisibility(View.VISIBLE);
                            setListeners(tv_dni8);
                            break;
                        case 8:
                            tv_dni9.setText(lista_dnis.get(i));
                            tv_dni9.setVisibility(View.VISIBLE);
                            setListeners(tv_dni9);
                            break;
                        case 9:
                            tv_dni10.setText(lista_dnis.get(i));
                            tv_dni10.setVisibility(View.VISIBLE);
                            setListeners(tv_dni10);
                            break;
                        case 10:
                            tv_dni11.setText(lista_dnis.get(i));
                            tv_dni11.setVisibility(View.VISIBLE);
                            setListeners(tv_dni11);
                            break;
                        case 11:
                            tv_dni12.setText(lista_dnis.get(i));
                            tv_dni12.setVisibility(View.VISIBLE);
                            setListeners(tv_dni12);
                            break;
                        case 12:
                            tv_dni13.setText(lista_dnis.get(i));
                            tv_dni13.setVisibility(View.VISIBLE);
                            setListeners(tv_dni13);
                            break;
                        case 13:
                            tv_dni14.setText(lista_dnis.get(i));
                            tv_dni14.setVisibility(View.VISIBLE);
                            setListeners(tv_dni14);
                            break;
                    }
                }
            }

            @Override
            public void onConsultaError(VolleyError error) {
                fv.mostrarMensaje(gestoresAlumnos.this, "No se pudieron recaudar datos. ");
            }
        });
    }

    /**
     * Metodo que obtiene todos los DNI de los alumnos
     * @param cd -- Interfaz utilizada para los datos
     * @return -- Lista de dni
     */
    public List<String> getDNIAlumnos(ConsultarDatos cd){
        List<String> dnis = new ArrayList<>();
        final String URL = fv.getURL()+"getAllUsers.php";

        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest sr = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray ja = new JSONArray(response);
                            for (int i = 0; i < ja.length(); i++) {
                                JSONArray ja1 = ja.getJSONArray(i);
                                JSONObject jo = ja1.getJSONObject(0);
                                String dni = jo.getString("dni");
                                dnis.add(dni);
                            }
                            cd.onConsultaExitosa(dnis);
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

    public void setListeners(TextView tv){
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perfil_alumno(tv.getText().toString());
            }
        });
    }
}