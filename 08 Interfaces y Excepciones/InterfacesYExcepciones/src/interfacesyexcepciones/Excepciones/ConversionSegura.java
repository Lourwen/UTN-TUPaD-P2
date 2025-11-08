
package interfacesyexcepciones.Excepciones;

import java.util.Scanner;

public class ConversionSegura {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese un numero: ");
        String texto = sc.nextLine();

        try {
            int numero = Integer.parseInt(texto);
            System.out.println("Numero ingresado: " + numero);
        } catch (NumberFormatException e) {
            System.out.println("Error: el texto ingresado no corresponde a un numero valido.");
        } finally {
            sc.close();
            System.out.println("Conversion segura terminada");
        }
    }
}
