
package interfacesyexcepciones;

public class Paypal implements Pago{
    private String email;

    public Paypal(String email) {
        this.email = email;
    }

    @Override
    public void procesarPago(double monto) {
        System.out.println("Pago realizado por PayPal (" + email + ") por $" + monto);
    }   
}
