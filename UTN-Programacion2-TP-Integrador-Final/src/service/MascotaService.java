package service;

import config.DatabaseConnection;
import dao.MascotaDAO;
import dao.MicrochipDAO;
import entities.Mascota;
import entities.Microchip;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para la gestión de mascotas (Entidad A)
 * Implementa GenericService con transacciones obligatorias
 * Maneja validaciones de negocio y relación 1→1 con Microchip
 */
public class MascotaService implements GenericService<Mascota> {

    private final MascotaDAO mascotaDAO = new MascotaDAO();
    private final MicrochipDAO microchipDAO = new MicrochipDAO();

    // ==========================================================
    // IMPLEMENTACIÓN DE GenericService CON TRANSACCIONES
    // ==========================================================
    
    @Override
    public Mascota insertar(Mascota mascota) throws Exception {
        validarMascota(mascota);
        
        Connection conn = null;
        try {
            // Abrir transacción
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            
            // Validar relación 1→1: verificar que no tenga microchip duplicado
            if (mascota.getMicrochip() != null && mascota.getMicrochip().getId() != null) {
                validarRelacionUnoAUno(mascota, conn);
            }
            
            // Ejecutar inserción
            Mascota resultado = mascotaDAO.crear(mascota, conn);
            
            // Commit si todo OK
            conn.commit();
            return resultado;
            
        } catch (Exception e) {
            // Rollback ante cualquier error
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    e.addSuppressed(rollbackEx);
                }
            }
            throw new Exception("Error al insertar mascota: " + e.getMessage(), e);
        } finally {
            // Restablecer autoCommit(true) y cerrar recursos
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeEx) {
                    System.err.println("Error al cerrar conexión: " + closeEx.getMessage());
                }
            }
        }
    }
    
    @Override
    public Mascota actualizar(Mascota mascota) throws Exception {
        validarMascotaParaActualizacion(mascota);
        
        Connection conn = null;
        try {
            // Abrir transacción
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            
            // Validar relación 1→1
            if (mascota.getMicrochip() != null && mascota.getMicrochip().getId() != null) {
                validarRelacionUnoAUno(mascota, conn);
            }
            
            // Ejecutar actualización
            Mascota resultado = mascotaDAO.actualizar(mascota, conn);
            
            // Commit si todo OK
            conn.commit();
            return resultado;
            
        } catch (Exception e) {
            // Rollback ante cualquier error
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    e.addSuppressed(rollbackEx);
                }
            }
            throw new Exception("Error al actualizar mascota: " + e.getMessage(), e);
        } finally {
            // Restablecer autoCommit(true) y cerrar recursos
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeEx) {
                    System.err.println("Error al cerrar conexión: " + closeEx.getMessage());
                }
            }
        }
    }
    
    @Override
    public boolean eliminar(long id) throws Exception {
        Connection conn = null;
        try {
            // Abrir transacción
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            
            // Ejecutar eliminación
            boolean resultado = mascotaDAO.eliminar(id, conn);
            
            // Commit si todo OK
            conn.commit();
            return resultado;
            
        } catch (Exception e) {
            // Rollback ante cualquier error
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    e.addSuppressed(rollbackEx);
                }
            }
            throw new Exception("Error al eliminar mascota: " + e.getMessage(), e);
        } finally {
            // Restablecer autoCommit(true) y cerrar recursos
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeEx) {
                    System.err.println("Error al cerrar conexión: " + closeEx.getMessage());
                }
            }
        }
    }
    
    @Override
    public Mascota getById(long id) throws Exception {
        try {
            return mascotaDAO.leer(id);
        } catch (SQLException e) {
            throw new Exception("Error al obtener mascota: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Mascota> getAll() throws Exception {
        try {
            return mascotaDAO.leerTodos();
        } catch (SQLException e) {
            throw new Exception("Error al obtener mascotas: " + e.getMessage(), e);
        }
    }

    // ==========================================================
    // MÉTODOS DE COMPATIBILIDAD Y BÚSQUEDA
    // ==========================================================
    
    /**
     * Método de compatibilidad - redirige a insertar()
     */
    public Mascota crearMascota(Mascota mascota) throws Exception {
        return insertar(mascota);
    }
    
    /**
     * Método de compatibilidad - redirige a getById()
     */
    public Mascota obtenerMascota(long id) throws Exception {
        return getById(id);
    }
    
    /**
     * Método de compatibilidad - redirige a getAll()
     */
    public List<Mascota> obtenerTodasLasMascotas() throws Exception {
        return getAll();
    }
    
    /**
     * Método de compatibilidad - redirige a actualizar()
     */
    public Mascota actualizarMascota(Mascota mascota) throws Exception {
        return actualizar(mascota);
    }
    
    /**
     * Método de compatibilidad - redirige a eliminar()
     */
    public boolean eliminarMascota(long id) throws Exception {
        return eliminar(id);
    }
    
    /**
     * Método de compatibilidad para el nombre legacy
     */
    public Mascota registrarMascotaConMicrochip(Mascota mascota, Microchip microchip) throws Exception {
        return crearMascotaConMicrochip(mascota, microchip);
    }
    
    /**
     * Busca mascotas por nombre
     */
    public List<Mascota> buscarMascotasPorNombre(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre de búsqueda no puede estar vacío");
        }
        
        try {
            return mascotaDAO.buscarPorNombre(nombre.trim());
        } catch (SQLException e) {
            throw new Exception("Error al buscar mascotas por nombre: " + e.getMessage(), e);
        }
    }

    /**
     * Busca mascotas por especie
     */
    public List<Mascota> buscarMascotasPorEspecie(String especie) throws Exception {
        if (especie == null || especie.trim().isEmpty()) {
            throw new Exception("La especie de búsqueda no puede estar vacía");
        }
        
        try {
            return mascotaDAO.buscarPorEspecie(especie.trim());
        } catch (SQLException e) {
            throw new Exception("Error al buscar mascotas por especie: " + e.getMessage(), e);
        }
    }

    // ==========================================================
    // OPERACIONES COMPUESTAS CON TRANSACCIONES
    // ==========================================================
    
    /**
     * Operación compuesta: crear microchip, asociarlo a mascota y crear mascota
     * Utiliza transacción con conexión compartida según requisitos
     * 
     * @param mascota La mascota a registrar
     * @param microchip El microchip a crear y asociar
     * @return La mascota creada con el microchip asociado
     * @throws Exception si las validaciones fallan o ocurre un error
     */
    public Mascota crearMascotaConMicrochip(Mascota mascota, Microchip microchip) throws Exception {
        // Validaciones de negocio
        validarMascota(mascota);
        validarMicrochip(microchip);

        Connection conn = null;
        try {
            // Abrir transacción: setAutoCommit(false) sobre conexión compartida
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            
            // Verificar que el código de microchip no esté duplicado
            Microchip existente = microchipDAO.buscarPorCodigo(microchip.getCodigo(), conn);
            if (existente != null) {
                throw new Exception("Ya existe un microchip con el código: " + microchip.getCodigo());
            }
            
            // Ejecutar operaciones compuestas:
            // 1) Crear microchip (B)
            Microchip microchipCreado = microchipDAO.crear(microchip, conn);
            
            // 2) Asociarlo a mascota (A)
            mascota.setMicrochip(microchipCreado);
            
            // 3) Crear mascota (A)
            Mascota mascotaCreada = mascotaDAO.crear(mascota, conn);
            
            // commit() si todo OK
            conn.commit();
            return mascotaCreada;
            
        } catch (Exception e) {
            // rollback() ante cualquier error
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    e.addSuppressed(rollbackEx);
                }
            }
            throw new Exception("Error en operación compuesta: " + e.getMessage(), e);
        } finally {
            // Restablecer autoCommit(true) y cerrar recursos
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeEx) {
                    System.err.println("Error al cerrar conexión: " + closeEx.getMessage());
                }
            }
        }
    }

    /**
     * Operación compuesta: actualizar mascota y su microchip asociado
     * 
     * @param mascota La mascota a actualizar
     * @return La mascota actualizada
     * @throws Exception si las validaciones fallan o ocurre un error
     */
    public Mascota actualizarMascotaConMicrochip(Mascota mascota) throws Exception {
        validarMascotaParaActualizacion(mascota);
        
        Connection conn = null;
        try {
            // Abrir transacción
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            
            // Validar microchip si está presente
            if (mascota.getMicrochip() != null) {
                validarMicrochip(mascota.getMicrochip());
                validarRelacionUnoAUno(mascota, conn);
                
                // Actualizar microchip primero
                microchipDAO.actualizar(mascota.getMicrochip(), conn);
            }
            
            // Actualizar mascota
            Mascota resultado = mascotaDAO.actualizar(mascota, conn);
            
            // Commit si todo OK
            conn.commit();
            return resultado;
            
        } catch (Exception e) {
            // Rollback ante cualquier error
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    e.addSuppressed(rollbackEx);
                }
            }
            throw new Exception("Error al actualizar mascota con microchip: " + e.getMessage(), e);
        } finally {
            // Restablecer autoCommit(true) y cerrar recursos
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeEx) {
                    System.err.println("Error al cerrar conexión: " + closeEx.getMessage());
                }
            }
        }
    }

    // ==========================================================
    // MÉTODOS DE ESTADÍSTICAS
    // ==========================================================
    
    public int contarMascotas() throws Exception {
        try {
            return mascotaDAO.contarMascotas();
        } catch (SQLException e) {
            throw new Exception("Error al contar mascotas: " + e.getMessage(), e);
        }
    }

    // ==========================================================
    // VALIDACIONES (campos obligatorios, formatos según dominio)
    // ==========================================================
    
    private void validarMascota(Mascota mascota) throws Exception {
        if (mascota == null) {
            throw new Exception("La mascota no puede ser null");
        }

        // Campos obligatorios
        if (mascota.getNombre() == null || mascota.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre de la mascota es obligatorio");
        }
        
        if (mascota.getEspecie() == null || mascota.getEspecie().trim().isEmpty()) {
            throw new Exception("La especie es obligatoria");
        }
        
        if (mascota.getDuenio() == null || mascota.getDuenio().trim().isEmpty()) {
            throw new Exception("El dueño es obligatorio");
        }

        // Formatos según dominio
        if (mascota.getNombre().length() > 60) {
            throw new Exception("El nombre de la mascota no puede exceder 60 caracteres");
        }
        
        if (mascota.getEspecie().length() > 30) {
            throw new Exception("La especie no puede exceder 30 caracteres");
        }
        
        if (mascota.getDuenio().length() > 120) {
            throw new Exception("El nombre del dueño no puede exceder 120 caracteres");
        }

        if (mascota.getRaza() != null && mascota.getRaza().length() > 60) {
            throw new Exception("La raza no puede exceder 60 caracteres");
        }
    }

    private void validarMascotaParaActualizacion(Mascota mascota) throws Exception {
        validarMascota(mascota);
        
        if (mascota.getId() == null) {
            throw new Exception("El ID de la mascota es obligatorio para actualización");
        }
    }

    private void validarMicrochip(Microchip microchip) throws Exception {
        if (microchip == null) {
            throw new Exception("El microchip no puede ser null");
        }

        // Campos obligatorios
        if (microchip.getCodigo() == null || microchip.getCodigo().trim().isEmpty()) {
            throw new Exception("El código del microchip es obligatorio");
        }
        
        // Formatos según dominio
        if (microchip.getCodigo().length() > 25) {
            throw new Exception("El código del microchip no puede exceder 25 caracteres");
        }

        if (microchip.getVeterinaria() != null && microchip.getVeterinaria().length() > 120) {
            throw new Exception("El nombre de la veterinaria no puede exceder 120 caracteres");
        }

        if (microchip.getObservaciones() != null && microchip.getObservaciones().length() > 255) {
            throw new Exception("Las observaciones no pueden exceder 255 caracteres");
        }
    }
    
    /**
     * Validación de regla 1→1: impedir más de un B por A
     * Verifica que el microchip no esté ya asignado a otra mascota
     */
    private void validarRelacionUnoAUno(Mascota mascota, Connection conn) throws Exception {
        if (mascota.getMicrochip() == null || mascota.getMicrochip().getId() == null) {
            return; // No hay microchip que validar
        }
        
        try {
            // Buscar si el microchip ya está asignado a otra mascota
            List<Mascota> todasLasMascotas = mascotaDAO.leerTodos(conn);
            
            for (Mascota otraMascota : todasLasMascotas) {
                if (otraMascota.getMicrochip() != null && 
                    otraMascota.getMicrochip().getId().equals(mascota.getMicrochip().getId()) &&
                    !otraMascota.getId().equals(mascota.getId())) {
                    
                    throw new Exception(
                        String.format("Violación regla 1→1: El microchip con ID %d ya está asignado a la mascota '%s' (ID: %d)",
                                mascota.getMicrochip().getId(),
                                otraMascota.getNombre(),
                                otraMascota.getId())
                    );
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al validar relación 1→1: " + e.getMessage(), e);
        }
    }
}
