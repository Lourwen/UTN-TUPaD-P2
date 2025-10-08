
package relacionesuml;


public class Eje1 {
/*1.Pasaporte - Foto - Titular
a.Composición: Pasaporte → Foto
b.Asociación bidireccional: Pasaporte ↔ Titular
 */
   
    public static void main(String[] args) {
       Titular titular = new Titular("Lourdes Paco", "33453101");
        Foto foto = new Foto("juan.jpg", "JPEG");
        Pasaporte pasaporte = new Pasaporte("ABC1234", "2'10'2025", foto);

        // Vinculamos las referencias
        pasaporte.setTitular(titular);
        titular.setPasaporte(pasaporte);

        System.out.println("Titular del pasaporte: " + pasaporte.getTitular().getNombre());
        System.out.println("Pasaporte del titular: " + titular.getPasaporte().getNumero());
    }
    
    
}
