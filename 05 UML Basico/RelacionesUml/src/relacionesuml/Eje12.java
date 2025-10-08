
package relacionesuml;


public class Eje12 {
    /*11.Reproductor - Canción - Artista
a.Asociación unidireccional: Canción → Artista
b.Dependencia de uso: Reproductor.reproducir(Cancion)
     */
    public static void main(String[] args) {

        Contribuyente contribuyente = new Contribuyente("Lourdes Paco", "27-33453101-0");
        Impuesto impuesto = new Impuesto(15000.0,contribuyente);

        Calculadora calculadora = new Calculadora();
        calculadora.calcular(impuesto);
    }    
}
