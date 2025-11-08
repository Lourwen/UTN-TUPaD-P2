
package tp7_herenciaypolimorfismo.kata4;
public class Gato extends Animal {
     public Gato(String nombre) {
        super(nombre);
    }

    @Override
    public void hacerSonido() {
        System.out.println("Nyan nyan");
    }   
}
