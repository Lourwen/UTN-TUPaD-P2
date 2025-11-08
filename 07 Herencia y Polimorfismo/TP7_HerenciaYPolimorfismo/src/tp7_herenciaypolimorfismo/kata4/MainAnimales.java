
package tp7_herenciaypolimorfismo.kata4;
public class MainAnimales {
        public static void main(String[] args) {
        Animal[] animales = new Animal[3];

        animales[0] = new Perro("Kuma");
        animales[1] = new Gato("Suki");
        animales[2] = new Vaca("Nora");

        for (Animal a : animales) {
            a.describirAnimal();
            a.hacerSonido();  // 
            System.out.println("////////////////");
        }
    }
}
