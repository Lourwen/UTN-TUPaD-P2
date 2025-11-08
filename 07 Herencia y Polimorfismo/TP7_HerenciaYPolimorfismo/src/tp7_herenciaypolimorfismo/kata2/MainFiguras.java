
package tp7_herenciaypolimorfismo.kata2;


public class MainFiguras {
    public static void main(String[] args) {
        Figura[] figuras = new Figura[4];//array de figuras
        figuras[0] = new Circulo(5);
        figuras[1] = new Rectangulo(4, 6);
        figuras[2] = new Circulo(2.5);
        figuras[3] = new Rectangulo(1,3);

        for (Figura f : figuras) {
            f.mostrarInfo();
            System.out.println("area= "+ f.calcularArea());
            System.out.println("/////////////");
        }
    }
}
