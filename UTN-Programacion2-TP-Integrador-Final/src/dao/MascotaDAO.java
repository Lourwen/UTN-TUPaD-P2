package dao;

import config.DatabaseConnection;
import entities.Mascota;
import entities.Microchip;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Mascota
 * Implementa operaciones básicas de base de datos usando PreparedStatement
 * Implementa la interfaz GenericDao para operaciones CRUD estándar
 */
public class MascotaDAO implements GenericDao<Mascota> {

    // ==========================================================
    // MÉTODO PRIVADO PARA EVITAR REPETIR CÓDIGO
    // ==========================================================
    private Mascota mapearMascota(ResultSet rs) throws SQLException {
        Mascota mascota = new Mascota();
        mascota.setId(rs.getLong("id_mascota"));
        mascota.setEliminado(rs.getBoolean("mascota_eliminado"));
        mascota.setNombre(rs.getString("nombre"));
        mascota.setEspecie(rs.getString("especie"));
        mascota.setRaza(rs.getString("raza"));

        java.sql.Date fechaNac = rs.getDate("fecha_nacimiento");
        if (fechaNac != null) mascota.setFechaNacimiento(fechaNac.toLocalDate());

        mascota.setDuenio(rs.getString("duenio"));

        Long microchipId = rs.getLong("id_microchip");
        if (!rs.wasNull()) {
            Microchip mc = new Microchip();
            mc.setId(microchipId);
            mc.setEliminado(rs.getBoolean("microchip_eliminado"));
            mc.setCodigo(rs.getString("codigo"));

            java.sql.Date fechaImpl = rs.getDate("fecha_implantacion");
            if (fechaImpl != null) mc.setFechaImplantacion(fechaImpl.toLocalDate());

            mc.setVeterinaria(rs.getString("veterinaria"));
            mc.setObservaciones(rs.getString("observaciones"));

            mascota.setMicrochip(mc);
        }

        return mascota;
    }

