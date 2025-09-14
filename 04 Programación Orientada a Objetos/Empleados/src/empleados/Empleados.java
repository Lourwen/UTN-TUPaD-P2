
package empleados;


public class Empleados {

    public static void main(String[] args) {
        Empleado e1=new Empleado(1,"Lourdes","Tester1",10000);
        Empleado e2=new Empleado("Rosa","Tester2");
 
    //Aumentos
    e1.actualizarSalario(15); //15%de aumento salarial
    e2.actualizarSalario(12500); //Cantidad fija de aumento
    
        System.out.println(e1);
        System.out.println(e2);
        
        System.out.println("Total EMPLEADS: "+ Empleado.mostrarTotalEmpleados());
}
}
