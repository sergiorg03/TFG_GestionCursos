package com.example.gestordecursos;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FuncionesVarias_Test {

    FuncionesVarias fv = new FuncionesVarias();

    @Test
    public void test_formatoDNI_erroneo() {

        String dni = "12345678A";
        assertEquals(false, fv.formatoDNI(dni));
    }

    @Test
    public void test_formatoEmail_erroneo(){
        String email = "josemiguel.com";
        assertEquals(false, fv.formatoEmail(email));
    }

    @Test
    public void test_formatoEmail_correcto(){
        String email = "josemiguel@gmail.com";
        assertEquals(true, fv.formatoEmail(email));
    }

    @Test
    public void test_formatoDNI_valido() {

        String dni = "12345678Z";
        assertEquals(true, fv.formatoDNI(dni));
    }

    @Test
    public void test_esNumerico_valido(){
        String numero = "1234";
        assertEquals(true, fv.esNumerico(numero));
    }

    @Test
    public void test_esNumerico_erroneo(){
        String numero = "asnd";
        assertEquals(false, fv.esNumerico(numero));
    }

    @Test
    public void test_contieneTexto_valido(){
        String texto = "hola como estas";
        assertEquals(true, fv.contieneTexto(texto));
    }

    @Test
    public void test_contieneTexto_erroneo(){
        String texto = "";
        assertEquals(false, fv.contieneTexto(texto));
    }

    @Test
    public void test_quitarEspaciosEnBlanco(){
        String cadena = "hola como estas";
        assertEquals("hola_como_estas", fv.quitarEspaciosEnBlanco(cadena));
    }

    @Test
    public void test_textoFinal_contieneTexto(){
        String cadena = "Hola que tal";
        assertEquals(cadena, fv.textoFinal(cadena));
    }

    @Test
    public void test_textoFinal_NOcontieneTexto(){
        String cadena = "";
        assertEquals("Texto por defecto", fv.textoFinal(cadena));
    }

    @Test
    public void test_nombreCurso(){
        String nombre = "BasesDeDatos/DDL";
        assertEquals("DDL", fv.nombreCurso(nombre));
    }

    @Test
    public void test_obtenerClase_CG(){
        String nombre = "CursosGestores";
        assertEquals(cursosGestores.class, fv.obtenerClase(nombre));
    }

    @Test
    public void test_obtenerClase_CA(){
        String nombre = "CursosAlumnos";
        assertEquals(cursosAlumnos.class, fv.obtenerClase(nombre));
    }

    @Test
    public void test_obtenerClase_EC(){
        String nombre = "EditorCursos";
        assertEquals(EditorCursos.class, fv.obtenerClase(nombre));
    }

    @Test
    public void test_getIP(){
        FuncionesVarias fv1 = new FuncionesVarias("192.168.5.4");
        String IP = "192.168.5.4";
        assertEquals(IP, fv1.getIP());
    }

    @Test
    public void test_getURL(){
        FuncionesVarias fv1 = new FuncionesVarias("192.168.5.4");
        fv1.setURL("http://"+fv1.getIP()+"/tfg/app/API/");
        String URL = "http://192.168.5.4/tfg/app/API/";
        assertEquals(URL, fv1.getURL());
    }
}