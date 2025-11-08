
package interfacesyexcepciones.Excepciones;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LecturaDeArchivo {
      public static void main(String[] args) {
        System.out.print("Ingrese el nombre del archivo: ");
        Scanner sc = new Scanner(System.in);
        String nombreArchivo = sc.nextLine();

        File archivo = new File(nombreArchivo);

        try {
            Scanner lector = new Scanner(archivo);
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                System.out.println(linea);
            }
            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: el archivo no existe");
        } finally {
            sc.close();
            System.out.println("Lectura del archivo termiando");
        }
    }
}
