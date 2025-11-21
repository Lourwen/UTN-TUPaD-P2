
package coleccionesystock.Eje2_Biblioteca;

public class MainEje2 {

    public static void main(String[] args) {

        // 1. Crear biblioteca
        Biblioteca biblio = new Biblioteca("Biblioteca Nacional");

        // 2. Crear autores
        Autor a1 = new Autor("A1", "Haruki Murakami", "Japon");
        Autor a2 = new Autor("A2", "Yasunari Kawabata", "Japon");
        Autor a3 = new Autor("A3", "Isabel Allende", "Chile");

        // 3. Agregar libros
        biblio.agregarLibro("ISBN001", "Kafka en la orilla", 2002, a1);
        biblio.agregarLibro("ISBN002", "Pais de nieve", 1947, a2);
        biblio.agregarLibro("ISBN003", "Lo bello y lo triste", 1965, a2);
        biblio.agregarLibro("ISBN004", "La casa de los espiritus", 1982, a3);
        biblio.agregarLibro("ISBN005", "Mil grullas", 1952, a2);

        // 4. Listar libros
        biblio.listarLibros();

        // 5. Buscar por ISBN
        Libro buscado = biblio.buscarLibroPorIsbn("ISBN003");
        if (buscado != null) buscado.mostrarInfo();

        // 6. Filtrar por año
        System.out.println("\nLibros del anio 1963:");
        for (Libro l : biblio.filtrarLibrosPorAnio(1963)) {
            System.out.println(" - " + l);
        }

        // 7. Eliminar un libro
        biblio.eliminarLibro("ISBN001");

        // 8. Cantidad total
        System.out.println("\nCantidad total de libros: " + biblio.obtenerCantidadLibros());

        // 9. Autores disponibles
        biblio.mostrarAutoresDisponibles();
    }
}


