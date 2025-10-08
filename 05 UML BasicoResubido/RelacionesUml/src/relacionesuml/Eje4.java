
package relacionesuml;


public class Eje4 {
public static void main(String[] args) {
        /*4.TarjetaDeCrédito - Cliente - Banco
a.Asociación bidireccional: TarjetaDeCrédito ↔ Cliente
b.Agregación: TarjetaDeCrédito → Banco

    
    */
        Banco banco = new Banco("Banco Patagonia", "27-10145333-0");

        
        Cliente cliente = new Cliente("Lourdes Paco", "33453101");

        
        TarjetaDeCredito tarjetaDeCredito = new TarjetaDeCredito("1234-5678-9123-4567", "12/30", banco);
        tarjetaDeCredito.setCliente(cliente);
        cliente.setTarjetaDeCredito(tarjetaDeCredito);

        
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("DNI: " + cliente.getDni());
        System.out.println("Tarjeta: " + cliente.getTarjetaDeCredito().getNumero());
        System.out.println("Banco: " + cliente.getTarjetaDeCredito().getBanco().getNombre());
        System.out.println("Vencimiento: " + cliente.getTarjetaDeCredito().getFechaVencimiento());
    }    
}
