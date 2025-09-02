
package ejerpoo;

public class Estudiante {
    String nombre;
    String apellido;
    String curso;
    double calificacion;

public void mostrarInfo(){
    System.out.println("Nombre: "+nombre+" "+apellido);
    System.out.println("curso: "+curso);
    System.out.println("Calificacion: "+calificacion);
  
    }
public void subirCalificacion(double puntos){
    calificacion += puntos;
        System.out.println("Nueva calificacion: "+calificacion);
}
public void bajarCalificacion(double puntos){
    calificacion -= puntos;
        System.out.println("Nueva calificacion: "+calificacion);
    }
 
    public static void main(String[] args) {
        Estudiante e =new Estudiante();
        e.nombre = "Lourdes";
        e.apellido = "Paco";
        e.curso = "Programacion 2";
        e.calificacion = 8.0;
        
        //invocar metodos
        e.mostrarInfo();
        e.subirCalificacion(1.5);
        e.bajarCalificacion(1.5);
        e.mostrarInfo();
    }
}