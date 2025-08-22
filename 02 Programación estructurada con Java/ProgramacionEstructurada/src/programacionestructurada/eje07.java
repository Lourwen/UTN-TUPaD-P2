
package programacionestructurada;

import java.util.Scanner;


public class eje07 {
    public static void main(String[]args){
/*7.Validación de Nota entre 0 y 10 (do-while).Escribe un programa que 
solicite al usuario una nota entre 0 y 10. Si el usuario ingresa un número 
fuera de este rango, debe seguir pidiéndole la nota hasta que ingrese un valor válido.       
        */
Scanner input = new Scanner(System.in);
double nota;
    
do{
    System.out.println("Ingrese una nota entre 0 y 10: ");
    nota = Double.parseDouble(input.nextLine());
    if (nota < 0 || nota > 10) {
        System.out.println("Error: ingrese una nota de 0 a 10.");
        }
}while (nota <0 || nota >10);
        System.out.println("La Nota "+nota+ " fue guardada correctamente");    
    }
}
