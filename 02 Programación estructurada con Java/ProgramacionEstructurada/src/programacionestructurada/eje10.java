
package programacionestructurada;

import java.util.Scanner;

public class eje10 {
    /*10.Actualización de stock a partir de venta y recepción de productos.
Crea un método actualizarStock(int stockActual, int cantidadVendida,
int cantidadRecibida), que calcule el nuevo stock después de una venta y recepción
de productos:
NuevoStock = StockActual − CantidadVendida + CantidadRecibida 
NuevoStock = CantidadVendida + CantidadRecibida
Desde main(), solicita al usuario el stock actual, la cantidad vendida y la cantidad recibida, y muestra el stock actualizado.   
    */
//metodo para actualizacion de stock
    public static int actualizarStock(int stockActual,int cantidadVendida, int cantidadRecibida){
        int stock = stockActual - cantidadVendida + cantidadRecibida;
        return stock;
}
    
public static void main(String[]args){

    Scanner input = new Scanner(System.in);
    //Solicitamos los datos para el calculo
    System.out.print("Ingrese el stock actual del producto: ");
    int stockActual = Integer.parseInt(input.nextLine());
    
    System.out.print("Ingrese la cantidad vendida: ");
    int cantidadVendida = Integer.parseInt(input.nextLine());

    System.out.print("Ingrese la cantidad recibida: ");
    int cantidadRecibida = Integer.parseInt(input.nextLine());

    // Llamamos al metodo
    int stock = actualizarStock(stockActual, cantidadVendida, cantidadRecibida);
    
    System.out.println("El nuevo stock del producto es: "+ stock);
    
    }
    
}

