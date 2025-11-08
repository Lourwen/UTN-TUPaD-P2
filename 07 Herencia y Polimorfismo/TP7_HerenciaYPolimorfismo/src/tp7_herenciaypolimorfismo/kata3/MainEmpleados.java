
package tp7_herenciaypolimorfismo.kata3;


public class MainEmpleados {
    public static void main(String[] args) {

        Empleado[] empleados = new Empleado[3];
        empleados[0] = new EmpleadoPlanta("Lourdes", 1500000, 20000);
        empleados[1] = new EmpleadoTemporal("Paco", 50000, 120, 1200);
        empleados[2] = new EmpleadoPlanta("Laura", 1800000, 30000);

        for (Empleado e : empleados) {
            e.mostrarInfo();
            System.out.println("Sueldo: $" + e.calcularSueldo());

            if (e instanceof EmpleadoPlanta) {
                System.out.println("Tipo: Planta Permanente");
            } else if (e instanceof EmpleadoTemporal) {
                System.out.println("Tipo:Temporal/Contrato Temporal");
            }

            System.out.println("//////////////////////////////////");
        }
    }
}
