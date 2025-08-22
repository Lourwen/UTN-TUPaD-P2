
package programacionestructurada;

import java.util.Scanner;


public class eje11 {
    /*11.Cálculo de descuento especial usando variable global. Declara una variable global
Ejemplo de entrada/salida: = 0.10. Luego, crea un método calcularDescuentoEspecial(double precio) 
que use la variable global para calcular el descuento especial del 10%. Dentro del método, 
declara una variable local descuentoAplicado, almacena el valor del descuento y muestra el precio final con descuento    
    */
    //Bariable global
    static double descuento = 0.10;
    //metodo para precio final con descuento
    public static double calcularDescuentoEspecial(double precio){
        
        double descuentoAplicado = precio * descuento;// variable local
        double precioFinal = precio - descuentoAplicado;
        return precioFinal;
}    
public static void main(String[]args){

    Scanner input = new Scanner(System.in);
    
    //Solicitud del precio del preducto
    
    System.out.print("Ingrese el precio del producto: ");
    double precio = Double.parseDouble(input.nextLine());
    
    //metodo para calcular el descuento
   double precioFinal = calcularDescuentoEspecial(precio); 

    //calculo del descuento
    double descuentoApli = precio - precioFinal;
    //resultados
    System.out.println("El descuento especial aplicado es: "+ descuentoApli);    
    System.out.println("El precio final con decuento es: "+ precioFinal);
    
    }
          
    
}
