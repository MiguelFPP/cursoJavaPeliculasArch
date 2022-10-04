package pliculas.datos;

import java.util.List;

import accesodatosex.AccesoDatosEx;
import accesodatosex.EscrituraDatosEx;
import accesodatosex.LecturaDatosEx;
import peliculas.domain.Pelicula;

public interface AccesosDatos {
    boolean existe(String nombreRecurso) throws AccesoDatosEx;

    List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx;

    void escribir(Pelicula pelicula, String nombreRecurso, boolean anexan) throws EscrituraDatosEx;

    String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx;

    void crear(String nombreRecurso) throws AccesoDatosEx;

    void borrar(String nombreRecurso) throws AccesoDatosEx;
}
