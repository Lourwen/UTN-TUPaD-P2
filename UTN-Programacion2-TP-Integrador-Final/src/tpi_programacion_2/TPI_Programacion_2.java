package tpi_programacion_2;

import config.DatabaseConnection;

/**
 * Aplicación principal del TP Integrador - Ejercicio 6) AppMenu (consola)
 * Main invoca AppMenu según requisitos
 * 
 * @author Sistema
 */
public class TPI_Programacion_2 {

    public static void main(String[] args) {
        System.out.println("=== TP INTEGRADOR PROGRAMACION 2 ===");
        System.out.println("Sistema de Gestión de Mascotas y Microchips");
        System.out.println("Implementación completa con GenericDAO, GenericService y transacciones");
        System.out.println("==========================================\n");

        // Verificar conexión a base de datos
        try {
            System.out.println("🔄 Verificando conexión a base de datos...");
            DatabaseConnection.testConnection();
            System.out.println("✅ Conexión establecida correctamente.\n");
            
            // Main invoca AppMenu según requisito 6)
            AppMenu menu = new AppMenu();
            menu.ejecutar();
            
        } catch (Exception e) {
            System.err.println("❌ ERROR CRÍTICO: No se puede conectar a la base de datos.");
            System.err.println("💡 DETALLE: " + e.getMessage());
            System.err.println("\n🔧 SOLUCIONES:");
            System.err.println("   1. Verificar que MySQL esté ejecutándose");
            System.err.println("   2. Confirmar que existe la base de datos 'mascotasdb'");
            System.err.println("   3. Que se hayan ejecutado los scripts de creación: create_database.sql");
            System.err.println("   4. Verificar que las credenciales sean correctas en database.properties");
            System.err.println("   5. Verificar que el driver MySQL esté en el classpath");
            
        } finally {
            // Cerrar conexiones al finalizar
            try {
                DatabaseConnection.closeConnection();
                System.out.println("\n🔌 Conexiones de base de datos cerradas.");
            } catch (Exception e) {
                System.err.println("⚠️  Warning: Error al cerrar conexiones - " + e.getMessage());
            }
        }
    }

}

