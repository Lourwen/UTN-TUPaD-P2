package tpi_programacion_2;

import entities.Mascota;
import entities.Microchip;
import service.MascotaService;
import service.MicrochipService;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Menú de aplicación con manejo robusto de errores y CRUD completo
 * Cumple con los requisitos del ejercicio 6) AppMenu (consola)
 */
public class AppMenu {
    
    private final Scanner scanner;
    private final MascotaService mascotaService;
    private final MicrochipService microchipService;
    
    public AppMenu() {
        this.scanner = new Scanner(System.in);
        this.mascotaService = new MascotaService();
        this.microchipService = new MicrochipService();
    }
    
    public void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GESTIÓN DE MASCOTAS ===");
        System.out.println("Seleccione una opción:");
        System.out.println();
        
        System.out.println("=== GESTIÓN DE MASCOTAS ===");
        System.out.println("1.  Crear mascota");
        System.out.println("2.  Leer mascota por ID");
        System.out.println("3.  Listar todas las mascotas");
        System.out.println("4.  Actualizar mascota");
        System.out.println("5.  Eliminar mascota (lógico)");
        System.out.println("6.  Buscar mascotas por nombre");
        System.out.println("7.  Buscar mascotas por especie");
        
        System.out.println("\n=== GESTIÓN DE MICROCHIPS ===");
        System.out.println("8.  Crear microchip");
        System.out.println("9.  Leer microchip por ID");
        System.out.println("10. Listar todos los microchips");
        System.out.println("11. Actualizar microchip");
        System.out.println("12. Eliminar microchip (lógico)");
        System.out.println("13. Buscar microchip por código");
        System.out.println("14. Buscar microchips por veterinaria");
        
        System.out.println("\n=== OPERACIONES ESPECIALES ===");
        System.out.println("15. Crear mascota CON microchip (transacción)");
        System.out.println("16. Estadísticas del sistema");
        
