
package relacionesuml;


public class Computadora {
   private String marca;
   private String numeroSerie;
   private PlacaMadre placaMadre;//composicion
   private Propietario propietario;//bidireccion

    public Computadora(String marca, String numeroSerie, String modelo,String chipSet) {
        this.marca = marca;
        this.numeroSerie = numeroSerie;
        
        this.placaMadre= new PlacaMadre(modelo,chipSet);
    }

    public String getMarca() {
        return marca;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public PlacaMadre getPlacaMadre() {
        return placaMadre;
    }

    public Propietario getPropietario() {
        return propietario;
    }

   

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public void setPlacaMadre(PlacaMadre placaMadre) {
        this.placaMadre = placaMadre;
    }
     
   
}
