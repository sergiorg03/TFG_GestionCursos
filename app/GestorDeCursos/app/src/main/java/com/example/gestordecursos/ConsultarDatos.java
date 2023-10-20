package com.example.gestordecursos;

import com.android.volley.VolleyError;

// Creacion de la interfaz para la reutilizacion de metodos
public interface ConsultarDatos {

        void onConsultaExitosa(String rol);

        void onConsultaError(VolleyError error);
}
