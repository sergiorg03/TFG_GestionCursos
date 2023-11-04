package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

/**
 *
 * Clase que muestra el perfil de la persona
 *
 */
public class Perfil extends AppCompatActivity {

    FuncionesVarias fv = new FuncionesVarias();
    String dni;
    String nombreClase;
    Class claseAnterior;
    // Variables para guardar los datos de la base de datos
    String bd_nombre;
    String bd_ape1;
    String bd_ape2;
    String bd_telf;
    String bd_email;
    String bd_us;
    String bd_contra;
    String bd_perfil;

    // Atributos de la pantalla
    TextView tv_dni;
    EditText et_nombre;
    EditText et_ap1;
    EditText et_ap2;
    EditText et_telf;
    EditText et_email;
    TextView et_us;
    EditText et_contra;
    TextView et_perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        dni = getIntent().getStringExtra("dni");
        nombreClase = getIntent().getStringExtra("clase");
        claseAnterior = fv.obtenerClase(nombreClase);

        // Asignamos los EditText a cada View
        tv_dni = findViewById(R.id.dni_perfil);
        et_nombre = findViewById(R.id.nombre_p);
        et_ap1 = findViewById(R.id.apellido1_p);
        et_ap2 = findViewById(R.id.apellido2_p);
        et_telf = findViewById(R.id.telefono_p);
        et_email = findViewById(R.id.email_p);
        et_us = findViewById(R.id.usuario_p);
        et_contra = findViewById(R.id.contra_p);
        et_perfil = findViewById(R.id.perfil_p);

        // Asignamos el valor al TV del dni
        tv_dni.setText(dni);

        // mostramos los demas datos
        mostrarDatos();
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

    public interface ConsultarDatos{
        void onConsultaExitosa(String [] listaDatos);
        void onConsultaError(VolleyError ve);
    }

    /**
     * Metodo para mostrar los datos de la persona
     */
    public void mostrarDatos(){
        String [] datosPersonales = getDatos(new ConsultarDatos() {
            @Override
            public void onConsultaExitosa(String[] listaDatos) {
                bd_nombre = listaDatos[1];
                bd_ape1 = listaDatos[2];
                bd_ape2 = listaDatos[3];
                bd_telf = listaDatos[4];
                bd_email = listaDatos[5];
                bd_us = listaDatos[6];
                bd_contra = listaDatos[7];
                bd_perfil = listaDatos[8];

                // Mostramos los datos
                et_nombre.setText(listaDatos[1]);
                et_ap1.setText(listaDatos[2]);
                et_ap2.setText(listaDatos[3]);
                et_telf.setText(listaDatos[4]);
                et_email.setText(listaDatos[5]);
                et_us.setText(listaDatos[6]);
                et_perfil.setText(listaDatos[8].equalsIgnoreCase("a")? "Alumno": "Gestor");
            }

            @Override
            public void onConsultaError(VolleyError ve) {
                fv.mostrarMensaje(Perfil.this, "No se pudieron recopilar datos. ");
            }
        });
    }

