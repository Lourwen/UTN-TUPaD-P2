
package programacionestructurada;

import java.util.Scanner;


public class eje03 {
    public static void main(String[]args){
    /*3.Clasificación de Edad. Escribe un programa en Java que solicite al usuario
    su edad y clasifique su etapa de vida según la siguiente tabla:
    Menor de 12 años: "Niño" 
    Entre 12 y 17 años: "Adolescente"
    Entre 18 y 59 años: "Adulto" 60 años o más: "Adulto mayor" 
    */
    Scanner input=new Scanner (System.in);
    int edad;
        System.out.println("Ingrese su edad: ");
        edad = Integer.parseInt(input.nextLine());
        if(edad >= 60){
            System.out.println("Eres un adulto Mayor");}
        else if (edad >=18 && edad <60){
            System.out.println("Eres un adulto");}
        else if(edad >=12 && edad <18 ){
            System.out.println("Eres un adolescente");}
        else {System.out.println("Eres un niño");}
            
    }
}
