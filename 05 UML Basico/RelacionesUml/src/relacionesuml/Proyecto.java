
package relacionesuml;

public class Proyecto {
    private String nombre;
    private double duracionMin;

    public Proyecto(String nombre, double duracionMin) {
        this.nombre = nombre;
        this.duracionMin = duracionMin;
    }

    public String getNombre() {
        return nombre;
    }

    public double getDuracionMin() {
        return duracionMin;
    }
    
    
}
