
package relacionesuml;


public class PlacaMadre {
private String modelo;
private String chipSet;

    public PlacaMadre(String modelo, String chipSet) {
        this.modelo = modelo;
        this.chipSet = chipSet;
    }

    public String getModelo() {
        return modelo;
    }

    public String getChipSet() {
        return chipSet;
    }

}
