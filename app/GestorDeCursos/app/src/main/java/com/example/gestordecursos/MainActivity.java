/**
 * @author sergio Rodríguez Geniz
 */

package com.example.gestordecursos;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {

    TextView usuario = null;
    TextView contra = null;
    FuncionesVarias fv = new FuncionesVarias();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.user);
        contra = findViewById(R.id.pass);

        // Pedimos al usuario los permisos necesarios
        darPermisos();
    }

    /**
     * Metodo que pregunta al usuario si desea conceder permisos a la aplicación o no.
     */
    public void darPermisos(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.MANAGE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permisos, int [] resultado){
        super.onRequestPermissionsResult(requestCode, permisos, resultado);

        if (requestCode == 1) {
            if (resultado.length > 0 && resultado[0] == PackageManager.PERMISSION_GRANTED) {
                //fv.mostrarMensaje(MainActivity.this, "Permisos concedidos. ");
            } else {
                fv.mostrarMensaje(MainActivity.this, "Debe permitir a la aplicacion que lea y escriba en su dispositivo. ");

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }

    }

    /**
     * Metodo que va a la pantalla informacion
     * @param view
     */
    public void pantallaInfo(View view) {
        Intent i = new Intent(this, PantallaInfo.class); // Creamos la instancia de la clase intent para pasar a otra pantalla
        startActivity(i); // Cambiamos de pantalla

        finish(); // Terminamos la pantalla Main
    }

    /**
     * Metodo para ir a la clase de ayuda
     * @param view
     */
    public void pantallaAyuda(View view) {
        Intent i = new Intent(this, ayuda.class); // Creamos la instancia de la clase intent para pasar a otra pantalla
        startActivity(i); // Cambiamos de pantalla
        finish(); // Terminamos la pantalla Main
    }

    /**
     * Metodo para ir a la clase signIn (Crear cuenta)
     * @param view
     */
    public void signIn(View view) {
        Intent i = new Intent(this, signIn.class);
        startActivity(i);
        finish();
    }

    /**
     * Metodo que se ejecuta al pulsar el boton de LogIn y comprueba si los TV tiene texto, y si no salta un error
     * @param v -- View del boton pulsado
     */
    public void checkUsNotNullAndPassNotNull(View v){
        if (fv.contieneTexto(this.usuario.getText().toString().trim()) && fv.contieneTexto(this.contra.getText().toString().trim())) {
            logIn(v);
        }else{
            fv.mostrarMensaje(this, "Rellene ambos campos para poder iniciar sesion. ");
        }
    }

    /**
     * Metodo para hacer LogIn
     * @param v 
     */
    public void logIn(View v) {
        String us = usuario.getText().toString();
        String pass = contra.getText().toString();
        // Comprobamos que ha introducido un usuario y una contraseña
        if (fv.contieneTexto(us) && fv.contieneTexto(pass)) {
            // Hacemos una peticion http para ver si el usuario existe y la contraseña es correcta
            // Obtenemos de la peticion realizada el perfil del usuario
            String perfil = ObtenerPerfil(new ConsultarDatos() {
                @Override
                public void onConsultaExitosa(String rol, String dni) {
                    // Creamos la nueva clase
                    try {
                        // System.out.println(rol);

                        Class c = null;
                        if (rol.equalsIgnoreCase("perfilalumno")) {
                            c = cursosAlumnos.class;
                        } else if (rol.equalsIgnoreCase("perfilgestor")) {
                            c = cursosGestores.class;
                        }
                        if (c == null){
                            fv.mostrarMensaje(MainActivity.this, "El usuario introducido no existe. ");
                        }else {
                            Intent i = new Intent(getApplicationContext(), c); // Creamos la instancia de la clase intent para pasar a otra pantalla
                            i.putExtra("dni", dni);
                            startActivity(i); // Cambiamos de pantalla
                            finish(); // Terminamos la activity en la que estamos
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConsultaError(VolleyError error) {
                    //error.printStackTrace();
                }
            })[0];
        }else{
            mostrarMensaje("Debe rellenar ambos campos. ");
        }
    }

    /**
     * Funcion que realiza una consulta a la API checkLogin y obtiene el perfil del usuario
     * @param consultaDatos
     * @return -- un array de los perfiles obtenidos por la consulta
     */
    public String[] ObtenerPerfil(ConsultarDatos consultaDatos) {
        // Obtenemos los valores de los campos usuario y contra
        String us = usuario.getText().toString();
        String pass = contra.getText().toString();

        if (fv.contieneTexto(us) && fv.contieneTexto(pass)) {

            // URL del API a consultar
            //final String URL = "http://" + getString(R.string.ip) + "/tfg/app/API/checkLogin.php?usuario=" + us + "&contra=" + pass;
            final String URL = fv.getURL()+"checkLogin.php?usuario=" + us + "&contra=" + pass;

            RequestQueue rq = Volley.newRequestQueue(this);

            // Var para devolver la clase siguiente
            String[] perfil = new String[2];

            // Creamos un StringRequest con el metodo GET, al que le pasamos la URL de la API y realizamos la consulta
            StringRequest sr = new StringRequest(Request.Method.GET, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONArray ja = new JSONArray(response);
                                JSONObject json = ja.getJSONObject(0);

                                String perfil_obtenido = json.getString("perfil");
                                String dni = json.getString("dni");
                                perfil[0] = perfil_obtenido.equalsIgnoreCase("a") ? "perfilAlumno" : "perfilGestor";
                                perfil[1] = dni;

                                consultaDatos.onConsultaExitosa(perfil[0], perfil[1]);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            fv.mostrarMensaje(MainActivity.this, "El usuario o la contraseña introducidas contienen un error.");
                            consultaDatos.onConsultaError(error);
                        }
                    });
            // Anadimos la request a la cola
            rq.add(sr);

            return perfil;
        }
        return null;
    }

    /**
     * Metodo para mostrar mensajes
     * @param mensaje -- Mensaje a mostrar
     */
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    /**
     * Metodo para poder iniciar sesion automaticamente
     *
     * FASE DE DESARROLLO SOLO
     *
     * @param v
     */
    public void inicioRapido(View v){
        usuario.setText("angomart");
        contra.setText("ana");
        checkUsNotNullAndPassNotNull(findViewById(R.id.login).getRootView());
    }
}