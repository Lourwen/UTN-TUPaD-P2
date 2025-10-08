
package relacionesuml;


public class EditorVideo {
    public void exportar(String formato, Proyecto proyecto){
        Render render = new Render(formato, proyecto);
       
        System.out.println("Proyecto" +render.getProyecto().getNombre());
        System.out.println("Duracion (min): "+render.getProyecto().getDuracionMin());
        System.out.println("Formato al exportar: " +render.getFormato());
    
    }
}
