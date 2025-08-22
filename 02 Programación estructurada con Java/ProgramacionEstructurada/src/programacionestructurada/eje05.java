
package programacionestructurada;

import java.util.Scanner;

public class eje05 {
public static void main(String[]args){

    /*5.Suma de Números Pares (while).
Escribe un programa que solicite números al usuario y sume solo los números pares.
El ciclo debe continuar hasta que el usuario ingrese el número 0, momento en el 
que se debe mostrar la suma total de los pares ingresados.
    */
Scanner input = new Scanner(System.in);
int num = -1;
int sumaTotal = 0;

while (num!= 0){
    System.out.println("Ingrese un numero (0 para terminar): ");
    num = Integer.parseInt(input.nextLine());
     if(num%2==0){
     sumaTotal = sumaTotal + num;
     }
}
    System.out.println("La suma de los numeros pares es: " +sumaTotal);
    }    
}
