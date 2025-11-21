
package coleccionesystock.Eje2_Biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
      private String nombre;
    private List<Libro> libros;

    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.libros = new ArrayList<>();
    }

    // 1. Agregar libro
    public void agregarLibro(String isbn, String titulo, int anioPublicacion, Autor autor) {
        Libro nuevo = new Libro(isbn, titulo, anioPublicacion, autor);
        libros.add(nuevo);
    }

    // 2. Listar libros
    public void listarLibros() {
        if (libros.isEmpty()) {
            System.out.println("sin  coincidencias");
            return;
        }
        for (Libro l : libros) {
            l.mostrarInfo();
        }
    }

    // 3. Buscar por ISBN
    public Libro buscarLibroPorIsbn(String isbn) {
        for (Libro l : libros) {
            if (l.getIsbn().equalsIgnoreCase(isbn)) {
                return l;
            }
        }
        return null;
    }

    // 4. Eliminar libro
    public boolean eliminarLibro(String isbn) {
        Libro encontrado = buscarLibroPorIsbn(isbn);
        if (encontrado != null) {
            libros.remove(encontrado);
            return true;
        }
        return false;
    }

    // 5. Cantidad total
    public int obtenerCantidadLibros() {
        return libros.size();
    }

    // 6. Filtrar por año
    public List<Libro> filtrarLibrosPorAnio(int anio) {
        List<Libro> filtrados = new ArrayList<>();
        for (Libro l : libros) {
            if (l.getAnioPublicacion() == anio) {
                filtrados.add(l);
            }
        }
        return filtrados;
    }

    // 7. Mostrar autores disponibles
    public void mostrarAutoresDisponibles() {
        System.out.println("\nAutores en esta biblioteca:");
        ArrayList<String> nombres = new ArrayList<>();

        for (Libro l : libros) {
            if (!nombres.contains(l.getAutor().getNombre())) {
                System.out.println(" - " + l.getAutor());
                nombres.add(l.getAutor().getNombre());
            }
        }
    }
}
