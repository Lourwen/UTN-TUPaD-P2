
package relacionesuml;


public class Eje2 {
 /*2.Celular - Batería - Usuario
a.Agregación: Celular → Batería
b.Asociación bidireccional: Celular ↔ Usuario
*/
public static void main (String []args){
    Bateria bateria =new Bateria("Google pixel 06",300.0);
    Celular celular = new Celular("NoSeQEsUnImei","Google", "pixel06",bateria);
    Usuario usuario = new Usuario("Lourdes Paco","33453101");
    
    //vinculacion de relaciones
    
    
    celular.setUsuario(usuario);
    usuario.setCelular(celular);
    
    //impresion de datos
    System.out.println("Usuario: "+ usuario.getNombre());
    System.out.println("DNI: "+usuario.getDni());
    System.out.println("Celular: "+usuario.getCelular() );
    System.out.println("Marca:"+ usuario.getCelular().getMarca());
    System.out.println("Modelo:" + usuario.getCelular().getModelo());
    System.out.println("Bateria para "+usuario.getCelular().getBateria()+" Ah");
   
    
    }
}
