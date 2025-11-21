
package coleccionesystock.Eje3_Universidad;

import java.util.ArrayList;
import java.util.List;

public class Universidad {
     private String nombre;
    private List<Profesor> profesores;
    private List<Curso> cursos;

    public Universidad(String nombre) {
        this.nombre = nombre;
        profesores = new ArrayList<>();
        cursos = new ArrayList<>();
    }
//Agregra profesor
    public void agregarProfesor(Profesor p) {
        if (p != null && !profesores.contains(p)) {
            profesores.add(p);
        }
    }
//Agregar curso
    public void agregarCurso(Curso c) {
        if (c != null && !cursos.contains(c)) {
            cursos.add(c);
        }
    }
//Buscar profesor por ID
    public Profesor buscarProfesorPorId(String id) {
        for (Profesor p : profesores) {
            if (p.getId().equals(id)) return p;
        }
        return null;
    }
//Buscar curso por codigo
    public Curso buscarCursoPorCodigo(String codigo) {
        for (Curso c : cursos) {
            if (c.getCodigo().equals(codigo)) return c;
        }
        return null;
    }
//Asignar profesor a curso
    public void asignarProfesorACurso(String codigoCurso, String idProfesor) {
        Curso c = buscarCursoPorCodigo(codigoCurso);
        Profesor p = buscarProfesorPorId(idProfesor);

        if (c != null && p != null) {
            c.setProfesor(p);
        }
    }
//Eliminar curso
    public void eliminarCurso(String codigo) {
        Curso c = buscarCursoPorCodigo(codigo);

        if (c != null) {
            if (c.getProfesor() != null) {
                c.setProfesor(null); // rompe relación
            }
            cursos.remove(c);
        }
    }
//Eliminar profesor
    public void eliminarProfesor(String id) {
        Profesor p = buscarProfesorPorId(id);

        if (p != null) {
            // poner en null los cursos que dictaba
            for (Curso c : new ArrayList<>(p.getCursos())) {
                c.setProfesor(null);
            }
            profesores.remove(p);
        }
    }
//Listar profesores
    public void listarProfesores() {
        for (Profesor p : profesores) {
            p.mostrarInfo();
        }
    }
//Listar Cursos
    public void listarCursos() {
        for (Curso c : cursos) {
            c.mostrarInfo();
        }
    }
}
