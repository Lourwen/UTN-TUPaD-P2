
package interfacesyexcepciones.Excepciones;

import java.util.Scanner;

public class ValidacionDeEdad {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese su edad: ");
        int edad = sc.nextInt();

        try {
            if (edad < 0 || edad > 120) {
                throw new EdadInvalidaException("Edad invalida, debe tener entre 0 y 120 anios.");
            }
            System.out.println("Edad valida: " + edad);

        } catch (EdadInvalidaException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
            System.out.println("Validacion de edad terminada");
        }
    }
}
