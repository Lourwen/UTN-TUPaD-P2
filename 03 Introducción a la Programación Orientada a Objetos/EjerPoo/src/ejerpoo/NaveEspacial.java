
package ejerpoo;

public class NaveEspacial {
    String nombre;
    int combustible;
    int limiteCombus = 80;
    
    public void despegar(){
    if(combustible >=5){
    combustible= combustible -5;
        System.out.println("Despegamos!. Le quedan de combustible :"+combustible);
    }else{
        System.out.println("Su combustible es insuficiente");
    }
}    
    public void avanzar(int distancia){
    int uso = distancia * 5;
        if(combustible >=uso){
           combustible= combustible - uso;
        System.out.println("Avanzamos "+distancia+",combustible restante: "+combustible);
    }else {System.out.println("Combustible insuficiente para avanzar");
        }
}
        public void recargarCombustible(int cantidad){
    if(combustible + cantidad <=limiteCombus){
       combustible=combustible +cantidad;
       System.out.println("Se recargo "+cantidad+" combustible actual: "+combustible);      
    }else {
        combustible = limiteCombus;
        System.out.println("Se recargo al limite, "+combustible);
        }          
    }    
    public void mostrarEstado(){
        System.out.println("La nave "+nombre+" posee actualmente "+combustible+" de combustible");
    }
    
     public static void main(String[] args) {
        NaveEspacial nave = new NaveEspacial();
        nave.nombre = "ProgII";
        nave.combustible = 50;

        // Intentar avanzar sin recargar
        nave.avanzar(15); // tiene que llegar al 50

        // Recargar combustible
        nave.recargarCombustible(40); // debería llegar a 80

        // Despegar + avanzar 
        nave.despegar();
        nave.avanzar(10);

        // Mostrar estado fin
        nave.mostrarEstado();
    }
        
}
