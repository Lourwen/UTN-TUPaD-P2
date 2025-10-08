
package relacionesuml;


public class Eje7 {
    public static void main(String[] args){
    /*7.Vehículo - Motor - Conductor
a.Agregación: Vehículo → Motor
b.Asociación bidireccional: Vehículo ↔ Conductor
     */
    Motor motor = new Motor("hibrido","MHEV");
    Vehiculo vehiculo = new Vehiculo("ASD 456","Corolla Hybrid ",motor);
    Conductor conductor= new Conductor("Lourdes","profesional");
    
    vehiculo.setConductor(conductor);
    conductor.setVehiculo(vehiculo);
    
        System.out.println("Patente: " + vehiculo.getPatente());
        System.out.println("Modelo: " + vehiculo.getModelo());
        System.out.println("Motor: " + vehiculo.getMotor());
        System.out.println("Conductor: " + vehiculo.getConductor().getNombre());
        System.out.println("Licencia: " + vehiculo.getConductor().getLicencia());
    }
      
    
}
