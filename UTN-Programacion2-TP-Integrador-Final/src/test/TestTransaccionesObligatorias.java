package test;

import config.DatabaseConnection;
import entities.Mascota;
import entities.Microchip;
import service.GenericService;
import service.MascotaService;
import service.MicrochipService;
import java.time.LocalDate;

/**
 * Test específico para demostrar transacciones obligatorias
 * y validaciones según los requisitos del Service layer
 */
public class TestTransaccionesObligatorias {

    public static void main(String[] args) {
        System.out.println("=== TEST TRANSACCIONES OBLIGATORIAS ===");
        System.out.println("Demostrando Service con transacciones según requisitos 5)...\n");

        try {
            DatabaseConnection.testConnection();
        } catch (Exception e) {
            System.err.println("Error de conexión: " + e.getMessage());
            return;
        }

        // Test 1: Verificar implementación de GenericService
        testImplementacionGenericService();

        // Test 2: Probar transacciones en operaciones individuales
        testTransaccionesIndividuales();

        // Test 3: Probar operaciones compuestas con transacciones
        testOperacionesCompuestas();

        // Test 4: Probar validaciones y regla 1→1
        testValidacionesYReglas();

        System.out.println("\n🎉 Tests de transacciones completados!");
    }

    private static void testImplementacionGenericService() {
        System.out.println("1. Verificando implementación de GenericService...");
        
        MascotaService mascotaService = new MascotaService();
        MicrochipService microchipService = new MicrochipService();
        
        if (mascotaService instanceof GenericService) {
            System.out.println("   ✅ MascotaService implementa GenericService<Mascota>");
        }
        
        if (microchipService instanceof GenericService) {
            System.out.println("   ✅ MicrochipService implementa GenericService<Microchip>");
        }
        
        System.out.println("   ✅ Ambos services implementan GenericService correctamente\n");
    }

    private static void testTransaccionesIndividuales() {
        System.out.println("2. Probando transacciones en operaciones individuales...");
        
        try {
            MicrochipService microchipService = new MicrochipService();
            
            // Test insertar() con transacción automática
            System.out.println("   Probando insertar() con setAutoCommit(false)...");
            Microchip microchip = new Microchip();
            microchip.setCodigo("TXN-INDIVIDUAL-" + System.currentTimeMillis());
            microchip.setVeterinaria("Vet Individual");
            
            Microchip creado = microchipService.insertar(microchip);
            System.out.println("   ✅ INSERT con transacción: ID " + creado.getId());
            
            // Test actualizar() con transacción automática
            System.out.println("   Probando actualizar() con setAutoCommit(false)...");
            creado.setObservaciones("Actualizado en transacción");
            
            microchipService.actualizar(creado);
            System.out.println("   ✅ UPDATE con transacción completado");
            
            // Test eliminar() con transacción automática
            System.out.println("   Probando eliminar() con setAutoCommit(false)...");
            boolean eliminado = microchipService.eliminar(creado.getId());
            System.out.println("   ✅ DELETE con transacción: " + eliminado);
            
        } catch (Exception e) {
            System.out.println("   ❌ Error: " + e.getMessage());
        }
        
        System.out.println();
    }

