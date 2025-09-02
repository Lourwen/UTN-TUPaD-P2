
package ejerpoo;
public class Gallina {
    String idGallina;
    int edad;
    int huevosPuestos;

public void ponerHuevos(){
huevosPuestos=huevosPuestos + 1;
    System.out.println("La gallina a puesto "+huevosPuestos+ " huevos");
}        
public void envejecer(){
    edad = edad + 1;
    System.out.println("La gallina a envejecido a "+edad+ " anios");
}
public void mostrarEstado(){      
System.out.println("La gallina "+idGallina+" cumplio "+edad+" anos y puso "+huevosPuestos+" huevos");
}



public static void main(String[] args){
//creamos dos gallinas
Gallina g1 = new Gallina();
g1.idGallina = "l1"; // gallina 1 de raza Leghorn
g1.edad= 1;
g1.huevosPuestos =2;

Gallina g2 = new Gallina();
g2.idGallina = "l2"; // gallina 2 de raza Leghorn
g2.edad= 2;
g2.huevosPuestos =3;        
//simulacion
g1.envejecer();
g1.ponerHuevos();
g1.mostrarEstado();

g2.envejecer();
g2.ponerHuevos();
g2.mostrarEstado();
    }

}






