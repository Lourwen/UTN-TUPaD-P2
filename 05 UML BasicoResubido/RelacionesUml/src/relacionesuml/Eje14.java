
package relacionesuml;


public class Eje14 {
    /*14.EditorVideo - Proyecto - Render
a.Asociación unidireccional: Render → Proyecto
b.Dependencia de creación: EditorVideo.exportar(String, Proyecto)
    */
     public static void main(String[] args) {
        Proyecto proyecto = new Proyecto("Censo Arbolado Urbano", 65.0);
        EditorVideo editor = new EditorVideo();

        editor.exportar("MP4", proyecto);
    }
    
    
}
