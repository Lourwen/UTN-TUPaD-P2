
package relacionesuml;

public class Titular {
    private String nombre;
    private String dni;        
    private Pasaporte pasaporte;
    private CuentaBancaria cuentaBancaria;//ejercicio 10
    
    public Titular(String nombre,String dni){
    this.nombre=nombre;
    this.dni=dni;
       
    }
 public String getNombre() {
        return nombre;
    }

public String getDni() {
        return dni;
    }

public Pasaporte getPasaporte() {
        return pasaporte;
    }
public void setPasaporte(Pasaporte pasaporte) {
        this.pasaporte = pasaporte;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }


}
