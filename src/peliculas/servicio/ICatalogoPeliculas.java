package peliculas.servicio;

public interface ICatalogoPeliculas {
    String NOMBRE_RECURSO = "peliculas.txt";

    void agregarPelicula(String nombrepelicula);
    void listarPeliculas();
    void buscarPelicula(String buscar);
    void iniciarCatalogoPeliculas();
}
