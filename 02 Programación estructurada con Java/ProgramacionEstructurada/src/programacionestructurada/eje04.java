
package programacionestructurada;

import java.util.Scanner;


public class eje04 {
 public static void main(String[]args){
/*4.Calculadora de Descuento según categoría.
Escribe un programa que solicite al usuario el precio de un producto y su categoría (A, B o C).
Luego, aplique los siguientes descuentos:
Categoría A: 10% de descuento Categoría B: 15% de descuento Categoría C: 20% de descuento
El programa debe mostrar el precio original, el descuento aplicado y el precio final     
     */
 Scanner input = new Scanner (System.in);
 double  precioOriginal,precioFinal,descuento ;
 String categoria;
 
 System.out.println("Ingrese el precio del producto: ");
 precioOriginal = Double.parseDouble(input.nextLine());
 
 System.out.println("Ingrese la categoria del producto (A,B,C): ");
 categoria = input.nextLine();

 if(categoria.equals("A")) {
    descuento = precioOriginal * 0.10;
}else if (categoria.equals("B")) {
    descuento = precioOriginal * 0.15;
}else if (categoria.equals("C")) {
    descuento = precioOriginal * 0.20;
}else {
    descuento = 0;
}
 precioFinal = precioOriginal-descuento;
         
     System.out.println("Precio Original: $"+ precioOriginal);
     System.out.println("Descuento aplicado: $"+descuento);
     System.out.println("Precio final: $"+precioFinal);
 
  
 }
}
