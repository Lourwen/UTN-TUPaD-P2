
package relacionesuml;


public class Eje11 {
    /*11.Reproductor - Canción - Artista
a.Asociación unidireccional: Canción → Artista
b.Dependencia de uso: Reproductor.reproducir(Cancion)
     */
     public static void main(String[] args) {

        Artista artista = new Artista("Aki", "Jpop");
        Cancion cancion = new Cancion("Kanashimi ga tomaranai", artista);

        Reproductor reproductor = new Reproductor();
        reproductor.reproducir(cancion);
    }
    
    
    
    
    
    
    
}
