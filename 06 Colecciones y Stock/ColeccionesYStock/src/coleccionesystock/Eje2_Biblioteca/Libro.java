
package coleccionesystock.Eje2_Biblioteca;

public class Libro {
    private String isbn;
    private String titulo;
    private int anioPublicacion;
    private Autor autor;  // composicion con Autor

    public Libro(String isbn, String titulo, int anioPublicacion, Autor autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.autor = autor;
    }

    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public int getAnioPublicacion() { return anioPublicacion; }
    public Autor getAutor() { return autor; }

    public void mostrarInfo() {
        System.out.println("Libro: " + titulo);
        System.out.println("ISBN: " + isbn);
        System.out.println("Anio: " + anioPublicacion);
        System.out.println("Autor: " + autor); // usa toString de Autor
        System.out.println("....................");
    }

    @Override
    public String toString() {
        return titulo + " (" + isbn + ") - " + anioPublicacion + " - " + autor;
    }
}
