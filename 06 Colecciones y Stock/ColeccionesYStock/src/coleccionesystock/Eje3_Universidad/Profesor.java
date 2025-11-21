
package coleccionesystock.Eje3_Universidad;

import java.util.ArrayList;
import java.util.List;


public class Profesor {
    private String id;
    private String nombre;
    private String especialidad;
    private List<Curso> cursos;

    public Profesor(String id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.cursos = new ArrayList<>();
    }


    // Metodos de asociacion 
    
    //Agregar curso c
        public void agregarCurso(Curso c) {
        if (c == null) return;

        if (!cursos.contains(c)) {
            cursos.add(c);
        }

        if (c.getProfesor() != this) {
            c.setProfesor(this);  // sincroniza lado del curso
        }
    }
    // Eliminar curso
    public void eliminarCurso(Curso c) {
        if (c == null) return;
        if (cursos.contains(c)) {
            cursos.remove(c);
        }
        if (c.getProfesor() == this) {
            c.setProfesor(null);  // rompe asociación
        }
    }
//Listar curso
    public void listarCursos() {
        System.out.println("Cursos de " + nombre + ":");
        for (Curso c : cursos) {
            System.out.println(" - " + c.getCodigo() + " | " + c.getNombre());
        }
        System.out.println();
    }

    public void mostrarInfo() {
        System.out.println("Profesor: " + nombre);
        System.out.println("ID: " + id);
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Cantidad de cursos: " + cursos.size());
        System.out.println("----------------------");
    }

    // GETTERS
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public List<Curso> getCursos() { return cursos; }

    @Override
    public String toString() {
        return nombre + " [" + id + "]";
    }
}
