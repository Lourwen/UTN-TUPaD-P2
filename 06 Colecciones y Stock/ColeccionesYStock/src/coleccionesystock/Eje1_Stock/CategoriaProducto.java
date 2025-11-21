package coleccionesystock.Eje1_Stock;



public enum CategoriaProducto {
    ARBOLES("Arboles frutales y ornamentales"),
    SUSTRATO("Organicos e inorganicos"),
    SEMILLAS("Hibridas, criollas, trangenicas"),
    MACETAS("Plastico, Barro"),
    FERTILIZANTES("Inorganico u organico"),
    GENERICO("Sin categoria definida");  // mantiene descripción

    private String descripcion;

    // Constructor principal
    CategoriaProducto(String descripcion) {
        this.descripcion = descripcion;
    }

    // Constructor sobrecargado opcional
    CategoriaProducto() {
        this.descripcion = "Sin categoría definida";
    }

    public String getDescripcion() {
        return descripcion;
    }
}



