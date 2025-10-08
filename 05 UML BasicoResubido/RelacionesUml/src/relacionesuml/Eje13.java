
package relacionesuml;


public class Eje13 {
    /*13.GeneradorQR - Usuario - CódigoQR
a.Asociación unidireccional: CódigoQR → Usuario
b.Dependencia de creación: GeneradorQR.generar(String, Usuario)    
    */
   public static void main(String[] args) {
        Usuario usuario = new Usuario("Lourdes Paco",null, "lourdespaco@gmail.com");// null para no crear otro constructor en Usuario

        GeneradorQR generador = new GeneradorQR();
        generador.generar("https://comuna15.gob.ar/planificacionextracciones2025", usuario);
    } 
    
}
