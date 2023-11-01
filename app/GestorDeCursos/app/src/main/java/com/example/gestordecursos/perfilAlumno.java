package com.example.gestordecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 *
 * Clase para mostrar la puntuacion del alumno en los test realizados
 *
 */
public class perfilAlumno extends AppCompatActivity {

    FuncionesVarias fv = new FuncionesVarias();
    EditText dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_alumno);
        dni = findViewById(R.id.dni_PA);
    }

    /**
     * Metodo para volver a la clase anterior
     * @param v
     */
    public void volver(View v){

    }

    /**
     * MEtodo para obtener la puntuacion de la persona en los diferentes cursos existentes
     * @param v
     */
    public void getMarks(View v){
        String dni_us = this.dni.getText().toString(); // Obtenemos el DNI
         if (fv.contieneTexto(dni_us)){
            // Hacemos la peticion para obtener las puntuaciones de los cursos de la persona
             // String TERMINARRRRRR
         }else fv.mostrarMensaje(this, "Debe introducir su DNI para mostrar la puntuacion en los cursos. ");
    }

    /**
     * public void logIn(View v) {
     *         String us = usuario.getText().toString();
     *         String pass = contra.getText().toString();
     *         // Comprobamos que ha introducido un usuario y una contraseña
     *         if (us == null && pass == null) {
     *             // Hacemos una peticion http para ver si el usuario existe y la contraseña es correcta
     *             // Obtenemos de la peticion realizada el perfil del usuario
     *             String perfil = ObtenerPerfil(new ConsultarDatos() {
     *                 @Override
     *                 public void onConsultaExitosa(String rol) {
     *                     // Creamos la nueva clase
     *                     try {
     *                         // System.out.println(rol);
     *
     *                         Class c = null;
     *                         if (rol.equalsIgnoreCase("perfilalumno")) {
     *                             c = cursosAlumnos.class;
     *                         } else if (rol.equalsIgnoreCase("perfilgestor")) {
     *                             c = cursosGestores.class;
     *                         }
     *                         if (c == null){
     *                             fv.mostrarMensaje(MainActivity.this, "El usuario introducido no existe. ");
     *                         }else {
     *                             Intent i = new Intent(getApplicationContext(), c); // Creamos la instancia de la clase intent para pasar a otra pantalla
     *                             startActivity(i); // Cambiamos de pantalla
     *                             finish(); // Terminamos la activity en la que estamos
     *                         }
     *                     } catch (Exception e) {
     *                         e.printStackTrace();
     *                     }
     *                 }
     *
     *                 @Override
     *                 public void onConsultaError(VolleyError error) {
     *                     error.printStackTrace();
     *                 }
     *             })[0];
     *         }else{
     *             mostrarMensaje("Debe rellenar ambos campos. ");
     *         }
     *     }
     */
}