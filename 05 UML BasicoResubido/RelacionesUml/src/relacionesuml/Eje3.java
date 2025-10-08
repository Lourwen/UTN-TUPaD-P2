
package relacionesuml;


public class Eje3 {
    public static void main(String[] args) {
        /*3.Libro - Autor - Editorial
a.Asociación unidireccional: Libro → Autor
b.Agregación: Libro → Editorial
 */
        Autor autor = new Autor("Manuel Puig", "Argentina");
        Editorial editorial = new Editorial("Sudamericana", "Humberto Primo 555, Capital Federal");
        Libro libro = new Libro("Boquitas pintadas", "111-222-33-5555-6", autor, editorial);

        System.out.println("Libro: " + libro.getTitulo());
        System.out.println("Autor: " + libro.getAutor().getNombre());
        System.out.println("Editorial: " + libro.getEditorial().getNombre());
    }
}

