package ejerpoo;

public class Libro {
private String titulo;
private String autor;
private int anioPublicacion;

public Libro (String t, String a,int anio){
    titulo = t;
    autor =a;
    anioPublicacion= anio;
}
 // Getter
public String getTitulo() {
    return titulo;
    }
public String getAutor() {
    return autor;
    }    
public int getAnioPublicacion() {
    return anioPublicacion;
    }   
//setter con validacion

public void setAnioPublicacion(int anio){
    if (anio >0 && anio <=2025){
        anioPublicacion =anio;    
    } else {
        System.out.println("Error :"+anio+ "no es un año valido");
    }
}
//metodo para mostrar datos
public void mostrarInfo() {
        System.out.println("Titulo: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Año de publicacion: " + anioPublicacion);
    }

 public static void main(String[] args) {
 Libro l = new Libro("La hija del curandero", "Amy tan", 2001);

        l.mostrarInfo();

        // Intento de anio invalido
        l.setAnioPublicacion(4000); // muestra mensaje de error

        // Intento de anio valido
        l.setAnioPublicacion(2000);

        l.mostrarInfo();
    }
}