        System.out.println("\n0.  Salir");
        System.out.print("\nIngrese su opción: ");
    }
    
    public void ejecutar() {
        int opcion = -1;
        
        while (opcion != 0) {
            try {
                mostrarMenu();
                opcion = leerEntero();
                
                switch (opcion) {
                    case 1: crearMascota(); break;
                    case 2: leerMascotaPorId(); break;
                    case 3: listarTodasLasMascotas(); break;
                    case 4: actualizarMascota(); break;
                    case 5: eliminarMascota(); break;
                    case 6: buscarMascotasPorNombre(); break;
                    case 7: buscarMascotasPorEspecie(); break;
                    
                    case 8: crearMicrochip(); break;
                    case 9: leerMicrochipPorId(); break;
                    case 10: listarTodosLosMicrochips(); break;
                    case 11: actualizarMicrochip(); break;
                    case 12: eliminarMicrochip(); break;
                    case 13: buscarMicrochipPorCodigo(); break;
                    case 14: buscarMicrochipsPorVeterinaria(); break;
                    
                    case 15: crearMascotaConMicrochip(); break;
                    case 16: mostrarEstadisticas(); break;
                    
                    case 0: 
                        System.out.println("\n ¡Gracias por usar el sistema! Hasta luego.");
                        break;
                        
                    default:
                        System.out.println("\n ERROR: Opción no válida. Por favor ingrese un número entre 0 y 16.");
                }
                
            } catch (Exception e) {
                System.out.println("\n ERROR INESPERADO: " + e.getMessage());
                System.out.println("Presione Enter para continuar...");
                scanner.nextLine();
            }
        }
    }
    
    // ============================================================
    // CRUD DE MASCOTAS
    // ============================================================
    
    private void crearMascota() {
        System.out.println("\n=== CREAR NUEVA MASCOTA ===");
        
        try {
            Mascota mascota = new Mascota();
            
            System.out.print("Nombre: ");
            String nombre = leerTextoObligatorio();
            mascota.setNombre(convertirAMayusculasPrimeraLetra(nombre));
            
            System.out.print("Especie: ");
            String especie = leerTextoObligatorio();
            mascota.setEspecie(convertirAMayusculasPrimeraLetra(especie));
            
            System.out.print("Raza [opcional]: ");
            String raza = scanner.nextLine().trim();
            if (!raza.isEmpty()) {
                mascota.setRaza(convertirAMayusculasPrimeraLetra(raza));
            }
            
            System.out.print("Dueño: ");
            String duenio = leerTextoObligatorio();
            mascota.setDuenio(convertirAMayusculasPrimeraLetra(duenio));
            
            mascota.setFechaNacimiento(leerFecha("Fecha de nacimiento (AAAA-MM-DD) [opcional]: ", false));
            
            Mascota resultado = mascotaService.insertar(mascota);
            System.out.println("\n✅ ÉXITO: Mascota creada correctamente.");
            System.out.println("   ID generado: " + resultado.getId());
            System.out.println("   Nombre: " + resultado.getNombre());
            
        } catch (Exception e) {
            mostrarError("Error al crear mascota", e);
        }
    }
    
    private void leerMascotaPorId() {
        System.out.println("\n=== BUSCAR MASCOTA POR ID ===");
        
        try {
            System.out.print("Ingrese el ID de la mascota: ");
            long id = leerEnteroPositivo();
            
            Mascota mascota = mascotaService.getById(id);
            
            if (mascota == null) {
                System.out.println("\n❌ ID INEXISTENTE: No se encontró mascota con ID: " + id);
            } else {
                System.out.println("\n✅ MASCOTA ENCONTRADA:");
                mostrarMascotaDetalle(mascota);
            }
            
        } catch (Exception e) {
            mostrarError("Error al buscar mascota", e);
        }
    }
    
    private void listarTodasLasMascotas() {
        System.out.println("\n=== LISTADO DE TODAS LAS MASCOTAS ===");
        
        try {
            List<Mascota> mascotas = mascotaService.getAll();
            
            if (mascotas.isEmpty()) {
                System.out.println("\n📋 INFO: No hay mascotas registradas en el sistema.");
            } else {
                System.out.println("\n✅ Se encontraron " + mascotas.size() + " mascotas:");
                mostrarListaMascotas(mascotas);
            }
            
        } catch (Exception e) {
            mostrarError("Error al listar mascotas", e);
        }
    }
    
    private void actualizarMascota() {
        System.out.println("\n=== ACTUALIZAR MASCOTA ===");
        
        try {
            System.out.print("Ingrese el ID de la mascota a actualizar: ");
            long id = leerEnteroPositivo();
            
            Mascota mascota = mascotaService.getById(id);
            
            if (mascota == null) {
                System.out.println("\n❌ ID INEXISTENTE: No se encontró mascota con ID: " + id);
                return;
            }
            
            System.out.println("\nMascota actual:");
            mostrarMascotaDetalle(mascota);
            
            System.out.println("\nIngrese los nuevos datos (Enter para mantener el valor actual):");
            
            String nuevoNombre = leerTextoOpcional("Nombre [" + mascota.getNombre() + "]: ");
            if (!nuevoNombre.isEmpty()) {
                mascota.setNombre(convertirAMayusculasPrimeraLetra(nuevoNombre));
            }
            
            String nuevaEspecie = leerTextoOpcional("Especie [" + mascota.getEspecie() + "]: ");
            if (!nuevaEspecie.isEmpty()) {
                mascota.setEspecie(convertirAMayusculasPrimeraLetra(nuevaEspecie));
            }
            
            String nuevaRaza = leerTextoOpcional("Raza [" + (mascota.getRaza() != null ? mascota.getRaza() : "sin raza") + "]: ");
            if (!nuevaRaza.isEmpty()) {
                mascota.setRaza(convertirAMayusculasPrimeraLetra(nuevaRaza));
            }
            
            String nuevoDuenio = leerTextoOpcional("Dueño [" + mascota.getDuenio() + "]: ");
            if (!nuevoDuenio.isEmpty()) {
                mascota.setDuenio(convertirAMayusculasPrimeraLetra(nuevoDuenio));
            }
            
            LocalDate nuevaFecha = leerFecha("Fecha nacimiento [" + mascota.getFechaNacimiento() + "] (AAAA-MM-DD): ", true);
            if (nuevaFecha != null) {
                mascota.setFechaNacimiento(nuevaFecha);
            }
            
            Mascota resultado = mascotaService.actualizar(mascota);
            System.out.println("\n✅ ÉXITO: Mascota actualizada correctamente.");
            System.out.println("   ID: " + resultado.getId());
            
        } catch (Exception e) {
            mostrarError("Error al actualizar mascota", e);
        }
    }
    
    private void eliminarMascota() {
        System.out.println("\n=== ELIMINAR MASCOTA (LÓGICO) ===");
        
        try {
            System.out.print("Ingrese el ID de la mascota a eliminar: ");
            long id = leerEnteroPositivo();
            
            Mascota mascota = mascotaService.getById(id);
            
            if (mascota == null) {
                System.out.println("\n❌ ID INEXISTENTE: No se encontró mascota con ID: " + id);
                return;
            }
            
            System.out.println("\nMascota a eliminar:");
            mostrarMascotaDetalle(mascota);
            
            System.out.print("¿Está seguro que desea eliminar esta mascota? (S/N): ");
            String confirmacion = scanner.nextLine().trim().toUpperCase();
            
            if ("S".equals(confirmacion) || "SI".equals(confirmacion)) {
                boolean eliminada = mascotaService.eliminar(id);
                
                if (eliminada) {
                    System.out.println("\n✅ ÉXITO: Mascota eliminada correctamente (eliminación lógica).");
                } else {
                    System.out.println("\n❌ ERROR: No se pudo eliminar la mascota.");
                }
            } else {
                System.out.println("\n📋 INFO: Operación cancelada.");
            }
            
        } catch (Exception e) {
            mostrarError("Error al eliminar mascota", e);
        }
    }
    
    private void buscarMascotasPorNombre() {
        System.out.println("\n=== BUSCAR MASCOTAS POR NOMBRE ===");
        
        try {
            System.out.print("Ingrese el nombre (o parte del nombre) a buscar: ");
            String nombre = leerTextoObligatorio();
            
            List<Mascota> mascotas = mascotaService.buscarMascotasPorNombre(nombre);
            
            if (mascotas.isEmpty()) {
                System.out.println("\n📋 SIN RESULTADOS: No se encontraron mascotas con nombre que contenga: \"" + nombre + "\"");
            } else {
                System.out.println("\n✅ Se encontraron " + mascotas.size() + " mascotas:");
                mostrarListaMascotas(mascotas);
            }
            
        } catch (Exception e) {
            mostrarError("Error al buscar mascotas por nombre", e);
        }
    }
    
    private void buscarMascotasPorEspecie() {
        System.out.println("\n=== BUSCAR MASCOTAS POR ESPECIE ===");
        
        try {
            System.out.print("Ingrese la especie a buscar: ");
            String especie = leerTextoObligatorio();
            
            List<Mascota> mascotas = mascotaService.buscarMascotasPorEspecie(especie);
            
            if (mascotas.isEmpty()) {
                System.out.println("\n📋 SIN RESULTADOS: No se encontraron mascotas de la especie: \"" + especie + "\"");
            } else {
                System.out.println("\n✅ Se encontraron " + mascotas.size() + " mascotas:");
                mostrarListaMascotas(mascotas);
            }
            
        } catch (Exception e) {
            mostrarError("Error al buscar mascotas por especie", e);
        }
    }
    
    // ============================================================
    // CRUD DE MICROCHIPS
    // ============================================================
    
    private void crearMicrochip() {
        System.out.println("\n=== CREAR NUEVO MICROCHIP ===");
        
        try {
            Microchip microchip = new Microchip();
            
            System.out.print("Código del microchip: ");
            String codigo = leerTextoObligatorio();
            microchip.setCodigo(codigo.toUpperCase());
            
            microchip.setFechaImplantacion(leerFecha("Fecha de implantación (AAAA-MM-DD) [opcional]: ", false));
            
            System.out.print("Veterinaria [opcional]: ");
            String veterinaria = scanner.nextLine().trim();
            if (!veterinaria.isEmpty()) {
                microchip.setVeterinaria(convertirAMayusculasPrimeraLetra(veterinaria));
            }
            
            System.out.print("Observaciones [opcional]: ");
            String observaciones = scanner.nextLine().trim();
            if (!observaciones.isEmpty()) {
                microchip.setObservaciones(observaciones);
            }
            
            Microchip resultado = microchipService.insertar(microchip);
            System.out.println("\n✅ ÉXITO: Microchip creado correctamente.");
            System.out.println("   ID generado: " + resultado.getId());
            System.out.println("   Código: " + resultado.getCodigo());
            
        } catch (Exception e) {
            mostrarError("Error al crear microchip", e);
        }
    }
    
    private void leerMicrochipPorId() {
        System.out.println("\n=== BUSCAR MICROCHIP POR ID ===");
        
        try {
            System.out.print("Ingrese el ID del microchip: ");
            long id = leerEnteroPositivo();
            
            Microchip microchip = microchipService.getById(id);
            
            if (microchip == null) {
                System.out.println("\n❌ ID INEXISTENTE: No se encontró microchip con ID: " + id);
            } else {
                System.out.println("\n✅ MICROCHIP ENCONTRADO:");
                mostrarMicrochipDetalle(microchip);
            }
            
        } catch (Exception e) {
            mostrarError("Error al buscar microchip", e);
        }
    }
    
    private void listarTodosLosMicrochips() {
        System.out.println("\n=== LISTADO DE TODOS LOS MICROCHIPS ===");
        
        try {
            List<Microchip> microchips = microchipService.getAll();
            
            if (microchips.isEmpty()) {
                System.out.println("\n📋 INFO: No hay microchips registrados en el sistema.");
            } else {
                System.out.println("\n✅ Se encontraron " + microchips.size() + " microchips:");
                mostrarListaMicrochips(microchips);
            }
            
        } catch (Exception e) {
            mostrarError("Error al listar microchips", e);
        }
    }
    
    private void actualizarMicrochip() {
        System.out.println("\n=== ACTUALIZAR MICROCHIP ===");
        
        try {
            System.out.print("Ingrese el ID del microchip a actualizar: ");
            long id = leerEnteroPositivo();
            
            Microchip microchip = microchipService.getById(id);
            
            if (microchip == null) {
                System.out.println("\n❌ ID INEXISTENTE: No se encontró microchip con ID: " + id);
                return;
            }
            
            System.out.println("\nMicrochip actual:");
            mostrarMicrochipDetalle(microchip);
            
            System.out.println("\nIngrese los nuevos datos (Enter para mantener el valor actual):");
            
            String nuevoCodigo = leerTextoOpcional("Código [" + microchip.getCodigo() + "]: ");
            if (!nuevoCodigo.isEmpty()) {
                microchip.setCodigo(nuevoCodigo.toUpperCase());
            }
            
            LocalDate nuevaFecha = leerFecha("Fecha implantación [" + microchip.getFechaImplantacion() + "] (AAAA-MM-DD): ", true);
            if (nuevaFecha != null) {
                microchip.setFechaImplantacion(nuevaFecha);
            }
            
            String nuevaVeterinaria = leerTextoOpcional("Veterinaria [" + (microchip.getVeterinaria() != null ? microchip.getVeterinaria() : "sin veterinaria") + "]: ");
            if (!nuevaVeterinaria.isEmpty()) {
                microchip.setVeterinaria(convertirAMayusculasPrimeraLetra(nuevaVeterinaria));
            }
            
            String nuevasObservaciones = leerTextoOpcional("Observaciones [" + (microchip.getObservaciones() != null ? microchip.getObservaciones() : "sin observaciones") + "]: ");
            if (!nuevasObservaciones.isEmpty()) {
                microchip.setObservaciones(nuevasObservaciones);
            }
            
            Microchip resultado = microchipService.actualizar(microchip);
            System.out.println("\n✅ ÉXITO: Microchip actualizado correctamente.");
            System.out.println("   ID: " + resultado.getId());
            
        } catch (Exception e) {
            mostrarError("Error al actualizar microchip", e);
        }
    }
    
    private void eliminarMicrochip() {
        System.out.println("\n=== ELIMINAR MICROCHIP (LÓGICO) ===");
        
        try {
            System.out.print("Ingrese el ID del microchip a eliminar: ");
            long id = leerEnteroPositivo();
            
            Microchip microchip = microchipService.getById(id);
            
            if (microchip == null) {
                System.out.println("\n❌ ID INEXISTENTE: No se encontró microchip con ID: " + id);
                return;
            }
            
            System.out.println("\nMicrochip a eliminar:");
            mostrarMicrochipDetalle(microchip);
            
            System.out.print("¿Está seguro que desea eliminar este microchip? (S/N): ");
            String confirmacion = scanner.nextLine().trim().toUpperCase();
            
            if ("S".equals(confirmacion) || "SI".equals(confirmacion)) {
                boolean eliminado = microchipService.eliminar(id);
                
                if (eliminado) {
                    System.out.println("\n✅ ÉXITO: Microchip eliminado correctamente (eliminación lógica).");
                } else {
                    System.out.println("\n❌ ERROR: No se pudo eliminar el microchip.");
                }
            } else {
                System.out.println("\n📋 INFO: Operación cancelada.");
            }
            
        } catch (Exception e) {
            mostrarError("Error al eliminar microchip", e);
        }
    }
    
    private void buscarMicrochipPorCodigo() {
        System.out.println("\n=== BUSCAR MICROCHIP POR CÓDIGO ===");
        
        try {
            System.out.print("Ingrese el código del microchip a buscar: ");
            String codigo = leerTextoObligatorio();
            
            Microchip microchip = microchipService.buscarMicrochipPorCodigo(codigo);
            
            if (microchip == null) {
                System.out.println("\n📋 SIN RESULTADOS: No se encontró microchip con código: \"" + codigo + "\"");
            } else {
                System.out.println("\n✅ MICROCHIP ENCONTRADO:");
                mostrarMicrochipDetalle(microchip);
            }
            
        } catch (Exception e) {
            mostrarError("Error al buscar microchip por código", e);
        }
    }
    
    private void buscarMicrochipsPorVeterinaria() {
        System.out.println("\n=== BUSCAR MICROCHIPS POR VETERINARIA ===");
        
        try {
            System.out.print("Ingrese el nombre de la veterinaria: ");
            String veterinaria = leerTextoObligatorio();
            
            List<Microchip> microchips = microchipService.buscarMicrochipsPorVeterinaria(veterinaria);
            
            if (microchips.isEmpty()) {
                System.out.println("\n📋 SIN RESULTADOS: No se encontraron microchips de la veterinaria: \"" + veterinaria + "\"");
            } else {
                System.out.println("\n✅ Se encontraron " + microchips.size() + " microchips:");
                mostrarListaMicrochips(microchips);
            }
            
        } catch (Exception e) {
            mostrarError("Error al buscar microchips por veterinaria", e);
        }
    }
    
    // ============================================================
    // OPERACIONES ESPECIALES
    // ============================================================
    
    private void crearMascotaConMicrochip() {
        System.out.println("\n=== CREAR MASCOTA CON MICROCHIP (TRANSACCIÓN) ===");
        
        try {
            System.out.println("Primero ingrese los datos de la mascota:");
            
            Mascota mascota = new Mascota();
            
            System.out.print("Nombre: ");
            String nombre = leerTextoObligatorio();
            mascota.setNombre(convertirAMayusculasPrimeraLetra(nombre));
            
            System.out.print("Especie: ");
            String especie = leerTextoObligatorio();
            mascota.setEspecie(convertirAMayusculasPrimeraLetra(especie));
            
            System.out.print("Raza [opcional]: ");
            String raza = scanner.nextLine().trim();
            if (!raza.isEmpty()) {
                mascota.setRaza(convertirAMayusculasPrimeraLetra(raza));
            }
            
            System.out.print("Dueño: ");
            String duenio = leerTextoObligatorio();
            mascota.setDuenio(convertirAMayusculasPrimeraLetra(duenio));
            
            mascota.setFechaNacimiento(leerFecha("Fecha de nacimiento (AAAA-MM-DD) [opcional]: ", false));
            
            System.out.println("\nAhora ingrese los datos del microchip:");
            
            Microchip microchip = new Microchip();
            
            System.out.print("Código del microchip: ");
            String codigo = leerTextoObligatorio();
            microchip.setCodigo(codigo.toUpperCase());
            
            microchip.setFechaImplantacion(leerFecha("Fecha de implantación (AAAA-MM-DD) [opcional]: ", false));
            
            System.out.print("Veterinaria [opcional]: ");
            String veterinaria = scanner.nextLine().trim();
            if (!veterinaria.isEmpty()) {
                microchip.setVeterinaria(convertirAMayusculasPrimeraLetra(veterinaria));
            }
            
            System.out.print("Observaciones [opcional]: ");
            String observaciones = scanner.nextLine().trim();
            if (!observaciones.isEmpty()) {
                microchip.setObservaciones(observaciones);
            }
            
            System.out.println("\nEjecutando transacción...");
            
            Mascota resultado = mascotaService.crearMascotaConMicrochip(mascota, microchip);
            
            System.out.println("\n✅ ÉXITO: Mascota y microchip creados en transacción.");
            System.out.println("   ID de mascota: " + resultado.getId());
            System.out.println("   Nombre: " + resultado.getNombre());
            System.out.println("   Microchip código: " + resultado.getMicrochip().getCodigo());
            System.out.println("   Microchip ID: " + resultado.getMicrochip().getId());
            
        } catch (Exception e) {
            mostrarError("Error en operación compuesta", e);
        }
    }
    
    private void mostrarEstadisticas() {
        System.out.println("\n=== ESTADÍSTICAS DEL SISTEMA ===");
        
        try {
            int totalMascotas = mascotaService.contarMascotas();
            int totalMicrochips = microchipService.contarMicrochips();
            
            System.out.println("📊 Total de mascotas registradas: " + totalMascotas);
            System.out.println("📊 Total de microchips registrados: " + totalMicrochips);
            
            if (totalMascotas > 0 || totalMicrochips > 0) {
                System.out.println("\n✅ Sistema operativo con datos.");
            } else {
                System.out.println("\n📋 Sistema vacío - no hay datos registrados.");
            }
            
        } catch (Exception e) {
            mostrarError("Error al obtener estadísticas", e);
        }
    }
    
    // ============================================================
    // MÉTODOS AUXILIARES PARA ENTRADA ROBUSTA
    // ============================================================
    
    private int leerEntero() {
        while (true) {
            try {
                int valor = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer
                return valor;
            } catch (InputMismatchException e) {
                System.out.print("❌ ENTRADA INVÁLIDA: Debe ingresar un número entero. Intente nuevamente: ");
                scanner.nextLine(); // limpiar entrada inválida
            }
        }
    }
    
    private long leerEnteroPositivo() {
        while (true) {
            try {
                long valor = scanner.nextLong();
                scanner.nextLine(); // limpiar buffer
                
                if (valor <= 0) {
                    System.out.print("❌ ENTRADA INVÁLIDA: Debe ingresar un número positivo. Intente nuevamente: ");
                    continue;
                }
                
                return valor;
            } catch (InputMismatchException e) {
                System.out.print("❌ ENTRADA INVÁLIDA: Debe ingresar un número entero positivo. Intente nuevamente: ");
                scanner.nextLine(); // limpiar entrada inválida
            }
        }
    }
    
    private String leerTextoObligatorio() {
        while (true) {
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.print("❌ CAMPO OBLIGATORIO: No puede estar vacío. Intente nuevamente: ");
        }
    }
    
    private String leerTextoOpcional(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    private LocalDate leerFecha(String prompt, boolean permitirVacio) {
        while (true) {
            try {
                System.out.print(prompt);
                String fechaStr = scanner.nextLine().trim();
                
                if (fechaStr.isEmpty()) {
                    if (permitirVacio) {
                        return null; // No cambiar fecha actual
                    } else {
                        return null; // Fecha opcional
                    }
                }
                
                return LocalDate.parse(fechaStr);
                
            } catch (DateTimeParseException e) {
                System.out.println("❌ FORMATO INVÁLIDO: Debe usar el formato AAAA-MM-DD (ej: 2023-12-25)");
            }
        }
    }
    
    private String convertirAMayusculasPrimeraLetra(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }
    
    // ============================================================
    // MÉTODOS PARA MOSTRAR INFORMACIÓN
    // ============================================================
    
    private void mostrarListaMascotas(List<Mascota> mascotas) {
        int contador = 1;
        for (Mascota mascota : mascotas) {
            System.out.println("\n--- MASCOTA #" + contador + " ---");
            mostrarMascotaDetalle(mascota);
            contador++;
        }
    }
    
    private void mostrarMascotaDetalle(Mascota mascota) {
        System.out.println("ID: " + mascota.getId());
        System.out.println("Nombre: " + mascota.getNombre());
        System.out.println("Especie: " + mascota.getEspecie());
        System.out.println("Raza: " + (mascota.getRaza() != null ? mascota.getRaza() : "No especificada"));
        System.out.println("Fecha Nacimiento: " + (mascota.getFechaNacimiento() != null ? mascota.getFechaNacimiento() : "No especificada"));
        System.out.println("Dueño: " + mascota.getDuenio());

        Microchip microchip = mascota.getMicrochip();
        if (microchip != null) {
            System.out.println("🔸 MICROCHIP ASOCIADO:");
            System.out.println("  - ID: " + microchip.getId());
            System.out.println("  - Código: " + microchip.getCodigo());
            System.out.println("  - Fecha Implantación: " + (microchip.getFechaImplantacion() != null ? microchip.getFechaImplantacion() : "No especificada"));
            System.out.println("  - Veterinaria: " + (microchip.getVeterinaria() != null ? microchip.getVeterinaria() : "No especificada"));
            System.out.println("  - Observaciones: " + (microchip.getObservaciones() != null ? microchip.getObservaciones() : "Sin observaciones"));
        } else {
            System.out.println("🔸 Sin microchip asignado");
        }
    }
    
    private void mostrarListaMicrochips(List<Microchip> microchips) {
        int contador = 1;
        for (Microchip microchip : microchips) {
            System.out.println("\n--- MICROCHIP #" + contador + " ---");
            mostrarMicrochipDetalle(microchip);
            contador++;
        }
    }
    
    private void mostrarMicrochipDetalle(Microchip microchip) {
        System.out.println("ID: " + microchip.getId());
        System.out.println("Código: " + microchip.getCodigo());
        System.out.println("Fecha Implantación: " + (microchip.getFechaImplantacion() != null ? microchip.getFechaImplantacion() : "No especificada"));
        System.out.println("Veterinaria: " + (microchip.getVeterinaria() != null ? microchip.getVeterinaria() : "No especificada"));
        System.out.println("Observaciones: " + (microchip.getObservaciones() != null ? microchip.getObservaciones() : "Sin observaciones"));
    }
    
    private void mostrarError(String contexto, Exception e) {
        System.out.println("\n❌ " + contexto.toUpperCase());
        
        String mensaje = e.getMessage();
        if (mensaje != null) {
            // Identificar tipos específicos de errores para mensajes más claros
            if (mensaje.contains("Duplicate entry") || mensaje.contains("ya existe")) {
                System.out.println("💡 VIOLACIÓN DE UNICIDAD: " + mensaje);
            } else if (mensaje.contains("violación") || mensaje.contains("Violación")) {
                System.out.println("💡 VIOLACIÓN DE REGLAS DE NEGOCIO: " + mensaje);
            } else if (mensaje.contains("Connection") || mensaje.contains("database")) {
                System.out.println("💡 ERROR DE BASE DE DATOS: " + mensaje);
                System.out.println("   Verifique que la base de datos esté disponible y configurada correctamente.");
            } else {
                System.out.println("💡 DETALLE: " + mensaje);
            }
        } else {
            System.out.println("💡 Error interno del sistema.");
        }
        
        System.out.println("\n🔄 Presione Enter para continuar...");
        scanner.nextLine();
    }
}