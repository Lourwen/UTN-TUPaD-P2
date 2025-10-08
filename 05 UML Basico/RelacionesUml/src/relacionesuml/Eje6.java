
package relacionesuml;


public class Eje6 {
      public static void main(String[] args) {
  /*6.Reserva - Cliente - Mesa
a.Asociación unidireccional: Reserva → Cliente
b.Agregación: Reserva → Mesa

          
          */
        Cliente cliente = new Cliente("Lourdes Paco","33453101", "1160395155");
        Mesa mesa = new Mesa(5, 4);
        Reserva reserva = new Reserva("2025-10-07", "20:00", cliente, mesa);

        System.out.println("Reserva para: " + reserva.getCliente().getNombre());
        System.out.println("Telefono: " + reserva.getCliente().getTelefono());
        System.out.println("Fecha: " + reserva.getFecha());
        System.out.println("Hora: " + reserva.getHora());
        System.out.println("Mesa: " + reserva.getMesa().getNumero() + " capacidad: " + reserva.getMesa().getCapacidad());
    }
}
