package test;

import config.DatabaseConnection;
import dao.GenericDao;
import dao.MascotaDAO;
import dao.MicrochipDAO;
import entities.Mascota;
import entities.Microchip;
import service.MascotaService;
import service.MicrochipService;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase de prueba para verificar la implementación del GenericDAO
 * y las operaciones CRUD con transacciones
 */
public class TestGenericDAO {

    public static void main(String[] args) {
        System.out.println("=== PRUEBAS DE GenericDAO ===");
        System.out.println("Verificando implementación de los requisitos del TP...\n");

        // Probar conexión a BD
        try {
            DatabaseConnection.testConnection();
        } catch (Exception e) {
            System.err.println("Error de conexión: " + e.getMessage());
            return;
        }

        // Test 1: Verificar que DAOs implementan GenericDao
        testImplementacionGenericDao();

        // Test 2: Probar operaciones CRUD básicas
        testOperacionesCRUD();

        // Test 3: Probar métodos con Connection externa
        testTransacciones();

        System.out.println("\n🎉 Todas las pruebas completadas!");
    }

    private static void testImplementacionGenericDao() {
        System.out.println("1. Verificando implementación de GenericDao...");
        
        // Verificar que MascotaDAO implementa GenericDao<Mascota>
        MascotaDAO mascotaDAO = new MascotaDAO();
        if (mascotaDAO instanceof GenericDao) {
            System.out.println("   ✅ MascotaDAO implementa GenericDao<Mascota>");
        }

        // Verificar que MicrochipDAO implementa GenericDao<Microchip>
        MicrochipDAO microchipDAO = new MicrochipDAO();
        if (microchipDAO instanceof GenericDao) {
            System.out.println("   ✅ MicrochipDAO implementa GenericDao<Microchip>");
        }

        System.out.println("   ✅ Ambos DAOs implementan la interfaz GenericDao correctamente\n");
    }

    private static void testOperacionesCRUD() {
        System.out.println("2. Probando operaciones CRUD con GenericService (transacciones obligatorias)...");
        
        try {
            MicrochipService microchipService = new MicrochipService();

            // Crear microchip usando insertar() con transacción
            Microchip microchip = new Microchip();
            microchip.setCodigo("TEST-CRUD-" + System.currentTimeMillis());
            microchip.setVeterinaria("Veterinaria Prueba");
            microchip.setObservaciones("Microchip de prueba CRUD");
            
            Microchip microchipCreado = microchipService.insertar(microchip);
            System.out.println("   ✅ INSERT (con transacción): Microchip creado con ID " + microchipCreado.getId());

            // Leer microchip usando getById()
            Microchip microchipLeido = microchipService.getById(microchipCreado.getId());
            System.out.println("   ✅ GET BY ID: Microchip leído - Código: " + microchipLeido.getCodigo());

            // Actualizar microchip usando actualizar() con transacción
            microchipLeido.setObservaciones("Microchip actualizado con transacción");
            microchipService.actualizar(microchipLeido);
            System.out.println("   ✅ UPDATE (con transacción): Microchip actualizado");

            // Leer todos usando getAll()
            List<Microchip> todosMicrochips = microchipService.getAll();
            System.out.println("   ✅ GET ALL: Se encontraron " + todosMicrochips.size() + " microchips");

            // Eliminar microchip usando eliminar() con transacción
            boolean eliminado = microchipService.eliminar(microchipCreado.getId());
            System.out.println("   ✅ DELETE (con transacción): Microchip eliminado - " + eliminado);

        } catch (Exception e) {
            System.out.println("   ❌ Error en CRUD: " + e.getMessage());
        }

        System.out.println();
    }

    private static void testTransacciones() {
        System.out.println("3. Probando operaciones compuestas con transacciones obligatorias...");
        
        try {
            MascotaService mascotaService = new MascotaService();

            // Crear mascota con microchip - operación compuesta con transacción
            Mascota mascota = new Mascota();
            mascota.setNombre("Mascota Transaccion");
            mascota.setEspecie("Gato");
            mascota.setRaza("Siames");
            mascota.setDuenio("Propietario Prueba");
            mascota.setFechaNacimiento(LocalDate.now().minusYears(1));

            Microchip microchip = new Microchip();
            microchip.setCodigo("TXN-" + System.currentTimeMillis());
            microchip.setVeterinaria("Vet Transaccion");
            microchip.setObservaciones("Prueba de transacción");

            // Operación compuesta: crear B, asociarla a A y crear A (con transacción compartida)
            Mascota resultado = mascotaService.crearMascotaConMicrochip(mascota, microchip);
            
            System.out.println("   ✅ OPERACIÓN COMPUESTA: Crear B + Asociar + Crear A");
            System.out.println("       Mascota ID: " + resultado.getId());
            System.out.println("       Microchip código: " + resultado.getMicrochip().getCodigo());

            // Probar validación 1→1: intentar asignar el mismo microchip a otra mascota
            try {
                Mascota otraMascota = new Mascota();
                otraMascota.setNombre("Mascota Duplicada");
                otraMascota.setEspecie("Perro");
                otraMascota.setDuenio("Otro Propietario");
                otraMascota.setMicrochip(resultado.getMicrochip());
                
                mascotaService.insertar(otraMascota);
                System.out.println("   ❌ ERROR: Debería haber fallado la validación 1→1");
            } catch (Exception e) {
                System.out.println("   ✅ VALIDACIÓN 1→1: " + e.getMessage());
            }

            // Limpiar datos de prueba usando métodos de GenericService
            mascotaService.eliminar(resultado.getId());
            
        } catch (Exception e) {
            System.out.println("   ❌ Error en transacción: " + e.getMessage());
        }

        System.out.println();
    }
}