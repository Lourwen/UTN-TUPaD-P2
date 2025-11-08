
package interfacesyexcepciones;

public class InterfacesYExcepciones {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("Lourdes Paco", "lpaco@agro.uba.ar");

        Producto p1 = new Producto("Paskone Mouse", 1000500);
        Producto p2 = new Producto("Audiculares", 5000);

        Pedido pedido = new Pedido(cliente);
        pedido.agregarProducto(p1);
        pedido.agregarProducto(p2);

        System.out.println(pedido);

        // Cambiamos el estado del pedido
        pedido.setEstado("Enviado");
        pedido.setEstado("Entregado");
    }
}


