
package tp7_herenciaypolimorfismo.kata4;
public class Animal {
      private String nombre;

    public Animal(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Método que será sobrescrito
    public void hacerSonido() {
        System.out.println("El animal hace un sonido genérico.");
    }

    public void describirAnimal() {
        System.out.println("Soy un animal llamado " + nombre);
    }
}
