
package relacionesuml;


public class GeneradorQR {
     public void generar(String valor, Usuario usuario){
        CodigoQR codigoQR = new CodigoQR(valor, usuario);
       
        System.out.println("Valor: "+codigoQR.getValor());
        System.out.println("Usuario: "+codigoQR.getUsuario().getNombre());
        System.out.println("Mail de Usuario: " +codigoQR.getUsuario().getEmail());
    
    }
}
