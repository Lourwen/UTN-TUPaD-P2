
package relacionesuml;


public class CuentaBancaria {
    private String cbu;
    private double saldo;
    private ClaveSeguridad claveSeguridad;
    private Titular titular;//bidireccional
    
    
    

    public CuentaBancaria(String cbu, double saldo) {
        this.cbu = cbu;
        this.saldo = saldo;
        this.claveSeguridad = new ClaveSeguridad("4everIvi&","6/10/25 9:03:23hs");
    }

    public String getCbu() {
        return cbu;
    }

    public double getSaldo() {
        return saldo;
    }
    public ClaveSeguridad getClaveSeguridad() {
        return claveSeguridad;
    }
   
     public void setClaveSeguridad(ClaveSeguridad claveSeguridad) {
     this.claveSeguridad = claveSeguridad;
    }
 public Titular getTitular() {
        return titular;       
    }
    public void setTitular(Titular titular) {
        this.titular = titular;
    }    
}
