
package relacionesuml;

public class Bateria {
    private String modelo;
    private double capacidad;//medida de amperio.hora(Ah)

    public Bateria(String modelo, double capacidad) {
        this.modelo = modelo;
        this.capacidad = capacidad;
    }
    public String getModelo() {
        return modelo;
    }

    public double getCapacidad() {
        return capacidad;
    }

    @Override
    public String toString() {
        return "modelo " + modelo + ", capacidad " + capacidad ;
    }
    
    
}