    /**
     * Metodo para mostrar los datos del usuario
     */
    public String[] getDatos(ConsultarDatos cd){
        final String URL = "http://"+getString(R.string.ip)+"/tfg/app/API/getProfileData.php?dni="+this.dni;

        RequestQueue rq = Volley.newRequestQueue(this);

        String[] datosPersona = new String[9];

        StringRequest sr = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray ja = new JSONArray(response);
                            JSONObject json = ja.getJSONObject(0);
                            datosPersona[0] = json.getString("dni");
                            datosPersona[1] = json.getString("nombre");
                            datosPersona[2] = json.getString("apellido1");
                            datosPersona[3] = json.getString("apellido2");
                            datosPersona[4] = json.getString("telefono");
                            datosPersona[5] = json.getString("email");
                            datosPersona[6] = json.getString("usuario");
                            datosPersona[7] = json.getString("contra");
                            datosPersona[8] = json.getString("perfil");

                            cd.onConsultaExitosa(datosPersona);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                cd.onConsultaError(error);
            }
        });

        //Añadimos la request a la cola
        rq.add(sr);
        return datosPersona;
    }

    /**
     * Método para modificar el perfil
     * @param v
     */
    public void updateProfile(View v){
        modificarDatosPerfil();
    }

    /**
     * Metodo para la comprobacion de los valores
     */
    public String[] asignarValoresDeCambio(){
        String nombre = "", ap1 = "", ap2 = "", em = "", telf = "", contra = "";
        if (bd_nombre.equalsIgnoreCase(et_nombre.getText().toString())){ // El nombre es el mismo
            nombre = bd_nombre;
        }else{ // El nombre es diferente
            nombre = et_nombre.getText().toString();
        }

        if(bd_ape1.equalsIgnoreCase(et_ap1.getText().toString())){ // El primer apellido es el mismo, no se ha cambiado
            ap1 = bd_ape1;
        }else{ //Se ha cambiado el apellido
            ap1 = et_ap1.getText().toString();
        }

        if (bd_ape2.equalsIgnoreCase(et_ap2.getText().toString())){ // El segundo apellido es el mismo, no se ha cambiado
            ap2 = bd_ape2;
        }else{ // Se  ha modificado
            ap2 = et_ap2.getText().toString();
        }

        if (bd_telf.equalsIgnoreCase(et_telf.getText().toString())){ // El telefono no se ha cambiado
            telf = bd_telf;
        }else{ // Se ha cambiado el telefono
            telf = et_telf.getText().toString();
        }

        if (bd_email.equalsIgnoreCase(et_email.getText().toString())){ // No se ha cambiado el email
            em = bd_email;
        }else{// Se ha cambiado el email
            if (fv.formatoEmail(et_email.getText().toString())) { // El formato del email es correcto
                em = et_email.getText().toString();
            }else{
                fv.mostrarMensaje(this, "El formato del email es incorrecto. ");
            }
        }

        if (fv.contieneTexto(et_contra.getText().toString())){ // La contraseña está modificada
            contra = bd_contra;
        }

        String [] datos = new String[]{nombre, ap1, ap2, telf, em, contra};
        return datos;
    }

    /**
     * Metodo para modificar los datos del perfil
     */
    public void modificarDatosPerfil(){

        // Nuevos valores para modificar
        String [] datosNuevos = asignarValoresDeCambio();

        final String URL = "http://"+getString(R.string.ip)+"/tfg/app/API/updateProfileData.php";

        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest sr = new StringRequest(Request.Method.PUT, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        fv.mostrarMensaje(Perfil.this, "Perfil modificado correctamente. ");
                        Intent i = new Intent(Perfil.this, Perfil.class);
                        i.putExtra("dni", dni);
                        i.putExtra("clase", nombreClase);
                        startActivity(i);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                fv.mostrarMensaje(Perfil.this, "No se pudo  modificar el perfil. ");
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
                    jsonBody.put("nombre", datosNuevos[0]);
                    jsonBody.put("apellido1", datosNuevos[1]);
                    jsonBody.put("apellido2", datosNuevos[2]);
                    jsonBody.put("telefono", datosNuevos[3]);
                    jsonBody.put("email", datosNuevos[4]);
                    jsonBody.put("contra", datosNuevos[5]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return jsonBody.toString().getBytes();
            }
        };
        // Añadimos la query a la cola
        rq.add(sr);
    }

    public void deleteProfile(View v){
        final String URL = "http://"+getString(R.string.ip)+"/tfg/app/API/deleteProfile.php";
        System.out.println("dni-->"+dni);

        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest sr = new StringRequest(/*Request.Method.DELETE*/ Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        fv.mostrarMensaje(Perfil.this, "Perfil borrado correctamente. ");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        fv.mostrarMensaje(Perfil.this, "No se pudo borrar el perfil. ");
                    }
                }) {
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody() {
                JSONObject jsonBody = null;
                try {
                    jsonBody = new JSONObject();
                    jsonBody.put("dni", dni);
                    System.out.println(jsonBody);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return jsonBody.toString().getBytes();
            }
        };

        rq.add(sr);

        salir();
    }

    public void salir(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}