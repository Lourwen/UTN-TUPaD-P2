package coleccionesystock.Eje1_Stock;

public class MainEje1 {

    public static void main(String[] args) {

        Inventario inventario = new Inventario();

        // 1. Crear al menos cinco productos y agregarlos al inventario
        Producto p1 = new Producto("A1", "Prunus ceracifera", CategoriaProducto.ARBOLES, 15500, 15);
        Producto p2 = new Producto("B2", "Tierra negra con perlita", CategoriaProducto.SUSTRATO, 3000, 8);
        Producto p3 = new Producto("C3", "Jacaranda-organica", CategoriaProducto.SEMILLAS, 85000, 5);
        Producto p4 = new Producto("D4", "15 l", CategoriaProducto.MACETAS, 900, 50);
        Producto p5 = new Producto("E5", "F.Organicos", CategoriaProducto.FERTILIZANTES, 1800, 20);

        inventario.agregarProducto(p1);
        inventario.agregarProducto(p2);
        inventario.agregarProducto(p3);
        inventario.agregarProducto(p4);
        inventario.agregarProducto(p5);

        System.out.println("1. Productos agregados correctamente.\n");

        // 2. Listar todos los productos
        System.out.println("2. Listado de productos:");
        inventario.listarProductos();
        System.out.println("---------------------------------------------");

        // 3. Buscar producto por ID
        System.out.println("3. Buscando producto con ID 'C3':");
        Producto buscado = inventario.buscarProductoPorId("C3");

        if (buscado != null) {
            buscado.mostrarInfo();
        } else {
            System.out.println("Producto no encontrado.");
        }
        System.out.println("---------------------------------------------");

        // 4. Filtrar por categoría
        System.out.println("4. Filtrando productos por categoria ARBOLES:");
        for (Producto p : inventario.filtrarPorCategoria(CategoriaProducto.ARBOLES)) {
            p.mostrarInfo();
            System.out.println();
        }
        System.out.println("----------------------------------------");

        // 5. Eliminar producto por su ID
        System.out.println("5. Eliminando producto con ID 'D4' (15 l)");
        boolean eliminado = inventario.eliminarProducto("D4");

        System.out.println(eliminado ? "Producto eliminado correctamente." : "No se encontro el producto.");
        System.out.println("Productos restantes:");
        inventario.listarProductos();
        System.out.println("------------------------------------");

        // 6. Actualizar stock
        System.out.println("6. Actualizando stock del producto 'A1' a 30 unidades.");
        boolean actualizado = inventario.actualizarStock("A1", 30);
        System.out.println(actualizado ? "Stock actualizado." : "Producto no encontrado.");
        inventario.buscarProductoPorId("A1").mostrarInfo();
        System.out.println("---------------------------------------------");

        // 7. Mostrar total de stock
        System.out.println("7. Total de stock disponible: " + inventario.obtenerTotalStock());
        System.out.println("---------------------------------------------");

        // 8. Producto con mayor stock
        System.out.println("8. Producto con mayor stock:");
        Producto mayor = inventario.obtenerProductoConMayorStock();
        if (mayor != null) mayor.mostrarInfo();
        System.out.println("---------------------------------------------");

        // 9. Filtrar productos por precio
        System.out.println("9. Productos entre $1000 y $3000:");
        for (Producto p : inventario.filtrarProductosPorPrecio(1000, 3000)) {
            p.mostrarInfo();
            System.out.println();
        }
        System.out.println("---------------------------------------------");

        // 10. Mostrar categorías disponibles
        System.out.println("10. Categorias disponibles:");
        inventario.mostrarCategoriasDisponibles();
    }
    
    
    
    
    
    
}

