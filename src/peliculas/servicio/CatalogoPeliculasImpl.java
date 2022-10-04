package peliculas.servicio;

import accesodatosex.AccesoDatosEx;
import peliculas.domain.Pelicula;
import pliculas.datos.AccesoDatosImpl;
import pliculas.datos.AccesosDatos;

public class CatalogoPeliculasImpl implements ICatalogoPeliculas {

    private final AccesosDatos datos;

    public CatalogoPeliculasImpl() {
        this.datos = new AccesoDatosImpl();
    }

    /**
     * The function agregarPelicula() takes a String as a parameter and creates a
     * new Pelicula object
     * with the String as a parameter. Then it checks if the file exists and if it
     * does, it appends the
     * new Pelicula object to the file
     * 
     * @param nombrepelicula String
     */
    @Override
    public void agregarPelicula(String nombrepelicula) {
        Pelicula pelicula = new Pelicula(nombrepelicula);
        boolean anexar = false;
        try {
            anexar = datos.existe(NOMBRE_RECURSO);
            datos.escribir(pelicula, NOMBRE_RECURSO, anexar);
        } catch (AccesoDatosEx e) {
            System.out.println("Error de acceso a datos");
            e.printStackTrace(System.out);
        }
    }

    /**
     * The function listarPeliculas() is a method of the class AccesoDatosImpl that
     * implements the
     * interface AccesoDatos. The method is called by the main() function of the
     * class Principal. The
     * method uses the listar() method of the class Datos to list the contents of
     * the file
     * NOMBRE_RECURSO
     */
    @Override
    public void listarPeliculas() {
        try {
            var peliculas = this.datos.listar(NOMBRE_RECURSO);

            for (var pelicula : peliculas) {
                System.out.println("pelicula: " + pelicula);
            }
        } catch (AccesoDatosEx e) {
            System.out.println("Error de acceso a datos");
            e.printStackTrace(System.out);
        }
    }

    /**
     * The function buscarPelicula() is a method of the class AccesoDatosImpl. It
     * takes a String as a
     * parameter and returns a String. It calls the method buscar() of the class
     * ImplementacionMySql
     * 
     * @param buscar String
     */
    @Override
    public void buscarPelicula(String buscar) {
        String resultado = null;
        try {
            resultado = this.datos.buscar(NOMBRE_RECURSO, buscar);
        } catch (AccesoDatosEx e) {
            System.out.println("Error de acceso a datos");
            e.printStackTrace(System.out);
        }
        System.out.println("resultado = " + resultado);
    }

    /**
     * If the file exists, delete it and create a new one. If it doesn't exist, create a new one.
     */
    @Override
    public void iniciarCatalogoPeliculas() {
        try {
            if (this.datos.existe(NOMBRE_RECURSO)) {
                datos.borrar(NOMBRE_RECURSO);
                datos.crear(NOMBRE_RECURSO);
            } else {
                datos.crear(NOMBRE_RECURSO);
            }
        } catch (AccesoDatosEx e) {
            System.out.println("Error de acceso a datos");
            e.printStackTrace(System.out);
        }
    }

}