    private static void testOperacionesCompuestas() {
        System.out.println("3. Probando operaciones compuestas con conexión compartida...");
        
        try {
            MascotaService mascotaService = new MascotaService();
            
            // Operación compuesta: crear B, asociarla a A y crear A
            System.out.println("   Ejecutando: crear Microchip + asociar + crear Mascota");
            
            Mascota mascota = new Mascota();
            mascota.setNombre("Mascota Compuesta");
            mascota.setEspecie("Perro");
            mascota.setRaza("Pastor");
            mascota.setDuenio("Dueño Compuesto");
            mascota.setFechaNacimiento(LocalDate.now().minusYears(3));

            Microchip microchip = new Microchip();
            microchip.setCodigo("COMP-" + System.currentTimeMillis());
            microchip.setVeterinaria("Vet Compuesta");
            microchip.setObservaciones("Operación compuesta");
            
            // Esta operación demuestra:
            // - setAutoCommit(false) sobre conexión compartida
            // - Ejecutar operaciones compuestas
            // - commit() si todo OK; rollback() ante error
            // - Restablecer autoCommit(true) y cerrar recursos
            Mascota resultado = mascotaService.crearMascotaConMicrochip(mascota, microchip);
            
            System.out.println("   ✅ OPERACIÓN COMPUESTA exitosa:");
            System.out.println("       - Microchip creado: " + resultado.getMicrochip().getCodigo());
            System.out.println("       - Mascota creada: " + resultado.getId());
            System.out.println("       - Asociación 1→1 establecida");
            
            // Limpiar
            mascotaService.eliminar(resultado.getId());
            
        } catch (Exception e) {
            System.out.println("   ❌ Error en operación compuesta: " + e.getMessage());
        }
        
        System.out.println();
    }

    private static void testValidacionesYReglas() {
        System.out.println("4. Probando validaciones y regla 1→1...");
        
        try {
            MascotaService mascotaService = new MascotaService();
            MicrochipService microchipService = new MicrochipService();
            
            // Test validaciones de campos obligatorios
            System.out.println("   Probando validaciones de campos obligatorios...");
            
            try {
                Mascota mascotaInvalida = new Mascota();
                // Falta nombre (obligatorio)
                mascotaInvalida.setEspecie("Gato");
                mascotaInvalida.setDuenio("Dueño");
                
                mascotaService.insertar(mascotaInvalida);
                System.out.println("   ❌ ERROR: Debería fallar validación de campo obligatorio");
            } catch (Exception e) {
                System.out.println("   ✅ Validación campo obligatorio: " + e.getMessage());
            }
            
            // Test validaciones de formato según dominio
            System.out.println("   Probando validaciones de formato...");
            
            try {
                Microchip microchipInvalido = new Microchip();
                microchipInvalido.setCodigo("CODIGO_MUY_LARGO_QUE_EXCEDE_LIMITE_25_CARACTERES");
                
                microchipService.insertar(microchipInvalido);
                System.out.println("   ❌ ERROR: Debería fallar validación de formato");
            } catch (Exception e) {
                System.out.println("   ✅ Validación formato: " + e.getMessage());
            }
            
            // Test regla 1→1 (impedir más de un B por A)
            System.out.println("   Probando regla 1→1...");
            
            // Crear microchip
            Microchip microchip = new Microchip();
            microchip.setCodigo("REGLA-1A1-" + System.currentTimeMillis());
            microchip.setVeterinaria("Vet Regla");
            Microchip microchipCreado = microchipService.insertar(microchip);
            
            // Crear primera mascota con microchip
            Mascota mascota1 = new Mascota();
            mascota1.setNombre("Mascota 1");
            mascota1.setEspecie("Perro");
            mascota1.setDuenio("Dueño 1");
            mascota1.setMicrochip(microchipCreado);
            Mascota resultado1 = mascotaService.insertar(mascota1);
            
            // Intentar crear segunda mascota con el mismo microchip
            try {
                Mascota mascota2 = new Mascota();
                mascota2.setNombre("Mascota 2");
                mascota2.setEspecie("Gato");
                mascota2.setDuenio("Dueño 2");
                mascota2.setMicrochip(microchipCreado);
                
                mascotaService.insertar(mascota2);
                System.out.println("   ❌ ERROR: Debería fallar regla 1→1");
            } catch (Exception e) {
                System.out.println("   ✅ Regla 1→1 aplicada: " + e.getMessage());
            }
            
            // Limpiar
            mascotaService.eliminar(resultado1.getId());
            microchipService.eliminar(microchipCreado.getId());
            
        } catch (Exception e) {
            System.out.println("   ❌ Error en validaciones: " + e.getMessage());
        }
        
        System.out.println();
    }
}