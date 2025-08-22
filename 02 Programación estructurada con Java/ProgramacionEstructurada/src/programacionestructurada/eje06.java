
package programacionestructurada;

import java.util.Scanner;


public class eje06 {
public static void main(String[]args){
/*6.Contador de Positivos, Negativos y Ceros (for). Escribe un programa que
pida al usuario ingresar 10 números enteros y cuente cuántos son positivos,
negativos y cuántos son ceros.   
    */
Scanner input =new Scanner(System.in);
int positivos = 0;
int negativos = 0;
int ceros = 0;
int num =0;
for(int i=1; i<=10; i= i + 1){
    System.out.println("Ingrese el numero "+ i+" de 10 numeros: ");
    num = Integer.parseInt(input.nextLine());
    
   if(num>0){
   positivos = positivos + 1;}
   else if(num <0){
   negativos =negativos+1;}
   else{
   ceros=ceros+1;}
}
  System.out.println("Resultados:");
  System.out.println("Positivos: "+positivos);
  System.out.println("Negativos: "+negativos);
  System.out.println("Ceros: "+ceros);
    }
    
}
