package service;

import config.DatabaseConnection;
import dao.MicrochipDAO;
import entities.Microchip;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para la gestión de microchips (Entidad B)
 * Implementa GenericService con transacciones obligatorias
 * Maneja validaciones de negocio y regla 1→1
 */
public class MicrochipService implements GenericService<Microchip> {

    private final MicrochipDAO microchipDAO = new MicrochipDAO();

    // ==========================================================
    // IMPLEMENTACIÓN DE GenericService CON TRANSACCIONES
    // ==========================================================
    
    @Override
    public Microchip insertar(Microchip microchip) throws Exception {
        validarMicrochip(microchip);
        
        Connection conn = null;
        try {
            // Abrir transacción: setAutoCommit(false)
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            
            // Verificar que el código no esté duplicado
            Microchip existente = microchipDAO.buscarPorCodigo(microchip.getCodigo(), conn);
            if (existente != null) {
                throw new Exception("Ya existe un microchip con el código: " + microchip.getCodigo());
            }
            
            // Ejecutar inserción
            Microchip resultado = microchipDAO.crear(microchip, conn);
            
            // commit() si todo OK
            conn.commit();
            return resultado;
            
        } catch (Exception e) {
            // rollback() ante cualquier error
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    e.addSuppressed(rollbackEx);
                }
            }
            throw new Exception("Error al insertar microchip: " + e.getMessage(), e);
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
    public Microchip actualizar(Microchip microchip) throws Exception {
        validarMicrochipParaActualizacion(microchip);
        
        Connection conn = null;
        try {
            // Abrir transacción
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            
            // Verificar que el código no esté duplicado por otro microchip
            Microchip existente = microchipDAO.buscarPorCodigo(microchip.getCodigo(), conn);
            if (existente != null && !existente.getId().equals(microchip.getId())) {
                throw new Exception("Ya existe otro microchip con el código: " + microchip.getCodigo());
            }
            
            // Ejecutar actualización
            Microchip resultado = microchipDAO.actualizar(microchip, conn);
            
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
            throw new Exception("Error al actualizar microchip: " + e.getMessage(), e);
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
            boolean resultado = microchipDAO.eliminar(id, conn);
            
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
            throw new Exception("Error al eliminar microchip: " + e.getMessage(), e);
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
    public Microchip getById(long id) throws Exception {
        try {
            return microchipDAO.leer(id);
        } catch (SQLException e) {
            throw new Exception("Error al obtener microchip: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Microchip> getAll() throws Exception {
        try {
            return microchipDAO.leerTodos();
        } catch (SQLException e) {
            throw new Exception("Error al obtener microchips: " + e.getMessage(), e);
        }
    }

    // ==========================================================
    // MÉTODOS DE COMPATIBILIDAD
    // ==========================================================
    
    /**
     * Método de compatibilidad - redirige a insertar()
     */
    public Microchip crearMicrochip(Microchip microchip) throws Exception {
        return insertar(microchip);
    }
    
    /**
     * Método de compatibilidad - redirige a getById()
     */
    public Microchip obtenerMicrochip(long id) throws Exception {
        return getById(id);
    }
    
    /**
     * Método de compatibilidad - redirige a getAll()
     */
    public List<Microchip> obtenerTodosLosMicrochips() throws Exception {
        return getAll();
    }
    
    /**
     * Método de compatibilidad - redirige a actualizar()
     */
    public Microchip actualizarMicrochip(Microchip microchip) throws Exception {
        return actualizar(microchip);
    }
    
    /**
     * Método de compatibilidad - redirige a eliminar()
     */
    public boolean eliminarMicrochip(long id) throws Exception {
        return eliminar(id);
    }

    // ==========================================================
    // MÉTODOS DE BÚSQUEDA ADICIONALES
    // ==========================================================
    
    public Microchip buscarMicrochipPorCodigo(String codigo) throws Exception {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new Exception("El código de búsqueda no puede estar vacío");
        }
        
        try {
            return microchipDAO.buscarPorCodigo(codigo.trim());
        } catch (SQLException e) {
            throw new Exception("Error al buscar microchip por código: " + e.getMessage(), e);
        }
    }

    public List<Microchip> buscarMicrochipsPorVeterinaria(String veterinaria) throws Exception {
        if (veterinaria == null || veterinaria.trim().isEmpty()) {
            throw new Exception("El nombre de la veterinaria no puede estar vacío");
        }
        
        try {
            return microchipDAO.buscarPorVeterinaria(veterinaria.trim());
        } catch (SQLException e) {
            throw new Exception("Error al buscar microchips por veterinaria: " + e.getMessage(), e);
        }
    }

    public int contarMicrochips() throws Exception {
        try {
            return microchipDAO.contarMicrochips();
        } catch (SQLException e) {
            throw new Exception("Error al contar microchips: " + e.getMessage(), e);
        }
    }

    // ==========================================================
    // VALIDACIONES (campos obligatorios, formatos según dominio)
    // ==========================================================
    
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

    private void validarMicrochipParaActualizacion(Microchip microchip) throws Exception {
        validarMicrochip(microchip);
        
        if (microchip.getId() == null) {
            throw new Exception("El ID del microchip es obligatorio para actualización");
        }
    }
}