
package coleccionesystock.Eje3_Universidad;

public class Curso {
    private String codigo;
    private String nombre;
    private Profesor profesor;

    public Curso(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.profesor = null;
    }

    // Asignacion bidireccional

    public void setProfesor(Profesor p) {
        // 1. Si tenia profesor, limpiarlo del otro lado
        if (this.profesor != null) {
            Profesor anterior = this.profesor;
            this.profesor = null;
            anterior.getCursos().remove(this);
        }

        // 2. Asignar el nuevo profesor
        this.profesor = p;

        // 3. Agregar al listado del profesor
        if (p != null && !p.getCursos().contains(this)) {
            p.getCursos().add(this);
        }
    }


    public void mostrarInfo() {
        System.out.println("Curso: " + nombre);
        System.out.println("Codigo: " + codigo);
        System.out.println("Profesor: " +
            (profesor != null ? profesor.getNombre() : "Sin asignar"));
        System.out.println("----------------------");
    }

    // GETTERS
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public Profesor getProfesor() { return profesor; }

    @Override
    public String toString() {
        return nombre + " (" + codigo + ")";
    }
}
