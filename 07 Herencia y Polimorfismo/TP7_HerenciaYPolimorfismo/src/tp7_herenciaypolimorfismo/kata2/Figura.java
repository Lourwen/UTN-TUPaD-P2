
package tp7_herenciaypolimorfismo.kata2;

public abstract class Figura {
        protected String nombre;

    public Figura(String nombre) {
        this.nombre = nombre;
    }

    
    // Método abstracto
    public abstract double calcularArea();

    public void mostrarInfo() {
        System.out.println("Figura: " + nombre);
    }
}
