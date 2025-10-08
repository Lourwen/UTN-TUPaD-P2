
package relacionesuml;

public class Reproductor {
    public void reproducir(Cancion cancion){
        System.out.println("Playing "+ cancion.getTitulo());
        System.out.println("Artista " + cancion.getArtista().getNombre());
        System.out.println("Genero "+ cancion.getArtista().getGenero());
    
        
        
        
    }
}
