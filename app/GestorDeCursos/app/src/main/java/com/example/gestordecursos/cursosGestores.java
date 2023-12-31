package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.ImageView;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Clase para mostrar los cursos en el perfil gestor
 *
 */
public class cursosGestores extends AppCompatActivity {

    FuncionesVarias fv = new FuncionesVarias();
    String dni;
    int lineaSalto;
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

    // TextViews
    TextView tv_cg1;
    TextView tv_cg2;
    TextView tv_cg3;
    TextView tv_cg4;
    TextView tv_cg5;
    TextView tv_cg6;
    TextView tv_cg7;
    TextView tv_cg8;
    TextView tv_cg9;
    TextView tv_cg10;
    ImageView ib_edit1;
    ImageView ib_del1;
    ImageView ib_edit2;
    ImageView ib_del2;
    ImageView ib_edit3;
    ImageView ib_del3;
    ImageView ib_edit4;
    ImageView ib_del4;
    ImageView ib_edit5;
    ImageView ib_del5;
    ImageView ib_edit6;
    ImageView ib_del6;
    ImageView ib_edit7;
    ImageView ib_del7;
    ImageView ib_edit8;
    ImageView ib_del8;
    ImageView ib_edit9;
    ImageView ib_del9;
    ImageView ib_edit10;
    ImageView ib_del10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos_gestores);
        dni = getIntent().getStringExtra("dni");
        lineaSalto = getIntent().getIntExtra("lineaSalto", 0);

        inicializarVariables();
    }

    /**
     * Metodo para inicializar las variables a utilizar
     */
    public void inicializarVariables(){
        tv_cg1  = findViewById(R.id.tv_cg1);
        tv_cg2  = findViewById(R.id.tv_cg2);
        tv_cg3  = findViewById(R.id.tv_cg3);
        tv_cg4  = findViewById(R.id.tv_cg4);
        tv_cg5  = findViewById(R.id.tv_cg5);
        tv_cg6  = findViewById(R.id.tv_cg6);
        tv_cg7  = findViewById(R.id.tv_cg7);
        tv_cg8  = findViewById(R.id.tv_cg8);
        tv_cg9  = findViewById(R.id.tv_cg9);
        tv_cg10 = findViewById(R.id.tv_cg10);

        tv_cg1.setText("");
        tv_cg2.setText("");
        tv_cg3.setText("");
        tv_cg4.setText("");
        tv_cg5.setText("");
        tv_cg6.setText("");
        tv_cg7.setText("");
        tv_cg8.setText("");
        tv_cg9.setText("");
        tv_cg10.setText("");


        tv_cg1.setHint("");
        tv_cg2.setHint("");
        tv_cg3.setHint("");
        tv_cg4.setHint("");
        tv_cg5.setHint("");
        tv_cg6.setHint("");
        tv_cg7.setHint("");
        tv_cg8.setHint("");
        tv_cg9.setHint("");
        tv_cg10.setHint("");

        ib_edit1    = findViewById(R.id.edit1);
        ib_del1     = findViewById(R.id.del1);

        ib_edit2    = findViewById(R.id.edit2);
        ib_del2     = findViewById(R.id.del2);

        ib_edit3    = findViewById(R.id.edit3);
        ib_del3     = findViewById(R.id.del3);

        ib_edit4    = findViewById(R.id.edit4);
        ib_del4     = findViewById(R.id.del4);

        ib_edit5    = findViewById(R.id.edit5);
        ib_del5     = findViewById(R.id.del5);

        ib_edit6    = findViewById(R.id.edit6);
        ib_del6     = findViewById(R.id.del6);

        ib_edit7    = findViewById(R.id.edit7);
        ib_del7     = findViewById(R.id.del7);

        ib_edit8    = findViewById(R.id.edit8);
        ib_del8     = findViewById(R.id.del8);

        ib_edit9    = findViewById(R.id.edit9);
        ib_del9     = findViewById(R.id.del9);

        ib_edit10   = findViewById(R.id.edit10);
        ib_del10    = findViewById(R.id.del10);

        ib_edit1.setVisibility(View.INVISIBLE);
        ib_del1.setVisibility(View.INVISIBLE);
        ib_edit2.setVisibility(View.INVISIBLE);
        ib_del2.setVisibility(View.INVISIBLE);
        ib_edit3.setVisibility(View.INVISIBLE);
        ib_del3.setVisibility(View.INVISIBLE);
        ib_edit4.setVisibility(View.INVISIBLE);
        ib_del4.setVisibility(View.INVISIBLE);
        ib_edit5.setVisibility(View.INVISIBLE);
        ib_del5.setVisibility(View.INVISIBLE);
        ib_edit6.setVisibility(View.INVISIBLE);
        ib_del6.setVisibility(View.INVISIBLE);
        ib_edit7.setVisibility(View.INVISIBLE);
        ib_del7.setVisibility(View.INVISIBLE);
        ib_edit8.setVisibility(View.INVISIBLE);
        ib_del8.setVisibility(View.INVISIBLE);
        ib_edit9.setVisibility(View.INVISIBLE);
        ib_del9.setVisibility(View.INVISIBLE);
        ib_edit10.setVisibility(View.INVISIBLE);
        ib_del10.setVisibility(View.INVISIBLE);

        mostrarTodosCursos();
        setListeners();
    }

    /**
     * Memtodo para volver a la clase anterior (LogIn)
     * @param v
     */
    public void salir(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    /**
     * Metodo para ir a la pantalla de perfil
     * @param v
     */
    public void perfil(View v){
        Intent i = new Intent(this, Perfil.class);
        i.putExtra("dni", dni);
        i.putExtra("clase", "cursosgestores");
        startActivity(i);
        finish();
    }

    /**
     * Metodo para asignar listener a los textViews
     */
    public void setListeners(){

        tv_cg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_cg1.getText().toString())){
                    //System.out.println("Hola");
                    // Ir a la clase EditorCursos mandar id_curso
                    editarCurso(id_curso1);
                }
            }
        });

        tv_cg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_cg2.getText().toString())){
                    editarCurso(id_curso2);
                }
            }
        });

        tv_cg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_cg3.getText().toString())){
                    editarCurso(id_curso3);
                }
            }
        });

        tv_cg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_cg4.getText().toString())){
                    editarCurso(id_curso4);
                }
            }
        });

        tv_cg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_cg5.getText().toString())){
                    editarCurso(id_curso5);
                }
            }
        });

        tv_cg6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_cg6.getText().toString())){
                    editarCurso(id_curso6);
                }
            }
        });

        tv_cg7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_cg7.getText().toString())){
                    editarCurso(id_curso7);
                }
            }
        });

        tv_cg8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_cg8.getText().toString())){
                    editarCurso(id_curso8);
                }
            }
        });

        tv_cg9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_cg9.getText().toString())){
                    editarCurso(id_curso8);
                }
            }
        });

        tv_cg10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fv.contieneTexto(tv_cg10.getText().toString())){
                    editarCurso(id_curso10);
                }
            }
        });
    }

    /**
     * Metodo para dirigirnos a la clase de editar cursos
     * @param id_curso
     */
    public void editarCurso(String id_curso){
        System.out.println("cursosGestores: editarCurso->");
        Intent i = new Intent(this, informacionEditarCursos.class);
        i.putExtra("dni", dni);
        i.putExtra("clase", "cursosgestores");
        i.putExtra("id_curso", id_curso);
        startActivity(i);
        finish();
    }

    /**
     * Metodo para borrar el curso indicado
     * @param id_curso
     */
    public void borrarCurso(String id_curso){
        //final String URL = "http://"+getString(R.string.ip)+"/tfg/app/API/deleteCourse.php";
        final String URL = fv.getURL()+"deleteCourse.php";

        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest sr = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            fv.mostrarMensaje(cursosGestores.this, "Curso borrado correctamente. ");
                        }
                    },
                new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            //fv.guardadoLogs(error.toString(), "cursosGestores_corrarCursos");
                            fv.mostrarMensaje(cursosGestores.this, "No se pudo borrar el curso. ");
                        }
                    })
        {
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody() {
                JSONObject jsonBody = null;
                try {
                    jsonBody = new JSONObject();
                    jsonBody.put("id_curso", id_curso);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return jsonBody.toString().getBytes();
            }
        };

        // Añadimos la peticion a la cola
        rq.add(sr);
        resetClass();
    }

    // Interfaz para obtener los datos
    public interface ConsultarDatos{
            void onConsultaExitosa(List<String[]> lista);
            void onConsultaError(VolleyError ve);
    }

    /**
     * Metodo para mostrar todos los cursos
     */
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
                            tv_cg1.setText(todoDatosCurso.get(key)[0]);
                            System.out.println("Id: "+ id_curso1);
                            System.out.println("Nombre curso: "+ tv_cg1.getText().toString());
                            ib_edit1.setVisibility(View.VISIBLE);
                            ib_del1.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            id_curso2 = key;
                            tv_cg2.setText(todoDatosCurso.get(key)[0]);
                            ib_edit2.setVisibility(View.VISIBLE);
                            ib_del2.setVisibility(View.VISIBLE);

                            break;
                        case 3:
                            id_curso3 = key;
                            tv_cg3.setText(todoDatosCurso.get(key)[0]);
                            ib_edit3.setVisibility(View.VISIBLE);
                            ib_del3.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            id_curso4 = key;
                            tv_cg4.setText(todoDatosCurso.get(key)[0]);
                            ib_edit4.setVisibility(View.VISIBLE);
                            ib_del4.setVisibility(View.VISIBLE);
                            break;
                        case 5:
                            id_curso5 = key;
                            tv_cg5.setText(todoDatosCurso.get(key)[0]);
                            ib_edit5.setVisibility(View.VISIBLE);
                            ib_del5.setVisibility(View.VISIBLE);
                            break;
                        case 6:
                            id_curso6 = key;
                            tv_cg6.setText(todoDatosCurso.get(key)[0]);
                            ib_edit6.setVisibility(View.VISIBLE);
                            ib_del6.setVisibility(View.VISIBLE);
                            break;
                        case 7:
                            id_curso7 = key;
                            tv_cg7.setText(todoDatosCurso.get(key)[0]);
                            ib_edit7.setVisibility(View.VISIBLE);
                            ib_del7.setVisibility(View.VISIBLE);
                            break;
                        case 8:
                            id_curso8 = key;
                            tv_cg8.setText(todoDatosCurso.get(key)[0]);
                            ib_edit8.setVisibility(View.VISIBLE);
                            ib_del8.setVisibility(View.VISIBLE);
                            break;
                        case 9:
                            id_curso9 = key;
                            tv_cg9.setText(todoDatosCurso.get(key)[0]);
                            ib_edit9.setVisibility(View.VISIBLE);
                            ib_del9.setVisibility(View.VISIBLE);
                            break;
                        case 10:
                            id_curso10 = key;
                            tv_cg10.setText(todoDatosCurso.get(key)[0]);
                            ib_edit10.setVisibility(View.VISIBLE);
                            ib_del10.setVisibility(View.VISIBLE);
                            break;
                    }
                    i++;
                }
            }

            @Override
            public void onConsultaError(VolleyError ve) {
                System.out.println("CursosGestores: mostrarTodosCursos: onConsultaError->");
                //fv.mostrarMensaje(cursosGesotres.this, "No se pudieron recopilar datos. ");
            }
        });
    }

    public List<String[]> getCursos(ConsultarDatos cd){

        List<String[]> listaCursos = new ArrayList<>();

        //final String URL = "http://"+getString(R.string.ip)+"/tfg/app/API/getAllCourses.php";
        final String URL = fv.getURL()+"getAllCourses.php?lineaSalto="+lineaSalto;

        RequestQueue rq = Volley.newRequestQueue(this);

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
                                listaCursos.add(curso);
                            }


                            cd.onConsultaExitosa(listaCursos);
                        } catch (JSONException e) {
                            System.out.println("Esta correcto, intenta convertir un array vacio en un JSONArray. ");
                            tv_cg1.setText("No hay más cursos a mostrar. ");
                            tv_cg1.setOnClickListener(null);
                            findViewById(R.id.nextQuestion).setOnClickListener(null);
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //fv.guardadoLogs(error.toString(), "cursosGestores_getCursos");
                cd.onConsultaError(error);
            }
        });

        rq.add(sr);
        return listaCursos;
    }

    /**
     *
     * METODOS PARA ELIMINAR UN CURSO
     *
     */
    public void ib_DelCurso1(View v){
        confirmacionBorradoCurso(id_curso1);
    }

    public void ib_DelCurso2(View v){
        confirmacionBorradoCurso(id_curso2);
    }

    public void ib_DelCurso3(View v){
        confirmacionBorradoCurso(id_curso3);
    }

    public void ib_DelCurso4(View v){
        confirmacionBorradoCurso(id_curso4);
    }

    public void ib_DelCurso5(View v){
        confirmacionBorradoCurso(id_curso5);
    }

    public void ib_DelCurso6(View v){
        confirmacionBorradoCurso(id_curso6);
    }

    public void ib_DelCurso7(View v){
        confirmacionBorradoCurso(id_curso7);
    }

    public void ib_DelCurso8(View v){
        confirmacionBorradoCurso(id_curso8);
    }

    public void ib_DelCurso9(View v){
        confirmacionBorradoCurso(id_curso9);
    }

    public void ib_DelCurso10(View v){
        confirmacionBorradoCurso(id_curso10);
    }

    /**
     *
     * METODOS PARA EDITAR UN CURSO
     *
     */
    public void ib_ed1(View v){
        editarCurso(id_curso1);
    }

    public void ib_ed2(View v){
        editarCurso(id_curso2);
    }

    public void ib_ed3(View v){
        editarCurso(id_curso3);
    }

    public void ib_ed4(View v){
        editarCurso(id_curso4);
    }

    public void ib_ed5(View v){
        editarCurso(id_curso5);
    }

    public void ib_ed6(View v){
        editarCurso(id_curso6);
    }

    public void ib_ed7(View v){
        editarCurso(id_curso7);
    }

    public void ib_ed8(View v){
        editarCurso(id_curso8);
    }

    public void ib_ed9(View v){
        editarCurso(id_curso9);
    }

    public void ib_ed10(View v){
        editarCurso(id_curso10);
    }

    /**
     * Metodo para resetear la clase y mostrar los cursos existentes
     */
    public void resetClass(){
        Intent i = new Intent(cursosGestores.this, cursosGestores.class);
        i.putExtra("dni", dni);
        startActivity(i);
        finish();
    }

    /**
     * Metodo para añadir un curso (PDF) desde el dispositivo
     * @param v
     */
    public void addCourse(View v){
        /*Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Downloads.EXTERNAL_CONTENT_URI);
        i.setType("pdf/");
        startActivityForResult(i.createChooser(i, "Seleccione el curso a subir: "), 10);*/

        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("application/pdf");// Asignamos el tipo de archivo
        startActivityForResult(i, 10);// Comenzamos el intent para obtener el pdf
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK && data != null) {
            System.out.println("cursosGestores: onActivityResult: data.getData().getPath(): "+data.getData().getPath());
            Uri pdf = data.getData();
            //System.out.println("Nombre: "+obtenerNombreArchivo(pdf));
            System.out.println(pdf);
            copiarPDF(pdf);
        }else{
            fv.mostrarMensaje(this, "No eligio ningun archivo para subir. ");
        }
    }

    /**
     * Metodo que copia un archivo pdf de un lugar en el dispositivo movil a la carpeta de descargas
     * @param url -- ruta del pdf
     */
    public void copiarPDF(Uri url) {
        // Ruta a la carpeta donde estan todos los cursos almacenados.
        final String ruta = "/storage/self/primary/Download"+ File.separator + "cursos/";
        File pdf = null;
        String nombrePDF = obtenerNombreArchivo(url);
        try {
            System.out.println("url.getLastPathSegment()--> " + url.getLastPathSegment());

            // Archivo a guardar en la carpeta cursos
            pdf = new File("/storage/self/primary/Download" + File.separator + "cursos/", obtenerNombreArchivo(url));

            System.out.println("Ruta para guardar el archivo pdf--> "+ pdf.getPath());
            System.out.println("Nombre curso: "+ pdf.getName());

            InputStream is = getContentResolver().openInputStream(url);
            OutputStream os = new FileOutputStream(pdf);
            byte[] bite = new byte[1024];
            int i;
            while ((i = is.read(bite)) > 0){
                os.write(bite, 0, i);
            }

            is.close();
            os.close();
        } catch (IOException e) {
            System.out.println("ERRORRRRRRRRRRRRRRRRR");
            e.printStackTrace();
        }

        System.out.println("Nombre: "+obtenerNombreArchivo(url)+" ruta: "+ruta);
        // Añadimos el curso a la API
        addCourseBD(nombrePDF, ruta);
        // crearTest(); Enviar dni, id_curso, clase
    }

    /**
     * Metodo que añade el curso a la base de datos
     * @param nombreCurso -- Nombre del curso
     * @param ruta_pdf -- ruta del pdf a guardar
     */
    public void addCourseBD(String nombreCurso, String ruta_pdf){
        System.out.println("Nombre del curso = "+ nombreCurso);

        final String URL = fv.getURL()+"addCourse.php";

        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest sr = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            String id_curso = json.getString("id_curso");
                            System.out.println("cursosGestores: addCourse: id_curso: "+id_curso);
                            editarCurso(id_curso);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //fv.guardadoLogs(error.toString(), "cursosGestores_crearTest");
                        fv.mostrarMensaje(cursosGestores.this, "No se pudo crear el curso, intentelo más tarde. ");
                    }
                }
        ){
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody() {
                JSONObject jsonBody = new JSONObject();
                try {
                    System.out.println("cursosGestores: addCourse: dni: "+ dni);
                    System.out.println("cursosGestores: addCourse: nombre: "+ nombreCurso);
                    System.out.println("cursosGestores: addCourse: ruta_pdf: "+ ruta_pdf);
                    jsonBody.put("dni", dni);
                    jsonBody.put("nombre", nombreCurso);
                    jsonBody.put("ruta_pdf", ruta_pdf);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return jsonBody.toString().getBytes();
            }
        };
        // añadimos la peticion a la cola
        rq.add(sr);
    }

    /**
     * Metodo que obtiene el nombre de un archivo en el almacenamiento externo del didpositivo
     * @param u -- ruta del archivo del que obtendremos el nombre
     * @return -- Devolvemos el nombre del archivo.
     */
    public String obtenerNombreArchivo(Uri u){
        String nombre = "";

        if (u.getScheme().equals("content")){
            try {
                Cursor c = getContentResolver().query(u, null, null, null, null);
                if (c != null && c.moveToFirst()) {
                    int nombreIndex = c.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    if (nombreIndex != -1) {
                        nombre = c.getString(nombreIndex);
                    }
                }
            }catch (Exception e){
                System.out.println("cursosGestores: obtenerNombreArchivo: error : "+e);
            }
        }

        return nombre;
    }

    /**
     * Metodo que pide al usuario confirmacion para el borrado del curso
     * @param id_curso -- Id del curso a borrar.
     */
    protected void confirmacionBorradoCurso(String id_curso){

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Confirmacion Borrado del curso. ")
          .setMessage("¿Está seguro que desea borrar el curso?")
          .setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println();
                borrarCurso(id_curso);
            }
        }).setNegativeButton("No", null).show();
    }

    /**
     * Metodo que nos dirige a la pantalla de perfil de alumnos
     * @param v
     */
    public void alumnos(View v){
        Intent i = new Intent(this, gestoresAlumnos.class);
        i.putExtra("clase", "cursosgestores");
        i.putExtra("dni_g", dni);
        startActivity(i);
        finish();
    }

    /**
     * Metodo que muestra los siguiente 14 cursos.
     * @param v -- View del boton pulsado
     */
    public void siguientes10Cursos(View v){
        Intent i = new Intent(this, cursosGestores.class);
        i.putExtra("dni", dni);
        int siguientesCursos = lineaSalto + 10;
        i.putExtra("lineaSalto", siguientesCursos);
        startActivity(i);
        finish();
    }

    /**
     * Metodo que muestra los 14 cursos anteriores
     * @param v -- View del boton pulsado para obtener los cursos anteriores
     */
    public void cursosAnteriores(View v){
        Intent i = new Intent(this, cursosGestores.class);
        i.putExtra("dni", dni);
        int siguientesCursos = (lineaSalto-10 <= 0)? 0: lineaSalto-10;
        i.putExtra("lineaSalto", siguientesCursos);
        startActivity(i);
        finish();
    }
}