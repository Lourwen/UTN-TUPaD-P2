
package relacionesuml;


public class Usuario {
    private String nombre;
    private String dni;
    private Celular celular;//bidireccional
    private String email;//para ejercicio7
//constructor ejercicio2
    public Usuario(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }
//constructor ejercicio 8    
    public Usuario(String nombre, String dni,String email) {
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
    }
    
    
     public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public Celular getCelular() {
        return celular;
    }

    public String getEmail() { 
        return email;
    }

    public void setCelular(Celular celular) {
        this.celular = celular;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
