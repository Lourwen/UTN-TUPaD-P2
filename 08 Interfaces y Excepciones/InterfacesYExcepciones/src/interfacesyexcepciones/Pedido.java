
package interfacesyexcepciones;

import java.util.ArrayList;
import java.util.List;

public class Pedido implements Pagable {
    private List<Producto> productos;
    private String estado;
    private Cliente cliente; // cliente asociado

    public Pedido(Cliente cliente) {
        this.productos = new ArrayList<>();
        this.cliente = cliente;
        this.estado = "Creado Ok";
        notificarEstado();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void setEstado(String estado) {
        this.estado = estado;
        notificarEstado();
    }

    private void notificarEstado() {
        if (cliente != null) {
            cliente.notificar("El estado actual del pedido es  " + estado);
        }
    }

    @Override
    public double calcularTotal() {
        double total = 0;
        for (Producto p : productos) {
            total += p.calcularTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Pedido de " + productos.size() + " productos / Total: $" + calcularTotal();
    }
}

