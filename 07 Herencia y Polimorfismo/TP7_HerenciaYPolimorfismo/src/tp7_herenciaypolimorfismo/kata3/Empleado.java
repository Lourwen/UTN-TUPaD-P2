
package tp7_herenciaypolimorfismo.kata3;

public abstract class Empleado {
    private String nombre;
    private double salarioPiso;

    public Empleado(String nombre, double salarioPiso) {
        this.nombre = nombre;
        this.salarioPiso = salarioPiso;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalarioPiso() {
        return salarioPiso;
    }

    public void mostrarInfo() {
        System.out.println("Empleado: " + nombre);
    }

    public abstract double calcularSueldo();
}
