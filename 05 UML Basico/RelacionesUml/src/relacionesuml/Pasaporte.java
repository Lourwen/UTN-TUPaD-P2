
package relacionesuml;


public class Pasaporte {
    private String numero;
    private String fechaEmicion;
    private Foto foto;
    private Titular titular;

    public Pasaporte(String numero, String fechaEmicion, Foto foto) {
        this.numero = numero;
        this.fechaEmicion = fechaEmicion;
        this.foto = foto;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaEmicion() {
        return fechaEmicion;
    }

    public void setFechaEmicion(String fechaEmicion) {
        this.fechaEmicion = fechaEmicion;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }
   
    
    
    
    
}
