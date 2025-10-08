
package relacionesuml;


public class Documento {
    private String titulo;
    private String contenido;
    private FirmaDigital firmaDigital;//composicion

    public Documento(String titulo, String contenido,String codigoHash,String fecha, Usuario usuario) {
        this.titulo = titulo;
        this.contenido = contenido;
        //creacion de fiorma digital por el documento
        this.firmaDigital =new FirmaDigital (codigoHash, fecha, usuario);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public FirmaDigital getFirmaDigital() {
        return firmaDigital;
    }
   
    }
