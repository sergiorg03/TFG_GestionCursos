package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Clase para mostrar los cursos que los alumnos pueden realizar
 *
 *
 */
public class cursosAlumnos extends AppCompatActivity {

    // Variables
    FuncionesVarias fv = new FuncionesVarias();
    String dni;
    String id_curso1;
    String id_curso2;
    String id_curso3;
    String id_curso4;
    String id_curso5;
    String id_curso6;
    String id_curso7;
    String id_curso8;
    String id_curso9;
    String id_curso10;
    String id_curso11;
    String id_curso12;
    String id_curso13;
    String id_curso14;

    String ruta_pdf_curso1;
    String ruta_pdf_curso2;
    String ruta_pdf_curso3;
    String ruta_pdf_curso4;
    String ruta_pdf_curso5;
    String ruta_pdf_curso6;
    String ruta_pdf_curso7;
    String ruta_pdf_curso8;
    String ruta_pdf_curso9;
    String ruta_pdf_curso10;
    String ruta_pdf_curso11;
    String ruta_pdf_curso12;
    String ruta_pdf_curso13;
    String ruta_pdf_curso14;

    // TextViews
    TextView tv_curso1;
    TextView tv_curso2;
    TextView tv_curso3;
    TextView tv_curso4;
    TextView tv_curso5;
    TextView tv_curso6;
    TextView tv_curso7;
    TextView tv_curso8;
    TextView tv_curso9;
    TextView tv_curso10;
    TextView tv_curso11;
    TextView tv_curso12;
    TextView tv_curso13;
    TextView tv_curso14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos_alumnos);
        dni = getIntent().getStringExtra("dni");

        tv_curso1  = findViewById(R.id.tv_curso1);
        tv_curso2  = findViewById(R.id.tv_curso2);
        tv_curso3  = findViewById(R.id.tv_curso3);
        tv_curso4  = findViewById(R.id.tv_curso4);
        tv_curso5  = findViewById(R.id.tv_curso5);
        tv_curso6  = findViewById(R.id.tv_curso6);
        tv_curso7  = findViewById(R.id.tv_curso7);
        tv_curso8  = findViewById(R.id.tv_curso8);
        tv_curso9  = findViewById(R.id.tv_curso9);
        tv_curso10 = findViewById(R.id.tv_curso10);
        tv_curso11 = findViewById(R.id.tv_curso11);
        tv_curso12 = findViewById(R.id.tv_curso12);
        tv_curso13 = findViewById(R.id.tv_curso13);
        tv_curso14 = findViewById(R.id.tv_curso14);

        // asignamos el texto
        tv_curso1.setText("");
        tv_curso2.setText("");
        tv_curso3.setText("");
        tv_curso4.setText("");
        tv_curso5.setText("");
        tv_curso6.setText("");
        tv_curso7.setText("");
        tv_curso8.setText("");
        tv_curso9.setText("");
        tv_curso10.setText("");
        tv_curso11.setText("");
        tv_curso12.setText("");
        tv_curso13.setText("");
        tv_curso14.setText("");

        tv_curso1.setHint("");
        tv_curso2.setHint("");
        tv_curso3.setHint("");
        tv_curso4.setHint("");
        tv_curso5.setHint("");
        tv_curso6.setHint("");
        tv_curso7.setHint("");
        tv_curso8.setHint("");
        tv_curso9.setHint("");
        tv_curso10.setHint("");
        tv_curso11.setHint("");
        tv_curso12.setHint("");
        tv_curso13.setHint("");
        tv_curso14.setHint("");

        setListeners();
        mostrarTodosCursos();
    }

    /**
     * Funcion para salir de la pantalla a la pantalla de LogIn
     * @param v
     */
    public void salir(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    /**
     * Metodo que me lleva a la clase para poder ver las notas de cada curso
     * @param v
     */
    public void verNotas(View v){
        Intent i = new Intent(this, notasAlumnos.class);
        i.putExtra("dni", dni);
        startActivity(i);
        finish();
    }

    /**
     * Metodo para ir a la clase de perfil
     * @param v
     */
    public void perfil(View v){
        Intent i = new Intent(this, Perfil.class);
        i.putExtra("dni", dni);
        i.putExtra("clase", "cursosAlumnos");
        startActivity(i);
        finish();
    }

    public interface ConsultarDatos{
        void onConsultaExitosa(List<String[]> listaCursos);
        void onConsultaError(VolleyError error);
    }

    public void mostrarTodosCursos(){

        List<String []> cursos = getCursos(new ConsultarDatos() {

            @Override
            public void onConsultaExitosa(List<String[]> listaCursos) {

                Map<String, String[]> todoDatosCurso = new HashMap<>();
                int i = 1;
                // Añadimos los cursos con sus respectivas notas
                for (String[] curso : listaCursos) {
                         /*
                         System.out.println(curso[0]);
                         System.out.println(curso[1]);
                         */
                    String [] datosCurso = new String[]{curso[1], curso[2]};
                    todoDatosCurso.put(curso[0], datosCurso);
                }

                for (Map.Entry<String, String[]> entry : todoDatosCurso.entrySet()) {
                    String key = entry.getKey();
                    switch (i) {
                        case 1:
                            id_curso1 = key;
                            tv_curso1.setText(todoDatosCurso.get(key)[0]);
                            ruta_pdf_curso1 = todoDatosCurso.get(key)[1];
                            System.out.println("Id: "+ id_curso1);
                            System.out.println("Nombre curso: "+ tv_curso1.getText().toString());
                            System.out.println("ruta: "+ ruta_pdf_curso1);
                            break;
                        case 2:
                            id_curso2 = key;
                            tv_curso2.setText(todoDatosCurso.get(key)[0]);
                            ruta_pdf_curso2 = todoDatosCurso.get(key)[1];
                            break;
                        case 3:
                            id_curso3 = key;
                            tv_curso3.setText(todoDatosCurso.get(key)[0]);
                            ruta_pdf_curso3 = todoDatosCurso.get(key)[1];
                            break;
                        case 4:
                            id_curso4 = key;
                            tv_curso4.setText(todoDatosCurso.get(key)[0]);
                            ruta_pdf_curso4 = todoDatosCurso.get(key)[1];
                            break;
                        case 5:
                            id_curso5 = key;
                            tv_curso5.setText(todoDatosCurso.get(key)[0]);
                            ruta_pdf_curso5 = todoDatosCurso.get(key)[1];
                            break;
                        case 6:
                            id_curso6 = key;
                            tv_curso6.setText(todoDatosCurso.get(key)[0]);
                            ruta_pdf_curso6 = todoDatosCurso.get(key)[1];
                            break;
                        case 7:
                            id_curso7 = key;
                            tv_curso7.setText(todoDatosCurso.get(key)[0]);
                            ruta_pdf_curso7 = todoDatosCurso.get(key)[1];
                            break;
                        case 8:
                            id_curso8 = key;
                            tv_curso8.setText(todoDatosCurso.get(key)[0]);
                            ruta_pdf_curso8 = todoDatosCurso.get(key)[1];
                            break;
                        case 9:
                            id_curso9 = key;
                            tv_curso9.setText(todoDatosCurso.get(key)[0]);
                            ruta_pdf_curso9 = todoDatosCurso.get(key)[1];
                            break;
                        case 10:
                            id_curso10 = key;
                            tv_curso10.setText(todoDatosCurso.get(key)[0]);
                            ruta_pdf_curso10 = todoDatosCurso.get(key)[1];
                            break;
                        case 11:
                            id_curso11 = key;
                            tv_curso11.setText(todoDatosCurso.get(key)[0]);
                            ruta_pdf_curso11 = todoDatosCurso.get(key)[1];
                            break;
                        case 12:
                            id_curso12 = key;
                            tv_curso12.setText(todoDatosCurso.get(key)[0]);
                            ruta_pdf_curso12 = todoDatosCurso.get(key)[1];
                            break;
                        case 13:
                            id_curso13 = key;
                            tv_curso13.setText(todoDatosCurso.get(key)[0]);
                            ruta_pdf_curso13 = todoDatosCurso.get(key)[1];
                            break;
                        case 14:
                            id_curso14 = key;
                            tv_curso14.setText(todoDatosCurso.get(key)[0]);
                            ruta_pdf_curso14 = todoDatosCurso.get(key)[1];
                            break;
                    }
                    i++;
                }
            }

            @Override
            public void onConsultaError(VolleyError ve) {
                fv.mostrarMensaje(cursosAlumnos.this, "No se pudieron recopilar datos. ");
            }
        });
    }

    public List<String []> getCursos(ConsultarDatos cd){

        // final String URL = "http://"+getString(R.string.ip)+"/tfg/app/API/getAllCourses.php";
        final String URL = fv.getURL()+"getAllCourses.php";

        RequestQueue rq = Volley.newRequestQueue(this);

        List<String []> cursos = new ArrayList<>();

        StringRequest sr = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray ja = new JSONArray(response);
                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject json = ja.getJSONObject(i);
                                String [] curso = new String[3];
                                curso[0] = json.getString("id");
                                curso[1] = json.getString("nombre");
                                curso[2] = json.getString("ruta_pdf");
                                cursos.add(curso);
                            }


                            cd.onConsultaExitosa(cursos);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //fv.guardadoLogs(error.toString(), "cursosAlumnos_getCursos");
                cd.onConsultaError(error);
            }
        });

        rq.add(sr);

        return cursos;
    }

    /**
     * Metodo para asignar listener a los textViews
     */
    public void setListeners(){

        tv_curso1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_curso1.getText().toString())){
                    downloadPDF(ruta_pdf_curso1, tv_curso1.getText().toString());
                    nextClass(id_curso1);
                }
            }
        });

        tv_curso2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_curso2.getText().toString())){
                    downloadPDF(ruta_pdf_curso2, tv_curso2.getText().toString());
                    nextClass(id_curso2);
                }
            }
        });

        tv_curso3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_curso3.getText().toString())){
                    downloadPDF(ruta_pdf_curso3, tv_curso3.getText().toString());
                    nextClass(id_curso3);
                }
            }
        });

        tv_curso4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_curso4.getText().toString())){
                    downloadPDF(ruta_pdf_curso4, tv_curso4.getText().toString());
                    nextClass(id_curso4);
                }
            }
        });

        tv_curso5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_curso5.getText().toString())){
                    downloadPDF(ruta_pdf_curso5, tv_curso5.getText().toString());
                    nextClass(id_curso5);
                }
            }
        });

        tv_curso6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_curso6.getText().toString())){
                    downloadPDF(ruta_pdf_curso6, tv_curso6.getText().toString());
                    nextClass(id_curso6);
                }

            }
        });

        tv_curso7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_curso7.getText().toString())){
                    downloadPDF(ruta_pdf_curso7, tv_curso7.getText().toString());
                    nextClass(id_curso7);
                }

            }
        });

        tv_curso8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_curso8.getText().toString())){
                    downloadPDF(ruta_pdf_curso8, tv_curso8.getText().toString());
                    nextClass(id_curso8);
                }
            }
        });

        tv_curso9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_curso9.getText().toString())){
                    downloadPDF(ruta_pdf_curso9, tv_curso9.getText().toString());
                    nextClass(id_curso9);
                }
            }
        });

        tv_curso10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_curso10.getText().toString())){
                    downloadPDF(ruta_pdf_curso10, tv_curso10.getText().toString());
                    nextClass(id_curso10);
                }
            }
        });

        tv_curso11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_curso11.getText().toString())){
                    downloadPDF(ruta_pdf_curso11, tv_curso11.getText().toString());
                    nextClass(id_curso11);
                }
            }
        });

        tv_curso12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_curso12.getText().toString())){
                    downloadPDF(ruta_pdf_curso12, tv_curso12.getText().toString());
                    nextClass(id_curso12);
                }
            }
        });

        tv_curso13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_curso13.getText().toString())){
                    downloadPDF(ruta_pdf_curso13, tv_curso13.getText().toString());
                    nextClass(id_curso13);
                }
            }
        });

        tv_curso14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_curso14.getText().toString())){
                    downloadPDF(ruta_pdf_curso14, tv_curso14.getText().toString());
                    nextClass(id_curso14);
                }
            }
        });
    }

    /**
     * Metodo para comenzar la siguiente pantalla
     * @param id_curso
     */
    public void nextClass(String id_curso){
        Intent i = new Intent(cursosAlumnos.this, RealizarCursos.class);
        i.putExtra("dni", dni);
        i.putExtra("clase", "cursosAlumnos");
        i.putExtra("idCurso", id_curso);
        startActivity(i);
        finish();
    }

    /**
     * Metodo para descargar los cursos en el almacenamiento interno del dispositivo.
     * Se descarga en la carperta downloads>cursos
     * @param ruta -- nombre del archivo
     */
    public void downloadPDF(String ruta, String nombre){
        try {
            int id = getResources().getIdentifier(ruta, "raw", getPackageName());

            // Trazas
            /*System.out.println("Id Archivo--> "+R.raw.gestion_archivos_ubuntu);
            System.out.println("Id personal--> "+ id);
            System.out.println("packageName--> "+ getPackageName());*/

            InputStream is = getInputStream(id, nombre);

            File carpetaCursos = new File("/storage/self/primary/Download" + File.separator + "cursos");
            if (!carpetaCursos.exists()) {
                carpetaCursos.mkdir();
            }
            //System.out.println("Ruta carpeta cursos--> "+carpetaCursos.getAbsolutePath());

            File archivo = new File(carpetaCursos.getPath().toString()+ File.separator + ruta +".pdf");

            //System.out.println(archivo.getAbsolutePath());

            FileOutputStream fos = new FileOutputStream(archivo, false);

            // Copiamos el archivo al almacenamiento interno
            byte[] buffer = new byte[1024];
            int i;
            while ((i = is.read(buffer)) != -1) {
                fos.write(buffer, 0, i);
            }

            // Cerramos las instancias
            is.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fv.mostrarMensaje(this, "PDF del curso descargado correctamente.");
        fv.mostrarMensaje(this, "El curso se descargo en la carpeta cursos dentro de descargas. ");
    }

    /**
     * Metodo que devuelve el InputStream de un curso, por si se encuentra en la carpeta raw o en la carpeta Downloads
     * @param id -- Id para obtener el curso de la carpeta raw
     * @param nombre -- Nombre para obtener el curso de la carpeta downloads
     * @return -- Devuelve el inputStream correcto
     */
    public InputStream getInputStream(int id, String nombre){
        InputStream is = null;
        try {
            is = getResources().openRawResource(id);
        } catch (Resources.NotFoundException e) {
            // El recurso no se encontró en los recursos, seguirá siendo null
        }

        // Si el recurso no se encuentra en los recursos, intentar abrirlo desde el almacenamiento externo
        if (is == null) {
            File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "cursos/" + nombre);

            try {
                is = new FileInputStream(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return is;
    }
}