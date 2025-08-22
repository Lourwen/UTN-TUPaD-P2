
package programacionestructurada;

import java.util.Scanner;

public class eje02 {

    public static void main(String[] args ){
    
        /*2.Determinar el Mayor de Tres Números. Escribe un programa en Java 
    que pida al usuario tres números enteros y determine cuál es el mayor.
*/
    Scanner input = new Scanner(System.in);    
    int num1, num2,num3,mayorValor;
        System.out.println("Ingrese el primer numero: ");
        num1 = Integer.parseInt(input.nextLine());
        
        System.out.println("Ingrese el sedundo numero: ");
        num2 = Integer.parseInt(input.nextLine());
        
        System.out.println("Ingrese el tercer numero: ");
        num3 = Integer.parseInt(input.nextLine());
        
        if(num1>= num2 && num1>=num3){
            mayorValor = num1;}else if(num2 >= num1 && num2 >=num3){
            mayorValor = num2;} else{
            mayorValor = num3;}
        
        System.out.println("El numero mayor es: " +mayorValor);
    } 
}
