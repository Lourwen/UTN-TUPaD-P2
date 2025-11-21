package coleccionesystock.Eje1_Stock;

public class Producto {
    // Atributos de instancia
    private String codigo;
    private String nombre;
    private CategoriaProducto categoria;
    private double precio;
    private int stock;
    // Atributo estático (contador de productos)
    private static int contadorProductos = 0;
    // Constructor completo (principal)
    public Producto(String codigo, String nombre, CategoriaProducto categoria, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;

        contadorProductos++;  // uso de atributo estático
    }

    // Constructor sobrecargado (sin stock)
    public Producto(String codigo, String nombre, CategoriaProducto categoria, double precio) {
        this(codigo, nombre, categoria, precio, 0);
    }

    // Constructor sobrecargado mínimo
    public Producto(String codigo, String nombre) {
        this(codigo, nombre, CategoriaProducto.GENERICO, 0, 0);
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public CategoriaProducto getCategoria() { return categoria; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    // Setters
    public void setPrecio(double precio) { this.precio = precio; }
    public void setStock(int stock) { this.stock = stock; }

    // Métodos sobrecargados
    public void actualizarStock(int nuevoStock) {
        this.stock = nuevoStock;
    }

    public void actualizarStock(int cantidad, boolean sumar) {
        if (sumar) {
            this.stock += cantidad;
        } else {
            this.stock -= cantidad;
        }
    }

    // Mostrar info
    public void mostrarInfo() {
        System.out.println(this.toString());
    }

    // toString()
    @Override
    public String toString() {
        return "Producto {" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", categoria=" + categoria +
                ", precio=$" + precio +
                ", stock=" + stock +
                '}';
    }

    // Método estático
    public static int getContadorProductos() {
        return contadorProductos;
    }
}



