
package tp7_herenciaypolimorfismo;


public class Auto extends Vehiculo {
      private final int cantidadPuertas;

    public Auto(String marca, String modelo, int cantidadPuertas) {
        super(marca, modelo); // llama al constructor de la clase base
        this.cantidadPuertas = cantidadPuertas;
    }

    @Override
    public void mostrarInfo() {
        // Usa el método de la clase base y agrega más información
        super.mostrarInfo();
        System.out.println("Cantidad de puertas: " + cantidadPuertas);
    }  
}
