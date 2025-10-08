
package relacionesuml;


public class Calculadora {
    public void calcular(Impuesto impuesto){
        double monto= impuesto.getMonto();
        double montoTotal= monto*1.5; //impuesto x del 50%
        System.out.println("Contribuyente"+impuesto.getContribuyente().getNombre());
        System.out.println("Cuil: "+impuesto.getContribuyente().getCuil());
        System.out.println("monto basico: "+monto);
        System.out.println("Monto total: " +montoTotal);
    
    }
}
