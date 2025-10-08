
package relacionesuml;


public class Eje8 {
     public static void main(String[] args) {
 /*8.Documento - FirmaDigital - Usuario
   a.Composición: Documento → FirmaDigital
   b.Agregación: FirmaDigital → Usuario
    */
        Usuario usuario = new Usuario("Lourdes Paco Laura", "lpaco@agro.uba.ar");

        Documento documento = new Documento("Contratos","Texto del contenido","lo que sea un codigoHash","06/10/2025",usuario);
                

        System.out.println("Documento: " + documento.getTitulo());
        System.out.println("Contenido: " + documento.getContenido());
        System.out.println("Firma hash: " + documento.getFirmaDigital().getCodigoHash());
        System.out.println("Fecha firma: " + documento.getFirmaDigital().getFecha());
        System.out.println("Firmado por: " + documento.getFirmaDigital().getUsuario().getNombre());
    }
}
