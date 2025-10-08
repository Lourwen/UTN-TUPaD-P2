
package relacionesuml;

public class Cliente {
 private String nombre;
 private String dni;
 private TarjetaDeCredito tarjetaDeCredito;//bireccional
 private String telefono;//para el ejercicio 6

    public Cliente(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
      }

     // Constructor para el ejercicio 6 (con teléfono)
    public Cliente(String nombre, String dni, String telefono) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
    }
    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public TarjetaDeCredito getTarjetaDeCredito() {
        return tarjetaDeCredito;
    }

   public void setTarjetaDeCredito(TarjetaDeCredito tarjetaDeCredito) {
        this.tarjetaDeCredito = tarjetaDeCredito;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
 
 
}
