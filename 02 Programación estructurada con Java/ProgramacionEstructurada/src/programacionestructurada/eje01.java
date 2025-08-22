
package programacionestructurada;

import java.util.Scanner;

public class eje01 {

    public static void main(String[] args) {
    /* 1.Verificación de Año Bisiesto.Escribe un programa en Java que solicite al usuario 
    un año y determine si es bisiesto. Un año es bisiesto si es divisible por 4, 
    pero no por 100, salvo que sea divisible por 400.
*/
    Scanner input = new Scanner(System.in);
    int anio;
        System.out.println("Ingrese un año: ");
        anio = Integer.parseInt(input.nextLine());
        if((anio%4==0 && anio%100!=0) ||(anio%400==0)) {
            System.out.println("El año "+anio+" es bisiesto");
        } else {
            System.out.println("El año "+anio+ " no es bisiesto");
        }
       
        }
    }
