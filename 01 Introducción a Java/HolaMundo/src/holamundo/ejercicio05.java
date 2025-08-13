
package holamundo;

import java.util.Scanner;


public class ejercicio05 {
    public static void main(String[] args){
/*5.Escribe un programa que solicite dos números enteros y realice las siguientes
operaciones: suma, resta, multiplicación, división. Muestre los resultados en consola.*/
    Scanner input = new Scanner(System.in);
    int num1,num2;
    double suma,resta,multiplicacion,division;
    //solicitud al usuario
        System.out.println("Ingrese un numero: ");
        num1 = input.nextInt();
        System.out.println("Ingrese otro numnero: ");
        num2 = input.nextInt();
        suma = num1 + num2;
        resta = num1 - num2;
        multiplicacion = num1*num2;
        division = num1/(num2*1.0);
        //Impresion de resultados
        System.out.println("suma: "+suma+" ,resta: "+resta+" ,multiplicacion: "+multiplicacion+" ,division: "+multiplicacion);
        


    }
}
