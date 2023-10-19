/**
 * @author sergio Rodríguez Geniz
 */

package com.example.gestordecursos;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView usuario = null;
    TextView contra = null;
    PeticionesHTTP peticion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.user);
        contra = findViewById(R.id.pass);
    }
    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // feature requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            });

    public void pantallaInfo(View view) {
        Intent i = new Intent(this, PantallaInfo.class); // Creamos la instancia de la clase intent para pasar a otra pantalla
        startActivity(i); // Cambiamos de pantalla

        finish(); // Terminamos la pantalla Main
    }

    public void logIn(View v) {

        // Hacemos una peticion http para ver si el usuario existe y la contraseña es correcta
        // Obtenemos de la peticion realizada el perfil del usuario
        ObtenerPerfil(new ConsultarDatosCallback() {
            @Override
            public void onConsultaExitosa(String rol) {
                // Creamos la nueva clase
                try {
                    Class clase = Class.forName("com.example.gestordecursosr"+rol);

                    Intent i = new Intent(getApplicationContext(), clase); // Creamos la instancia de la clase intent para pasar a otra pantalla
                    startActivity(i); // Cambiamos de pantalla
                    finish(); // Terminamos la activity en la que estamos
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onConsultaError(VolleyError error) {}
        });


        // Hacemos una peticion http para ver si el usuario existe y la contraseña es correcta
        // Obtenemos de la peticion realizada el perfil del usuario

    }

    public interface ConsultarDatosCallback {
        void onConsultaExitosa(String rol);

        void onConsultaError(VolleyError error);
    }

    public String[] ObtenerPerfil(ConsultarDatosCallback callback) {
        String us = String.valueOf(usuario.getText());
        String pass = String.valueOf(contra.getText());

        final String URL = "http://" + getString(R.string.ip) + "/tfg/app/API/checkLogin.php?usuario=" + us + "&contra=" + pass;
        String[] perfil = new String[1];
        StringRequest sr = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject json = null;
                        try {
                            json = new JSONObject(response);

                            String perfil_obtenido = json.getString("perfil");
                            perfil[0] = perfil_obtenido.equalsIgnoreCase("a")? "perfilAlumno" : "perfilGestor";

                            callback.onConsultaExitosa(perfil[0]);
                        }catch (JSONException e){
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        callback.onConsultaError(error);
                    }
                });
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(sr);

        return perfil;
    }
}
