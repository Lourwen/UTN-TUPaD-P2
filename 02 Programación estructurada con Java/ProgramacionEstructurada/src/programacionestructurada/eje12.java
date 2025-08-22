
package programacionestructurada;


public class eje12 {
/*12.Modificación de un array de precios y visualización de resultados.
Crea un programa que:
a.	Declare e inicialice un array con los precios de algunos productos.
b.	Muestre los valores originales de los precios.
c.	Modifique el precio de un producto específico.
d.	Muestre los valores modificados.    
    */
    public static void main(String[] args) {
        // a. Declarar e inicializar un array con precios
        double[] precios = {199.99, 299.50, 149.75, 399.00, 89.99};

        // b. Mostrar valores originales
        System.out.println("Precios originales:");
        for (double precio : precios) {
            System.out.println("Precio: $" + precio + " ");
        }
        
        // c. Modificar el precio de un producto específico ej.indice= 2)
        precios[2] = 129.99;

        // d. Mostrar valores modificados
        System.out.println("Precios modificados:");
        for (double precio : precios) {
            System.out.println("Precio: $" + precio + " ");
        }
        System.out.println();
    }
} 
    
    
    
    


