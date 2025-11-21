package coleccionesystock.Eje1_Stock;

import java.util.ArrayList;

public class Inventario {

    private ArrayList<Producto> productos;
    public Inventario() {
        this.productos = new ArrayList<>();
    }

    // 1. Agregar producto
    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    // 2. Listar productos
    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
            return;
        }
        for (Producto p : productos) {
            p.mostrarInfo();
        }
    }

    // 3. Buscar producto por ID (código)
    public Producto buscarProductoPorId(String id) {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    // 4. Eliminar producto por ID
    public boolean eliminarProducto(String id) {
        Producto encontrado = buscarProductoPorId(id);
        if (encontrado != null) {
            productos.remove(encontrado);
            return true;
        }
        return false;
    }

    // 5. Actualizar stock
    public boolean actualizarStock(String id, int nuevaCantidad) {
        Producto p = buscarProductoPorId(id);

        if (p != null) {
            p.setStock(nuevaCantidad);
            return true;
        }
        return false;
    }

    // 6. Filtrar por categoría
    public ArrayList<Producto> filtrarPorCategoria(CategoriaProducto categoria) {
        ArrayList<Producto> filtrados = new ArrayList<>();

        for (Producto p : productos) {
            if (p.getCategoria() == categoria) {
                filtrados.add(p);
            }
        }

        return filtrados;
    }

    // 7. Obtener total de stock
    public int obtenerTotalStock() {
        int total = 0;

        for (Producto p : productos) {
            total += p.getStock();
        }

        return total;
    }

    // 8. Obtener producto con mayor stock
    public Producto obtenerProductoConMayorStock() {
        if (productos.isEmpty()) {
            return null;
        }

        Producto mayor = productos.get(0);

        for (Producto p : productos) {
            if (p.getStock() > mayor.getStock()) {
                mayor = p;
            }
        }
        return mayor;
        
    }

    // 9. Filtrar por precio
    public ArrayList<Producto> filtrarProductosPorPrecio(double min, double max) {
        ArrayList<Producto> filtrados = new ArrayList<>();

        for (Producto p : productos) {
            if (p.getPrecio() >= min && p.getPrecio() <= max) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }

    // 10. Mostrar categorías disponibles
    public void mostrarCategoriasDisponibles() {
        System.out.println("\nCategorias disponibles:");
        for (CategoriaProducto c : CategoriaProducto.values()) {
            System.out.println(" - " + c + ": " + c.getDescripcion());
        }
    }
      
    
}






