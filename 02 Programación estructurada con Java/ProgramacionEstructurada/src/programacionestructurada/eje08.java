
package programacionestructurada;

import java.util.Scanner;

public class eje08 {
        /*8.Cálculo del Precio Final con impuesto y descuento. Crea un método
    calcularPrecioFinal(double impuesto, double descuento) que calcule el
    precio final de un producto en un e-commerce. La fórmula es:
    PrecioFinal = PrecioBase + (PrecioBase×Impuesto) − (PrecioBase×Descuento)
    PrecioFinal = PrecioBase + (PrecioBase \times Impuesto) - (PrecioBase \times Descuento)        
        */
    //metodo para precio final
    public static double calcularPrecioFinal(double precioBase,double impuesto,double descuento){
        double precioFinal = precioBase + (precioBase * (impuesto / 100)) - (precioBase * (descuento / 100));
        return precioFinal;
}
    
public static void main(String[]args){

    Scanner input = new Scanner(System.in);
    //Solicitamos los datos para el calculo
    System.out.print("Ingrese el precio base de un producto: ");
    double precioBase = Double.parseDouble(input.nextLine());
    
    System.out.print("Ingrese el impuesto en porcentaje: ");
    double impuesto = Double.parseDouble(input.nextLine());

    System.out.print("Ingrese el descuento en porcentaje: ");
    double descuento = Double.parseDouble(input.nextLine());

    // Llamamos al metodo
    double precioFinal = calcularPrecioFinal(precioBase,impuesto,descuento);
    
    System.out.println("El precio final es: "+precioFinal);
    
    
    }
}
