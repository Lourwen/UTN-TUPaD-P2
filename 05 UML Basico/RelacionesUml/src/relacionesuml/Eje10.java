
package relacionesuml;


public class Eje10 {
  /*10.	CuentaBancaria - ClaveSeguridad - Titular
a.Composición: CuentaBancaria → ClaveSeguridad
b.Asociación bidireccional: CuentaBancaria ↔ Titular
     */
    public static void main(String[] args) {
        
        Titular titular = new Titular("Lourdes Paco", "33453101");
        CuentaBancaria cuenta = new CuentaBancaria("1234567890123456789012", 150000.75);
        

        // vincular relación bidireccional
        cuenta.setTitular(titular);
        titular.setCuentaBancaria(cuenta);

        // mostrar datos
        System.out.println("Titular: " + titular.getNombre());
        System.out.println("DNI: " + titular.getDni());
        System.out.println("Cuenta CBU: " + titular.getCuentaBancaria().getCbu());
        System.out.println("Saldo: $" + titular.getCuentaBancaria().getSaldo());
        System.out.println("Clave de seguridad: " + titular.getCuentaBancaria().getClaveSeguridad());
    }
}    
    
    
    

