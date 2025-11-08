
package tp7_herenciaypolimorfismo;


public class Vehiculo {
      protected String marca;
    protected String modelo;
//constructor
    public Vehiculo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public void mostrarInfo() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
    }
}
