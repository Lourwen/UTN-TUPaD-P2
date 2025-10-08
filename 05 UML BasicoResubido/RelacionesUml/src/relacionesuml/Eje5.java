
package relacionesuml;


public class Eje5 {
    public static void main(String[] args) {
       /*5.Computadora - PlacaMadre - Propietario
a.Composición: Computadora → PlacaMadre
b.Asociación bidireccional: Computadora ↔ Propietario

        
        */
        Propietario propietario = new Propietario("Lourdes Paco", "33453101");     
        Computadora computadora = new Computadora("Mouse", "B4-I5U01SR-A", "E-ATX", "z790");// composición interna de la placa madre

       
        computadora.setPropietario(propietario);
        propietario.setComputadora(computadora);

        
        System.out.println("Propietario: " + propietario.getNombre());
        System.out.println("DNI: " + propietario.getDni());
        System.out.println("Computadora: " + propietario.getComputadora().getMarca());
        System.out.println("Numero de serie: " + propietario.getComputadora().getNumeroSerie());
        System.out.println("Modelo: "+propietario.getComputadora().getPlacaMadre().getModelo());
        System.out.println("Placa Madre: "+propietario.getComputadora().getPlacaMadre().getChipSet());
        
    }
}
