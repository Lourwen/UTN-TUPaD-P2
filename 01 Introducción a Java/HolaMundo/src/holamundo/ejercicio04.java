/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package holamundo;

import java.util.Scanner;


public class ejercicio04 {
    public static void main(String[] args){
        /* 4.Escribe un programa que solicite al usuario ingresar su nombre 
        y edad, y luego los muestre en pantalla. Usa Scanner para capturar los datos. */
        Scanner input = new Scanner(System.in);
        String nombre;
        int edad;
        System.out.println("Ingrese su nombre: ");
        nombre = input.nextLine();
        System.out.println("Ingrese su edad: ");
        edad = input.nextInt();
        System.out.println("Su nombre es: " +nombre+ ", su edad es: "+ edad);
        
        
}
}