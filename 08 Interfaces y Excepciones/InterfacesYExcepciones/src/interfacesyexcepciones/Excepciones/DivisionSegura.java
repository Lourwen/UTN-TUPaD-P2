
package interfacesyexcepciones.Excepciones;

import java.util.Scanner;
public class DivisionSegura {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Ingrese dividendo: ");
            int dividendo = sc.nextInt();

            System.out.print("Ingrese divisor: ");
            int divisor = sc.nextInt();

            int resultado = dividendo / divisor;
            System.out.println("Resultado: " + resultado);

        } catch (ArithmeticException e) {
            System.out.println("Error: no se puede dividir por cero.");
        } finally {
            sc.close();
            System.out.println("Operacion de division segura terminada.");
        }
    }
}
