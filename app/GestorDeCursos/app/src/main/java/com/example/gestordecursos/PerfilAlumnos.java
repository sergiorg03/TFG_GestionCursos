package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

public class PerfilAlumnos extends AppCompatActivity {

    FuncionesVarias fv = new FuncionesVarias();
    String dni_g;
    String dni_a;
    String clase;
    Class claseAnterior;
    List<String> usuarios = new ArrayList<>();
    List<String> telefonos = new ArrayList<>();

    // Variables para guardar los datos de la base de datos
    String bd_dni;
    String bd_nombre;
    String bd_ape1;
    String bd_ape2;
    String bd_telf;
    String bd_email;
    String bd_us;
    String bd_contra;
    String bd_perfil;
    int bd_posPerfil;

    // Atributos de la pantalla
    EditText et_dni;
    EditText et_nombre;
    EditText et_ap1;
    EditText et_ap2;
    EditText et_telf;
    EditText et_email;
    TextView et_us;
    EditText et_contra;
    Spinner perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_alumnos);

        dni_g = getIntent().getStringExtra("dni_g");
        dni_a = getIntent().getStringExtra("dni_a");
        clase = getIntent().getStringExtra("clase");
        System.out.println("SRG: PerfilAlumnos: clase: "+clase);
        claseAnterior = fv.obtenerClase(clase);

        iniciarVars();
    }

    /**
     * Metodo para inicializar todas las variables
     */
    public void iniciarVars(){
        ArrayAdapter<String> contenidoSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.perfiles_pa));
        perfil = findViewById(R.id.perfil_pa);
        perfil.setAdapter(contenidoSpinner);

        et_dni = findViewById(R.id.dni_pa);
        et_nombre   = findViewById(R.id.nombre_pa);
        et_ap1  = findViewById(R.id.apellido1_pa);
        et_ap2  = findViewById(R.id.apellido2_pa);
        et_telf = findViewById(R.id.telefono_pa);
        et_email    = findViewById(R.id.email_pa);
        et_us   = findViewById(R.id.usuario_pa);
        et_contra   = findViewById(R.id.contra_pa);

        obtenerDatos();
        obtenerUsuariosYTelfs();
    }

    /**
     * Metodo para ir a la pantalla anterior (cursosGestores)
     * @param v
     */
    public void volver(View v){
        Intent i = new Intent(this, claseAnterior);
        i.putExtra("dni", dni_g);
        i.putExtra("clase", "cursosGestores");
        startActivity(i);
        finish();
    }

    /**
     * Metodo que pide confirmación al usuario para borrar su perfil
     * @param v -- View del ImageButton pulsado
     */
    public void deleteProfile(View v){
        // Creamos la pantalla de alerta
        AlertDialog.Builder adb = new AlertDialog.Builder(PerfilAlumnos.this);
        adb.setTitle("Confirmacion borrado del perfil. ")
                .setMessage("¿Está seguro que desea borrar perfil del alumno? \nSe perderan todos sus datos")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { // El usuario ha pulsado el boton SI
                        System.out.println("Se borra el perfil. ");
                        // Procedemos a llamar a la API para el borrado del perfil.
                        System.out.println("Perfil: deleteProfile: aceptada: "+true);
                        confirmarBorradoPerfil(); // Borramos el perfil
                    }
                }).setNegativeButton("No", null).show();
    }

    /**
     * Metodo que lleva a cabo la llamada a la API para el borrado del perfil del usuario
     */
    protected void confirmarBorradoPerfil(){

        final String URL = fv.getURL()+"deleteProfile.php";

        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest sr = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        fv.mostrarMensaje(PerfilAlumnos.this, "Perfil borrado correctamente. ");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        //fv.guardadoLogs(error.toString(), "perfil_BorradoPerfil");
                        fv.mostrarMensaje(PerfilAlumnos.this, "No se pudo borrar el perfil. ");
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
                    jsonBody.put("dni", dni_a);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return jsonBody.toString().getBytes();
            }
        };

        rq.add(sr);

        salir();
    }

    /**
     * Metodo que sale a la pantalla de login una vez borrado el perfil
     */
    public void salir(){
        Intent i = new Intent(this, gestoresAlumnos.class);
        i.putExtra("dni", dni_g);
        i.putExtra("clase", "cursosgestores");
        startActivity(i);
        finish();
    }

    public interface ConsultarDatos{
        void onConsultaExitosa(String[] datosPersonas);
        void onConsultaError(VolleyError e);
    }

    /**
     * Metodo para mostrar los datos de la persona
     */
    public void obtenerDatos(){

        String [] datosPersonales = getDatos(new ConsultarDatos() {
            @Override
            public void onConsultaExitosa(String[] listaDatos) {
                bd_dni = listaDatos[0];
                bd_nombre = listaDatos[1];
                bd_ape1 = listaDatos[2];
                bd_ape2 = listaDatos[3];
                bd_telf = listaDatos[4];
                bd_email = listaDatos[5];
                bd_us = listaDatos[6];
                bd_contra = listaDatos[7];
                bd_perfil = listaDatos[8];

                // Mostramos los datos
                et_dni.setText(listaDatos[0]);
                et_nombre.setText(listaDatos[1]);
                et_ap1.setText(listaDatos[2]);
                et_ap2.setText(listaDatos[3]);
                et_telf.setText(listaDatos[4]);
                et_email.setText(listaDatos[5]);
                et_us.setText(listaDatos[6]);
                perfil.setSelection(listaDatos[8].equalsIgnoreCase("a")? 1: 0);
                bd_posPerfil = listaDatos[8].equalsIgnoreCase("a")? 1: 0;
            }

            @Override
            public void onConsultaError(VolleyError ve) {
                fv.mostrarMensaje(PerfilAlumnos.this, "No se pudieron recopilar datos. ");
            }
        });
    }

    /**
     * Metodo para mostrar los datos del usuario
     */
    public String[] getDatos(ConsultarDatos cd){
        final String URL = fv.getURL()+"getProfileData.php?dni="+this.dni_a.trim();

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
     * Metodo que obtiene los datos a modificar
     * @return -- Devuelve una lista con los valores que van a ser actualizados.
     */
    public String [] obtenerNuevosDatos(){
        String dni = "", nombre = "", ap1 = "", ap2 = "", telf = "", em = "", us = "", contra = "", profile = "";

        // Comprobacion del DNI
        if (fv.formatoDNI(et_dni.getText().toString())){
            if (bd_dni.equalsIgnoreCase(et_dni.getText().toString())){
                dni = bd_dni;
            }else{
                dni = et_dni.getText().toString();
            }
        }else dni = bd_dni;

        // Comprobación del nombre
        if (bd_nombre.equalsIgnoreCase(et_nombre.getText().toString())){ // El nombre es el mismo
            nombre = bd_nombre;
        }else{ // El nombre es diferente
            nombre = et_nombre.getText().toString();
        }

        // Comprobación del primer apellido
        if(bd_ape1.equalsIgnoreCase(et_ap1.getText().toString())){ // El primer apellido es el mismo, no se ha cambiado
            ap1 = bd_ape1;
        }else{ //Se ha cambiado el apellido
            ap1 = et_ap1.getText().toString();
        }

        // Comprobación del segundo apellido
        if (bd_ape2.equalsIgnoreCase(et_ap2.getText().toString())){ // El segundo apellido es el mismo, no se ha cambiado
            ap2 = bd_ape2;
        }else{ // Se  ha modificado
            ap2 = et_ap2.getText().toString();
        }

        // Comprobacion del telefono
        if (bd_telf.equalsIgnoreCase(et_telf.getText().toString())){ // El telefono no se ha cambiado
            telf = bd_telf;
        }else{ // Se ha cambiado el telefono
            if (fv.esNumerico(et_telf.getText().toString()) && et_telf.getText().toString().length() == 9 && !telefonos.contains(et_telf.getText().toString())) {
                telf = et_telf.getText().toString();
            }else {
                fv.mostrarMensaje(this, "No se ha podido modificar el telefono. ");
                telf = bd_telf;
            }
        }

        // Comprobación del email
        if (bd_email.equalsIgnoreCase(et_email.getText().toString())){ // No se ha cambiado el email
            em = bd_email;
        }else{// Se ha cambiado el email
            if (fv.formatoEmail(et_email.getText().toString())) { // El formato del email es correcto
                em = et_email.getText().toString();
            }else{
                fv.mostrarMensaje(this, "El formato del email es incorrecto. ");
                em = bd_email;
            }
        }

        // Comprobacion de la contraseña
        if (fv.contieneTexto(et_contra.getText().toString())){ // La contraseña está modificada
            contra = et_contra.getText().toString();
        }else{
            contra = bd_contra;
        }

        if (perfil.getSelectedItemPosition() == bd_posPerfil){
            profile = bd_perfil;
        }else{
            profile = "Alumno".equalsIgnoreCase(perfil.getSelectedItem().toString())? "a": "g";
        }

        if (bd_us.equalsIgnoreCase(et_us.getText().toString())){
            us = bd_us;
        }else {
            if (fv.contieneTexto(et_us.getText().toString()) && !usuarios.contains(et_us.getText().toString())){
                us = et_us.getText().toString();
            }else{
                fv.mostrarMensaje(this, "El usuario introducido contiene un error, pruebe con otro diferente. ");
            }
        }

        String [] nuevosDatos = new String []{dni, nombre, ap1, ap2, telf, em, us, contra, profile};
        for (int i = 0; i < nuevosDatos.length; i++) {
            System.out.println("ObtenerNuevosDatos: nuevosDatos: "+nuevosDatos[i]);
        }
        return nuevosDatos;
    }

    /**
     * Metodo que modifica el perfil del alumno
     * @param v -- View del boton pulsado
     */
    public void updateProfile(View v){
        String[] datosNuevos = obtenerNuevosDatos();
        final String URL = fv.getURL()+"updateProfileData.php";

        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest sr = new StringRequest(Request.Method.PUT, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        salir();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        fv.mostrarMensaje(PerfilAlumnos.this, "No se pudo actualizar el perfil. ");
                    }
                }){
                public String getBodyContentType() {
                    return "application/json";
                }

                @Override
                public byte[] getBody() {
                    JSONObject jsonBody = new JSONObject();
                    try {
                        jsonBody.put("dni_a", datosNuevos[0]);
                        jsonBody.put("nombre", datosNuevos[1]);
                        jsonBody.put("apellido1", datosNuevos[2]);
                        jsonBody.put("apellido2", datosNuevos[3]);
                        if (!usuarios.contains(datosNuevos[4])) {
                            jsonBody.put("telefono", datosNuevos[4]);
                        }
                        jsonBody.put("email", datosNuevos[5]);
                        if (!usuarios.contains(datosNuevos[6])) {
                            jsonBody.put("usuario", datosNuevos[6]);
                        }
                        jsonBody.put("contra", datosNuevos[7]);
                        jsonBody.put("perfil", datosNuevos[8]);
                        jsonBody.put("dni_antiguo", dni_a);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return jsonBody.toString().getBytes();
                }
        };

        rq.add(sr);
    }

    public interface ConsultarDatosUsuarios{
        void onConsultaExitosa(List<String[]> listaUs);
        void onConsultaError(VolleyError v);
    }

    /**
     * Metodo que obtiene todos los usuarios y telefonos existentes 
     */
    public List<String[]> obtenerUsuariosYTelfs(){
        List<String[]> usuarios_obuyt = obtenerDatosUsuarios(new ConsultarDatosUsuarios() {
            @Override
            public void onConsultaExitosa(List<String[]> listaUs) {
                /*for (int i = 0; i < listaUs.size(); i++) {
                    //System.out.println("PerfilAlumnos: obtenerUsuariosYTelfs: onConsultaExistosa: usuarios: "+ listaUs.get(i)[0]);
                    //System.out.println("PerfilAlumnos: obtenerUsuariosYTelfs: onConsultaExistosa: telefonos: "+ listaUs.get(i)[1]);
                    usuarios.add(listaUs.get(i)[0]);
                    telefonos.add(listaUs.get(i)[1]);
                }*/
                System.out.println("Todos los usuarios y telefonos recaudados. ");
                /*for (int i = 0; i < usuarios.size(); i++) {
                    System.out.println("PerfilAlumnos: obtenerUsuariosYTelfs: onConsultaExitosa: usuarios: "+usuarios.get(i));
                }
                for (int i = 0; i < telefonos.size(); i++) {
                    System.out.println("PerfilAlumnos: obtenerUsuariosYTelfs: onConsultaExitosa: telefonos: "+telefonos.get(i));
                }*/
            }

            @Override
            public void onConsultaError(VolleyError v) {
                v.printStackTrace();
            }
        });

        return usuarios_obuyt;
    }

    /**
     * Metodo que realiza una peticion a la API para obtener los usuarios y telefonos registrados
     */
    public List<String[]> obtenerDatosUsuarios(ConsultarDatosUsuarios cd){
        final String URL = fv.getURL()+"checkUser.php";
        List<String[]> listaUs = new ArrayList<>();

        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest sr = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray ja = new JSONArray(response);
                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject json = ja.getJSONObject(i);
                                //System.out.println("PerfilAlumnos: obtenerDatosUsuarios: json: "+json.toString());
                                String [] usTelf = {json.getString("usuario"), json.getString("telefono")};

                                listaUs.add(usTelf);
                            }
                            for (int i = 0; i < listaUs.size(); i++) {
                                System.out.println("PerfilAlumnos: obtenerDatosUsuarios: "+ listaUs.get(i)[0]);
                                System.out.println("PerfilAlumnos: obtenerDatosUsuarios: "+ listaUs.get(i)[1]);
                                usuarios.add(listaUs.get(i)[0]);
                                telefonos.add(listaUs.get(i)[1]);
                            }
                            cd.onConsultaExitosa(listaUs);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        cd.onConsultaError(error);
                    }
                });

        rq.add(sr);
        return listaUs;
    }
}