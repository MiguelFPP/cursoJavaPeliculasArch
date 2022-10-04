package pliculas.datos;

import java.io.*;
import java.util.*;

import accesodatosex.AccesoDatosEx;
import accesodatosex.EscrituraDatosEx;
import accesodatosex.LecturaDatosEx;
import peliculas.domain.Pelicula;

public class AccesoDatosImpl implements AccesosDatos {

    @Override
    public boolean existe(String nombreRecurso) {
        File archivo = new File(nombreRecurso);

        return archivo.exists();
    }

    @Override
    public List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx {
        File archivo = new File(nombreRecurso);

        List<Pelicula> peliculas = new ArrayList<>();
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();

            while (linea != null) {
                Pelicula pelicula = new Pelicula(linea);
                peliculas.add(pelicula);
                linea = entrada.readLine();
            }

            entrada.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new LecturaDatosEx("Excepcion al listar peliculas, " + e.getMessage());
        } catch (IOException e) {
            throw new LecturaDatosEx("Excepcion al listar peliculas, " + e.getMessage());
        }

        return peliculas;
    }

    @Override
    public void escribir(Pelicula pelicula, String nombreRecurso, boolean anexan) throws EscrituraDatosEx {
        File archivo = new File(nombreRecurso);
        try {
            var salida = new PrintWriter(new FileWriter(archivo, anexan));

            salida.println(pelicula.toString());

            salida.close();

            System.out.println("Se ha escrito informacion en el archivo: " + pelicula);
        } catch (IOException e) {
            throw new EscrituraDatosEx("Excepcion al escribir peliculas, " + e.getMessage());
        }
    }

    @Override
    public String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx {
        File archivo = new File(nombreRecurso);
        String Resultado = null;

        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;

            linea = entrada.readLine();
            int indice = 1;

            while (linea != null) {
                if (buscar != null && buscar.equalsIgnoreCase(linea)) {
                    Resultado = "Pelicula encontrada " + linea + "encontrada en el indice " + indice;
                    break;
                }
                linea = entrada.readLine();
                indice++;
            }

            entrada.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new LecturaDatosEx("Excepcion al buscar pelicula, " + e.getMessage());
        } catch (IOException e) {
            throw new LecturaDatosEx("Excepcion al listar peliculas, " + e.getMessage());
        }

        return Resultado;
    }

    @Override
    public void crear(String nombreRecurso) throws AccesoDatosEx {
        File archivo = new File(nombreRecurso);

        try {
            var salida = new PrintWriter(new FileWriter(archivo));
            salida.close();
            System.out.println("Pelicula creada");
        } catch (IOException e) {
            throw new AccesoDatosEx("Excepcion al crear archivo, " + e.getMessage());
        }
    }

    @Override
    public void borrar(String nombreRecurso) {
        File archivo = new File(nombreRecurso);

        if (archivo.exists())
            archivo.delete();

        System.out.println("Se ha borrado el archivo");
    }

}
