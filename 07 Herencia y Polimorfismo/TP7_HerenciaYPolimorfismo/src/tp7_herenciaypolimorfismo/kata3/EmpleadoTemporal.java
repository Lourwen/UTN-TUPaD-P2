
package tp7_herenciaypolimorfismo.kata3;



public class EmpleadoTemporal extends Empleado {
    private int horasTrabajadas;
    private double valorHora;

    public EmpleadoTemporal(String nombre, double salarioPiso, int horasTrabajadas, double valorHora) {
        super(nombre, salarioPiso);
        this.horasTrabajadas = horasTrabajadas;
        this.valorHora = valorHora;
    }

    @Override
    public double calcularSueldo() {
        return getSalarioPiso() + (horasTrabajadas * valorHora);
    }
}