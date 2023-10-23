package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.List;

public class signIn extends AppCompatActivity {

    Spinner perfiles;
    TextView dni;
    TextView nombre;
    TextView ap1;
    TextView ap2;
    TextView telf;
    TextView email;
    TextView us;
    TextView contra;
    FuncionesVarias fv = new FuncionesVarias();
    String var_existeUsuario = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ArrayAdapter<String> contenidoSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.perfiles));
        dni = findViewById(R.id.dni);
        nombre = findViewById(R.id.nombre);
        ap1 = findViewById(R.id.ape1);
        ap2 = findViewById(R.id.ape2);
        telf = findViewById(R.id.telf);
        email = findViewById(R.id.email);
        us = findViewById(R.id.user_signIn);
        contra = findViewById(R.id.pass_signIn);
        perfiles = findViewById(R.id.perfil);
        perfiles.setAdapter(contenidoSpinner);
    }

    public void insertarUsuario(View v){

        // Obtenemos los valores de los campos
        String dni = this.dni.getText().toString();
        String nombre = this.nombre.getText().toString();
        String ap1 = this.ap1.getText().toString();
        String ap2 = this.ap2.getText().toString();
        String telf = this.telf.getText().toString();
        String email = this.email.getText().toString();
        String us = this.us.getText().toString();
        String contra = this.contra.getText().toString();
        String perfil = this.perfiles.getSelectedItem().toString();

        // Comprobaciones
        if (dni != null && nombre != null && ap1 != null && ap2 != null && telf != null && email != null && us != null && contra != null){ // Ha rellenado todos los campos
            //String existeUsuario = existeUser(us);
            if (!perfil.equalsIgnoreCase("Elija su perfil")){ // Ha elegido un perfil valido
                if (fv.formatoDNI(dni)){// El dni introducido tiene el formato correcto
                    if (fv.esNumerico(telf)){ // el numero de telefono son digitos
                        if (fv.formatoEmail(email)){// El email tiene formato correcto
                            //System.out.println("Existe el usuario: "+existeUsuario);
                            //System.out.println("Existe el usuario VAR: "+var_existeUsuario);
                            if (var_existeUsuario.equalsIgnoreCase("false")){ // comprobamos que no exista el usuario introducido
                                // false si el usuario indicado no existe, true si el usuario existe
                                //fv.mostrarMensaje(this, "el usuario no existe");
                                //System.out.println("Existe el usuario: "+existeUsuario);
                                //System.out.println("Existe el usuario VAR: "+var_existeUsuario);
                                crearUsuario(dni, nombre, ap1, ap2, telf, email, us, contra, perfil); // Insertamos el usuario
                                vueltaLogIn(v); // Volvemos a la pantalla de LogIn
                            }else fv.mostrarMensaje(this, "El usuario introducido existe, porfavor escoja otro usuario. ");
                            var_existeUsuario = null;
                        }else fv.mostrarMensaje(this, "El email introducido no tiene formato correcto. "); // El email no tiene formato correcto
                    }else fv.mostrarMensaje(this, "Debe introducir un numero de telefono correcto. "); // El telefono introducido no es correcto
                }else fv.mostrarMensaje(this, "El DNI introducido no tiene el formato correcto. "); // El dni introducido no es correcto
            }else{// No ah elegido un perfil valido
                fv.mostrarMensaje(this, "Debe elegir un perfil valido. ");
            }
        }else fv.mostrarMensaje(this, "Debe rellenar todos los campos antes de crear un usuario. "); // No ha rellenado todos los campos
    }

    /**
     * Boton para volver a la pantalla de login
     * @param v -- View que se le pasa para finalizar la activity actual
     */
    public void volver(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    /**
     * Funcion que comprueba si el nombre de usuario introducido existe en la BD o no
     * @param user -- usuario a comprobar
     * @return -- True si el usuario existe, False si no.
     */
    public String existeUser(String user){
        final String[] existe = {"false"}; // False si el usuario no existe
        String existeUsuario = obtenerUS(new ConsultarDatos() {
            @Override
            public void onConsultaExitosa(String a) {
                // Creamos la nueva clase
                try {
                    System.out.println("a:"+a);
                    existe[0] = a;
                    var_existeUsuario = a;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onConsultaError(VolleyError error) {
                error.printStackTrace();
            }
        })[0];
        return existe[0];
    }

    public String[] obtenerUS(ConsultarDatos consultaDatos) {

        String user = us.getText().toString();

        // URL del API a consultar
        final String URL = "http://" + getString(R.string.ip) + "/tfg/app/API/checkUser.php?usuario=" + user;

        RequestQueue rq = Volley.newRequestQueue(this);

        // Var para devolver la clase siguiente
        String[] usuario_existente = new String[1];

        // Creamos un StringRequest con el metodo GET, al que le pasamos la URL de la API y realizamos la consulta
        StringRequest sr = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            // JSONArray ja = new JSONArray(response);
                            // JSONObject json = ja.getJSONObject(0);
                            JSONObject json = new JSONObject(response);
                            String usuario_obtenido = json.getString("usuario");
                            System.out.println(usuario_obtenido);
                            // Si el usuario no existe devolvemos FALSE, si el usuario existe devolvemos TRUE
                            if (usuario_obtenido.equalsIgnoreCase("noexiste")){
                                usuario_existente[0] = "false";
                            }else if (usuario_obtenido.equalsIgnoreCase("existe")){
                                usuario_existente[0] = "true";
                                System.out.println(usuario_existente[0]);
                            }

                            consultaDatos.onConsultaExitosa(usuario_existente[0]);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        consultaDatos.onConsultaError(error);
                    }
                });
        // Anadimos la request a la cola
        rq.add(sr);

        return usuario_existente;
    }

    public void crearUsuario
            (String dni, String nombre, String ap1, String ap2, String telf, String email, String us, String contra, String perfil)
    {
        final String URL = "http://" + getString(R.string.ip) + "/tfg/app/API/newPerson.php";

        StringRequest sr = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        fv.mostrarMensaje(signIn.this, "Usuario creado correctamente. ");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        fv.mostrarMensaje(signIn.this, "No se pudo crear el usuario. ");
                        error.printStackTrace();
                    }
                }) {
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody() {
                JSONObject json = new JSONObject();
                try {
                    // ponemos los valores de los campos a introducir
                    json.put("dni", dni);
                    json.put("nombre", nombre);
                    json.put("apellido1", ap1);
                    json.put("apellido2", ap2);
                    json.put("telefono", telf);
                    json.put("email", email);
                    json.put("usuario", us);
                    json.put("contra", contra);
                    json.put("perfil", perfil);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return json.toString().getBytes();
            }
        };

        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(sr);
    }

    /**
     * Funcion para volver a la pantalla de Login
     * @param v
     */
    public void vueltaLogIn(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}