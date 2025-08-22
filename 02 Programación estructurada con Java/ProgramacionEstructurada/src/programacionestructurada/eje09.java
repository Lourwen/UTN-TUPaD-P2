
package programacionestructurada;

import java.util.Scanner;

public class eje09 {
/*A.calcularCostoEnvio(double peso, String zona): Calcula el costo de envío basado
en la zona de envío (Nacional o Internacional) y el peso del paquete.
Nacional: $5 por kg Internacional: $10 por kg    
    */
    public static double calcularCostoEnvio(double peso, String zona){
        double costoEnvio = 0;
        
        if(zona.equalsIgnoreCase("Nacional")) {
            costoEnvio = peso * 5;
        } else if(zona.equalsIgnoreCase("Internacional")) {
            costoEnvio = peso * 10;
        } else {
            System.out.println("Error. Use 'Nacional' o 'Internacional'.");
        }
        return costoEnvio;                
}
/*B.calcularTotalCompra(double precioProducto, double costoEnvio): 
Usa calcularCostoEnvio para sumar el costo del producto con el costo de envío.    
    */    
    public static double calcularTotalCompra(double precioProducto, double costoEnvio){
        return precioProducto + costoEnvio;                          
}      
    
public static void main(String[]args){
  
    Scanner input = new Scanner(System.in);
    
    //Solicitamos los datos para el calculo
    System.out.print("Ingrese el peso del paquete en Kg: ");
    double peso = Double.parseDouble(input.nextLine());
    
    System.out.print("Ingrese la zona del envio (Nacional o Internacional: ");
    String zona = input.nextLine();
    
    System.out.print("Ingrese el precio del producto: ");
    double precioProducto = Double.parseDouble(input.nextLine());
       
    // Llamamos al metodo calcularCostoEnvio
    double costoEnvio = calcularCostoEnvio(peso, zona);
    
    // Calculamos el total de la compra
    double totalCompra = calcularTotalCompra(precioProducto, costoEnvio);
    
    System.out.println("El precio del costo de envio es: "+costoEnvio);
    System.out.println("El total a pagar es: "+ totalCompra);
    
    }
}