    // ==========================================================
    // IMPLEMENTACIÓN DE GenericDao - CREAR
    // ==========================================================
    @Override
    public Mascota crear(Mascota mascota) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return crear(mascota, conn);
        }
    }

    @Override
    public Mascota crear(Mascota mascota, Connection connection) throws SQLException {
        String sql = "INSERT INTO mascota (nombre, especie, raza, fecha_nacimiento, duenio, microchip_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getEspecie());
            stmt.setString(3, mascota.getRaza());
            stmt.setDate(4, mascota.getFechaNacimiento() != null ? 
                        java.sql.Date.valueOf(mascota.getFechaNacimiento()) : null);
            stmt.setString(5, mascota.getDuenio());
            
            // Manejar microchip_id
            if (mascota.getMicrochip() != null && mascota.getMicrochip().getId() != null) {
                stmt.setLong(6, mascota.getMicrochip().getId());
            } else {
                stmt.setNull(6, java.sql.Types.BIGINT);
            }

            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        mascota.setId(keys.getLong(1));
                        return mascota;
                    }
                }
            }
        }
        
        throw new SQLException("Error al crear la mascota, no se generó ID");
    }

    // ==========================================================
    // IMPLEMENTACIÓN DE GenericDao - LEER
    // ==========================================================
    @Override
    public Mascota leer(long id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return leer(id, conn);
        }
    }

    @Override
    public Mascota leer(long id, Connection connection) throws SQLException {
        String sql = 
            "SELECT " +
                "m.id_mascota," +
                "m.eliminado AS mascota_eliminado," +
                "m.nombre," +
                "m.especie," +
                "m.raza," +
                "m.fecha_nacimiento," +
                "m.duenio," +
                "mc.id_microchip," +
                "mc.eliminado AS microchip_eliminado," +
                "mc.codigo," +
                "mc.fecha_implantacion," +
                "mc.veterinaria," +
                "mc.observaciones " +
            "FROM mascota m " +
            "LEFT JOIN microchip mc ON m.microchip_id = mc.id_microchip " +
            "WHERE m.id_mascota = ? AND m.eliminado = FALSE ";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearMascota(rs);
                }
            }
        }

        return null;
    }

    // ==========================================================
    // IMPLEMENTACIÓN DE GenericDao - LEER TODOS
    // ==========================================================
    @Override
    public List<Mascota> leerTodos() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return leerTodos(conn);
        }
    }

    @Override
    public List<Mascota> leerTodos(Connection connection) throws SQLException {
        List<Mascota> mascotas = new ArrayList<>();

        String sql = 
            "SELECT " +
                "m.id_mascota," +
                "m.eliminado AS mascota_eliminado," +
                "m.nombre," +
                "m.especie," +
                "m.raza," +
                "m.fecha_nacimiento," +
                "m.duenio," +
                "mc.id_microchip," +
                "mc.eliminado AS microchip_eliminado," +
                "mc.codigo," +
                "mc.fecha_implantacion," +
                "mc.veterinaria," +
                "mc.observaciones " +
            "FROM mascota m " +
            "LEFT JOIN microchip mc ON m.microchip_id = mc.id_microchip " +
            "WHERE m.eliminado = FALSE " +
            "ORDER BY m.nombre " +
            "LIMIT 100";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                mascotas.add(mapearMascota(rs));
            }
        }

        return mascotas;
    }

    // ==========================================================
    // IMPLEMENTACIÓN DE GenericDao - ACTUALIZAR
    // ==========================================================
    @Override
    public Mascota actualizar(Mascota mascota) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return actualizar(mascota, conn);
        }
    }

    @Override
    public Mascota actualizar(Mascota mascota, Connection connection) throws SQLException {
        String sql = "UPDATE mascota SET " +
                    "nombre = ?, " +
                    "especie = ?, " +
                    "raza = ?, " +
                    "fecha_nacimiento = ?, " +
                    "duenio = ?, " +
                    "microchip_id = ? " +
                    "WHERE id_mascota = ? AND eliminado = FALSE";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getEspecie());
            stmt.setString(3, mascota.getRaza());
            stmt.setDate(4, mascota.getFechaNacimiento() != null ? 
                        java.sql.Date.valueOf(mascota.getFechaNacimiento()) : null);
            stmt.setString(5, mascota.getDuenio());
            
            // Manejar microchip_id
            if (mascota.getMicrochip() != null && mascota.getMicrochip().getId() != null) {
                stmt.setLong(6, mascota.getMicrochip().getId());
            } else {
                stmt.setNull(6, java.sql.Types.BIGINT);
            }
            
            stmt.setLong(7, mascota.getId());

            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                return mascota;
            } else {
                throw new SQLException("No se pudo actualizar la mascota con ID: " + mascota.getId());
            }
        }
    }

    // ==========================================================
    // IMPLEMENTACIÓN DE GenericDao - ELIMINAR
    // ==========================================================
    @Override
    public boolean eliminar(long id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return eliminar(id, conn);
        }
    }

    @Override
    public boolean eliminar(long id, Connection connection) throws SQLException {
        String sql = "UPDATE mascota SET eliminado = TRUE WHERE id_mascota = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // ==========================================================
    // MÉTODOS ADICIONALES ESPECÍFICOS PARA MASCOTA
    // ==========================================================
    
    /**
     * Obtiene todas las mascotas (método legacy mantenido por compatibilidad)
     * @deprecated Usar leerTodos() en su lugar
     */
    @Deprecated
    public List<Mascota> obtenerTodas() throws SQLException {
        return leerTodos();
    }

    /**
     * Obtiene mascota por ID (método legacy mantenido por compatibilidad)
     * @deprecated Usar leer() en su lugar
     */
    @Deprecated
    public Mascota obtenerPorId(Long id) throws SQLException {
        return leer(id);
    }

    /**
     * Busca mascotas por nombre (coincidencia parcial)
     * 
     * @param nombre Nombre o parte del nombre a buscar
     * @return Lista de mascotas que coinciden con el criterio
     * @throws SQLException si ocurre un error durante la operación
     */
    public List<Mascota> buscarPorNombre(String nombre) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return buscarPorNombre(nombre, conn);
        }
    }

    public List<Mascota> buscarPorNombre(String nombre, Connection connection) throws SQLException {
        List<Mascota> mascotas = new ArrayList<>();

        String sql = 
            "SELECT " +
                "m.id_mascota," +
                "m.eliminado AS mascota_eliminado," +
                "m.nombre," +
                "m.especie," +
                "m.raza," +
                "m.fecha_nacimiento," +
                "m.duenio," +
                "mc.id_microchip," +
                "mc.eliminado AS microchip_eliminado," +
                "mc.codigo," +
                "mc.fecha_implantacion," +
                "mc.veterinaria," +
                "mc.observaciones " +
            "FROM mascota m " +
            "LEFT JOIN microchip mc ON m.microchip_id = mc.id_microchip " +
            "WHERE m.nombre LIKE ? AND m.eliminado = FALSE " +
            "ORDER BY m.nombre " +
            "LIMIT 50";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    mascotas.add(mapearMascota(rs));
                }
            }
        }

        return mascotas;
    }

    /**
     * Busca mascotas por especie
     * 
     * @param especie Especie de las mascotas a buscar
     * @return Lista de mascotas de la especie especificada
     * @throws SQLException si ocurre un error durante la operación
     */
    public List<Mascota> buscarPorEspecie(String especie) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return buscarPorEspecie(especie, conn);
        }
    }

    public List<Mascota> buscarPorEspecie(String especie, Connection connection) throws SQLException {
        List<Mascota> mascotas = new ArrayList<>();

        String sql = 
            "SELECT " +
                "m.id_mascota," +
                "m.eliminado AS mascota_eliminado," +
                "m.nombre," +
                "m.especie," +
                "m.raza," +
                "m.fecha_nacimiento," +
                "m.duenio," +
                "mc.id_microchip," +
                "mc.eliminado AS microchip_eliminado," +
                "mc.codigo," +
                "mc.fecha_implantacion," +
                "mc.veterinaria," +
                "mc.observaciones " +
            "FROM mascota m " +
            "LEFT JOIN microchip mc ON m.microchip_id = mc.id_microchip " +
            "WHERE m.especie LIKE ? AND m.eliminado = FALSE " +
            "ORDER BY m.nombre " +
            "LIMIT 50";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + especie + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    mascotas.add(mapearMascota(rs));
                }
            }
        }

        return mascotas;
    }

    /**
     * Cuenta el total de mascotas activas
     * 
     * @return Número total de mascotas no eliminadas
     * @throws SQLException si ocurre un error durante la operación
     */
    public int contarMascotas() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return contarMascotas(conn);
        }
    }

    public int contarMascotas(Connection connection) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM mascota WHERE eliminado = FALSE";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            }
        }

        return 0;
    }

    // ==========================================================
    // CREAR MASCOTA + MICROCHIP (CON TRANSACCIÓN)
    // ==========================================================
    /**
     * Crea una mascota junto con su microchip en una transacción
     * Este método demuestra el uso de DAOs con conexión externa para transacciones
     * 
     * @param mascota La mascota a crear
     * @param microchip El microchip a crear y asociar a la mascota
     * @return La mascota creada con el microchip asociado
     * @throws SQLException si ocurre un error durante la transacción
     */
    public Mascota crearMascotaConMicrochip(Mascota mascota, Microchip microchip) throws SQLException {
        Connection conn = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            // Crear instancia del DAO de Microchip para usar en la transacción
            MicrochipDAO microchipDAO = new MicrochipDAO();
            
            // 1) Insertar Microchip usando el DAO
            Microchip microchipCreado = microchipDAO.crear(microchip, conn);
            
            // 2) Asociar el microchip a la mascota
            mascota.setMicrochip(microchipCreado);
            
            // 3) Insertar Mascota usando el método con conexión externa
            Mascota mascotaCreada = crear(mascota, conn);
            
            // Commit de la transacción
            conn.commit();
            
            return mascotaCreada;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    e.addSuppressed(rollbackEx);
                }
            }
            throw e;

        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeEx) {
                    // Log error but don't throw to avoid masking original exception
                    System.err.println("Error al cerrar conexión: " + closeEx.getMessage());
                }
            }
        }
    }

    /**
     * Actualiza una mascota y su microchip en una transacción
     * 
     * @param mascota La mascota a actualizar
     * @return La mascota actualizada
     * @throws SQLException si ocurre un error durante la transacción
     */
    public Mascota actualizarMascotaConMicrochip(Mascota mascota) throws SQLException {
        Connection conn = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            // Actualizar mascota
            Mascota mascotaActualizada = actualizar(mascota, conn);
            
            // Si tiene microchip, actualizarlo también
            if (mascota.getMicrochip() != null && mascota.getMicrochip().getId() != null) {
                MicrochipDAO microchipDAO = new MicrochipDAO();
                microchipDAO.actualizar(mascota.getMicrochip(), conn);
            }
            
            // Commit de la transacción
            conn.commit();
            
            return mascotaActualizada;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    e.addSuppressed(rollbackEx);
                }
            }
            throw e;

        } finally {
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
}