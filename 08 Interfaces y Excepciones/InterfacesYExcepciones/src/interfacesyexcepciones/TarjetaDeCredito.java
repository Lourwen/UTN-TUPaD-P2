
package interfacesyexcepciones;

public class TarjetaDeCredito implements PagoConDescuento{
     private String titular;
    private final String numeroTarjeta;

    public TarjetaDeCredito(String titular, String numeroTarjeta) {
        this.titular = titular;
        this.numeroTarjeta = numeroTarjeta;
    }

    @Override
    public double aplicarDescuento(double monto) {
        return monto * 0.9; // Descuento del 10%
    }

    @Override
    public void procesarPago(double monto) {
        double montoConDescuento = aplicarDescuento(monto);
        System.out.println("Pago con Tarjeta de Credito por $" + montoConDescuento + " realizado por " + titular);
    }
}
