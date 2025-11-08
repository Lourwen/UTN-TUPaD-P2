
package tp7_herenciaypolimorfismo.kata3;

public class EmpleadoPlanta extends Empleado {
    private double bonificacion;

    public EmpleadoPlanta(String nombre, double salarioPiso, double bonificacion) {
        super(nombre, salarioPiso);
        this.bonificacion = bonificacion;
    }

    @Override
    public double calcularSueldo() {
        return getSalarioPiso() + bonificacion;
    }
}
