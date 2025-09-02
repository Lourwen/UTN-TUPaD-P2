
package ejerpoo;

public class Mascota {
    String nombre;
    String especie;
    int edad;
    
public void mostrarInfo(){
    System.out.println("Nombre: "+nombre);
    System.out.println("Especie: "+especie);
    System.out.println("Edad: "+edad);

}    
    
public void cumplirAnios(){
edad = edad + 1;
System.out.println(nombre+" festeja su cumpleanios numero "+edad+"!!");

}    
public static void main(String[] args) {
       
    //invocar metodos    
    Mascota m = new Mascota();
    m.nombre = "Arena";
    m.especie = "nn";
    m.edad = 12;
        
    m.cumplirAnios();
    m.mostrarInfo();    
    }
    
}
