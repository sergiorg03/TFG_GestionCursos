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
 * Clase para mostrar las notas obtenidas por un alumno
 */
public class pantallaNotasCursos extends AppCompatActivity {

    FuncionesVarias fv = new FuncionesVarias();
    String dni;
    String idcurso;
    String nombreCurso;
    String clase;
    Class claseAnterior;

    TextView pnc_nombreCurso;
    TextView tv_f1;
    TextView tv_nota1;
    TextView tv_f2;
    TextView tv_nota2;
    TextView tv_f3;
    TextView tv_nota3;
    TextView tv_f4;
    TextView tv_nota4;
    TextView tv_f5;
    TextView tv_nota5;
    TextView tv_f6;
    TextView tv_nota6;
    TextView tv_f7;
    TextView tv_nota7;
    TextView tv_f8;
    TextView tv_nota8;
    TextView tv_f9;
    TextView tv_nota9;
    TextView tv_f10;
    TextView tv_nota10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_notas_cursos);

        dni = getIntent().getStringExtra("dni");
        idcurso = getIntent().getStringExtra("idcurso");
        nombreCurso = getIntent().getStringExtra("nombreCurso");
        clase = getIntent().getStringExtra("clase");
        claseAnterior = fv.obtenerClase(clase);

        iniciarVars();
    }

    /**
     * Metodo para inicializar las variables
     */
    public void iniciarVars(){
        // textviews
        pnc_nombreCurso = findViewById(R.id.pnc_nombreCurso);
        pnc_nombreCurso.setText(nombreCurso);
        tv_f1       = findViewById(R.id.tv_f1);
        tv_nota1    = findViewById(R.id.tv_nota1);
        tv_f2       = findViewById(R.id.tv_f2);
        tv_nota2    = findViewById(R.id.tv_nota2);
        tv_f3       = findViewById(R.id.tv_f3);
        tv_nota3    = findViewById(R.id.tv_nota3);
        tv_f4       = findViewById(R.id.tv_f4);
        tv_nota4    = findViewById(R.id.tv_nota4);
        tv_f5       = findViewById(R.id.tv_f5);
        tv_nota5    = findViewById(R.id.tv_nota5);
        tv_f6       = findViewById(R.id.tv_f6);
        tv_nota6    = findViewById(R.id.tv_nota6);
        tv_f7       = findViewById(R.id.tv_f7);
        tv_nota7    = findViewById(R.id.tv_nota7);
        tv_f8       = findViewById(R.id.tv_f8);
        tv_nota8    = findViewById(R.id.tv_nota8);
        tv_f9       = findViewById(R.id.tv_f9);
        tv_nota9    = findViewById(R.id.tv_nota9);
        tv_f10      = findViewById(R.id.tv_f10);
        tv_nota10   = findViewById(R.id.tv_nota10);

        tv_f1.setText("");
        tv_nota1.setText("");
        tv_f2.setText("");
        tv_nota2.setText("");
        tv_f3.setText("");
        tv_nota3.setText("");
        tv_f4.setText("");
        tv_nota4.setText("");
        tv_f5.setText("");
        tv_nota5.setText("");
        tv_f6.setText("");
        tv_nota6.setText("");
        tv_f7.setText("");
        tv_nota7.setText("");
        tv_f8.setText("");
        tv_nota8.setText("");
        tv_f9.setText("");
        tv_nota9.setText("");
        tv_f10.setText("");
        tv_nota10.setText("");

        tv_f1.setHint("");
        tv_nota1.setHint("");
        tv_f2.setHint("");
        tv_nota2.setHint("");
        tv_f3.setHint("");
        tv_nota3.setHint("");
        tv_f4.setHint("");
        tv_nota4.setHint("");
        tv_f5.setHint("");
        tv_nota5.setHint("");
        tv_f6.setHint("");
        tv_nota6.setHint("");
        tv_f7.setHint("");
        tv_nota7.setHint("");
        tv_f8.setHint("");
        tv_nota8.setHint("");
        tv_f9.setHint("");
        tv_nota9.setHint("");
        tv_f10.setHint("");
        tv_nota10.setHint("");

        obtenerDatos();
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

    /**
     * Metodo para ir al perfil
     * @param v -- View del boton pulsado
     */
    public void perfil(View v){
        Intent i = new Intent(this, Perfil.class);
        i.putExtra("dni", dni);
        i.putExtra("clase", "notasalumnos");
        startActivity(i);
        finish();
    }

    public interface ConsultarDatos{
        void onConsultaExitosa(List<String[]> listaNotas);
        void onConsultaError(VolleyError e);
    }

    /**
     * Metodo que muestra las notas de los alumnos
     */
    public List<String[]> mostrarDatos(ConsultarDatos cd){
        final String URL = fv.getURL()+"getAllMarksForStudent.php?dni="+dni.trim()+"&id_curso="+ idcurso.trim();

        RequestQueue rq = Volley.newRequestQueue(this);

        List<String[]> lista = new ArrayList<>();

        StringRequest sr = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray ja = new JSONArray(response);

                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject json = ja.getJSONObject(i);
                                String [] fechaYNota = new String []{json.getString("lastModifiedDate"), json.getString("puntuacion")};
                                lista.add(fechaYNota);
                                /*switch(i){
                                    case 0:
                                        tv_f1.setText(json.getString("lastModifiedDate"));
                                        tv_nota1.setText(json.getString("puntuacion"));
                                        break;
                                    case 1:
                                        tv_f2.setText(json.getString("lastModifiedDate"));
                                        tv_nota2.setText(json.getString("puntuacion"));
                                        break;
                                    case 2:
                                        tv_f3.setText(json.getString("lastModifiedDate"));
                                        tv_nota3.setText(json.getString("puntuacion"));
                                        break;
                                    case 3:
                                        tv_f4.setText(json.getString("lastModifiedDate"));
                                        tv_nota4.setText(json.getString("puntuacion"));
                                        break;
                                    case 4:
                                        tv_f5.setText(json.getString("lastModifiedDate"));
                                        tv_nota5.setText(json.getString("puntuacion"));
                                        break;
                                     case 5:
                                        tv_f6.setText(json.getString("lastModifiedDate"));
                                        tv_nota6.setText(json.getString("puntuacion"));
                                        break;
                                    case 6:
                                        tv_f7.setText(json.getString("lastModifiedDate"));
                                        tv_nota7.setText(json.getString("puntuacion"));
                                        break;
                                    case 7:
                                        tv_f8.setText(json.getString("lastModifiedDate"));
                                        tv_nota8.setText(json.getString("puntuacion"));
                                        break;
                                    case 8:
                                        tv_f9.setText(json.getString("lastModifiedDate"));
                                        tv_nota9.setText(json.getString("puntuacion"));
                                        break;
                                    case 9:
                                        tv_f10.setText(json.getString("lastModifiedDate"));
                                        tv_nota10.setText(json.getString("puntuacion"));
                                        break;
                                }*/
                            }
                            cd.onConsultaExitosa(lista);
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                cd.onConsultaError(error);
            }
        });

        rq.add(sr);
        return lista;
    }

    public void obtenerDatos(){
        List<String[]> listaFecYNotas = mostrarDatos(new ConsultarDatos() {
            @Override
            public void onConsultaExitosa(List<String[]> listaNotas) {
                for(int i = 0; i < listaNotas.size(); i++){
                    // mostramos los datos obtenidos
                    switch(i){
                        case 0:
                            tv_f1   .setText(listaNotas.get(i)[0]);
                            tv_nota1.setText(listaNotas.get(i)[1]);
                            break;
                        case 1:
                            tv_f2   .setText(listaNotas.get(i)[0]);
                            tv_nota2.setText(listaNotas.get(i)[1]);
                            break;
                        case 2:
                            tv_f3   .setText(listaNotas.get(i)[0]);
                            tv_nota3.setText(listaNotas.get(i)[1]);
                            break;
                        case 3:
                            tv_f4   .setText(listaNotas.get(i)[0]);
                            tv_nota4.setText(listaNotas.get(i)[1]);
                            break;
                        case 4:
                            tv_f5   .setText(listaNotas.get(i)[0]);
                            tv_nota5.setText(listaNotas.get(i)[1]);
                            break;
                         case 5:
                            tv_f6   .setText(listaNotas.get(i)[0]);
                            tv_nota6.setText(listaNotas.get(i)[1]);
                            break;
                        case 6:
                            tv_f7   .setText(listaNotas.get(i)[0]);
                            tv_nota7.setText(listaNotas.get(i)[1]);
                            break;
                        case 7:
                            tv_f8   .setText(listaNotas.get(i)[0]);
                            tv_nota8.setText(listaNotas.get(i)[1]);
                            break;
                        case 8:
                            tv_f9   .setText(listaNotas.get(i)[0]);
                            tv_nota9.setText(listaNotas.get(i)[1]);
                            break;
                        case 9:
                            tv_f10   .setText(listaNotas.get(i)[0]);
                            tv_nota10.setText(listaNotas.get(i)[1]);
                            break;
                    }
                }
            }

            @Override
            public void onConsultaError(VolleyError e) {
                pnc_nombreCurso.setText("No hay datos a mostrar. ");
                e.printStackTrace();
            }
        });
    }
}