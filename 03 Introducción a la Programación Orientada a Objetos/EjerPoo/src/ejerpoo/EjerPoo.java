
package ejerpoo;

public class EjerPoo {
//Compilacion de invocamientos de las clases
    
    public static void main(String[] args) {
       //ESTUDIANTE
        Estudiante e =new Estudiante();
        e.nombre = "Lourdes";
        e.apellido = "Paco";
        e.curso = "Programacion 2";
        e.calificacion = 8.0;
        
        //invocar metodos de estudiante
        e.mostrarInfo();
        e.subirCalificacion(1.5);
        e.bajarCalificacion(1.5);
        e.mostrarInfo();
        //MASCOTA
        Mascota m = new Mascota ();
        m.nombre = "Arena";
        m.especie = "nn";
        m.edad = 12;
        //invocar metodos de mascota
        m.cumplirAnios();
        m.mostrarInfo();
        
        //LIBRO        
        Libro l = new Libro("La hija del curandero", "Amy tan", 2001);
        l.mostrarInfo();
        // Intento de anio invalido
        l.setAnioPublicacion(4000); // muestra mensaje de error
        // Intento de anio valido
        l.setAnioPublicacion(2000);
        l.mostrarInfo();
        
        //GALLINA
        Gallina g1 = new Gallina();
        g1.idGallina = "l1"; // gallina 1 de raza Leghorn
        g1.edad= 1;
        g1.huevosPuestos =2;

        Gallina g2 = new Gallina();
        g2.idGallina = "l2"; // gallina 2 de raza Leghorn
        g2.edad= 2;
        g2.huevosPuestos =3;        
        //simulacion
        g1.envejecer();
        g1.ponerHuevos();
        g1.mostrarEstado();

        g2.envejecer();
        g2.ponerHuevos();
        g2.mostrarEstado();
          
        //NAVE ESPACIAL
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
