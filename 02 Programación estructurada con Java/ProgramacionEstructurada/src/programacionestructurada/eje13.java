
package programacionestructurada;


public class eje13 {
    // función recursiva para imprimir precios
    public static void imprimir(double[] precios, int i) {
        if (i == precios.length) return; // caso base
        System.out.print("Precio: $" + precios[i] + " ");
        imprimir(precios, i + 1); // llamada recursiva
    }

    public static void main(String[] args) {
        double[] precios = {199.99, 299.5, 149.75, 399.0, 89.99};

        System.out.println("Precios originales:");
        imprimir(precios, 0);
        System.out.println();

        // modificar el tercer precio (índice 2)
        precios[2] = 129.99;

        System.out.println("Precios modificados:");
        imprimir(precios, 0);
        
    }

}
