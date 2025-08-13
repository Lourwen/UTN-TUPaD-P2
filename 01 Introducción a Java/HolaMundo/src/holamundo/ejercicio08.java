
package holamundo;

import java.util.Scanner;


public class ejercicio08 {
   public static void main(String[] args){
   /*8.	Manejar conversiones de tipo y división en Java.
    a.	Escribe un programa que divida dos números enteros ingresados por el usuario.
    b.	Modifica el código para usar double en lugar de int y compara los resultados.
*/  
   Scanner input = new Scanner (System.in);
   double num1,num2;
   double division;
   //Solicitud al usuario
       System.out.println("Ingrese un numero: ");
       num1 = input.nextInt();
       System.out.println("Ingrese otro numero: ");
       num2 = input.nextInt();
    division = num1/num2;   
       System.out.println("Resultado de la division: "+division);
 
   
   
   
   
   }
}
