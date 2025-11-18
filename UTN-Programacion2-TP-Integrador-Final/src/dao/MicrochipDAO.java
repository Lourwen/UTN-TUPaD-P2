package dao;

import config.DatabaseConnection;
import entities.Microchip;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Microchip
 * Implementa operaciones básicas de base de datos usando PreparedStatement
 * Implementa la interfaz GenericDao para operaciones CRUD estándar
 */
public class MicrochipDAO implements GenericDao<Microchip> {

    // ==========================================================
    // MÉTODO PRIVADO PARA MAPEAR RESULTSET A ENTIDAD
    // ==========================================================
    private Microchip mapearMicrochip(ResultSet rs) throws SQLException {
        Microchip microchip = new Microchip();
        microchip.setId(rs.getLong("id_microchip"));
        microchip.setEliminado(rs.getBoolean("eliminado"));
        microchip.setCodigo(rs.getString("codigo"));

        java.sql.Date fechaImpl = rs.getDate("fecha_implantacion");
        if (fechaImpl != null) {
            microchip.setFechaImplantacion(fechaImpl.toLocalDate());
        }

        microchip.setVeterinaria(rs.getString("veterinaria"));
        microchip.setObservaciones(rs.getString("observaciones"));

        return microchip;
    }

    // ==========================================================
    // IMPLEMENTACIÓN DE GenericDao - CREAR
    // ==========================================================
    @Override
    public Microchip crear(Microchip microchip) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return crear(microchip, conn);
        }
    }

    @Override
    public Microchip crear(Microchip microchip, Connection connection) throws SQLException {
        String sql = "INSERT INTO microchip (codigo, fecha_implantacion, veterinaria, observaciones) " +
                    "VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, microchip.getCodigo());
            stmt.setDate(2, microchip.getFechaImplantacion() != null ? 
                        java.sql.Date.valueOf(microchip.getFechaImplantacion()) : null);
            stmt.setString(3, microchip.getVeterinaria());
            stmt.setString(4, microchip.getObservaciones());

            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        microchip.setId(keys.getLong(1));
                        return microchip;
                    }
                }
            }
        }
        
        throw new SQLException("Error al crear el microchip, no se generó ID");
    }

    // ==========================================================
    // IMPLEMENTACIÓN DE GenericDao - LEER
    // ==========================================================
    @Override
    public Microchip leer(long id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return leer(id, conn);
        }
    }

    @Override
    public Microchip leer(long id, Connection connection) throws SQLException {
        String sql = "SELECT id_microchip, eliminado, codigo, fecha_implantacion, " +
                    "veterinaria, observaciones " +
                    "FROM microchip " +
                    "WHERE id_microchip = ? AND eliminado = FALSE";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearMicrochip(rs);
                }
            }
        }

        return null;
    }

    // ==========================================================
    // IMPLEMENTACIÓN DE GenericDao - LEER TODOS
    // ==========================================================
    @Override
    public List<Microchip> leerTodos() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return leerTodos(conn);
        }
    }

    @Override
    public List<Microchip> leerTodos(Connection connection) throws SQLException {
        List<Microchip> microchips = new ArrayList<>();

        String sql = "SELECT id_microchip, eliminado, codigo, fecha_implantacion, " +
                    "veterinaria, observaciones " +
                    "FROM microchip " +
                    "WHERE eliminado = FALSE " +
                    "ORDER BY codigo " +
                    "LIMIT 100";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                microchips.add(mapearMicrochip(rs));
            }
        }

        return microchips;
    }

    // ==========================================================
    // IMPLEMENTACIÓN DE GenericDao - ACTUALIZAR
    // ==========================================================
    @Override
    public Microchip actualizar(Microchip microchip) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return actualizar(microchip, conn);
        }
    }

    @Override
    public Microchip actualizar(Microchip microchip, Connection connection) throws SQLException {
        String sql = "UPDATE microchip SET " +
                    "codigo = ?, " +
                    "fecha_implantacion = ?, " +
                    "veterinaria = ?, " +
                    "observaciones = ? " +
                    "WHERE id_microchip = ? AND eliminado = FALSE";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, microchip.getCodigo());
            stmt.setDate(2, microchip.getFechaImplantacion() != null ? 
                        java.sql.Date.valueOf(microchip.getFechaImplantacion()) : null);
            stmt.setString(3, microchip.getVeterinaria());
            stmt.setString(4, microchip.getObservaciones());
            stmt.setLong(5, microchip.getId());

            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                return microchip;
            } else {
                throw new SQLException("No se pudo actualizar el microchip con ID: " + microchip.getId());
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
        String sql = "UPDATE microchip SET eliminado = TRUE WHERE id_microchip = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // ==========================================================
    // MÉTODOS ADICIONALES ESPECÍFICOS PARA MICROCHIP
    // ==========================================================
    
    /**
     * Busca un microchip por su código único
     * 
     * @param codigo Código del microchip a buscar
     * @return El microchip encontrado o null si no existe
     * @throws SQLException si ocurre un error durante la operación
     */
    public Microchip buscarPorCodigo(String codigo) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return buscarPorCodigo(codigo, conn);
        }
    }

    public Microchip buscarPorCodigo(String codigo, Connection connection) throws SQLException {
        String sql = "SELECT id_microchip, eliminado, codigo, fecha_implantacion, " +
                    "veterinaria, observaciones " +
                    "FROM microchip " +
                    "WHERE codigo = ? AND eliminado = FALSE";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearMicrochip(rs);
                }
            }
        }

        return null;
    }

    /**
     * Busca microchips por veterinaria
     * 
     * @param veterinaria Nombre de la veterinaria
     * @return Lista de microchips de la veterinaria especificada
     * @throws SQLException si ocurre un error durante la operación
     */
    public List<Microchip> buscarPorVeterinaria(String veterinaria) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return buscarPorVeterinaria(veterinaria, conn);
        }
    }

    public List<Microchip> buscarPorVeterinaria(String veterinaria, Connection connection) throws SQLException {
        List<Microchip> microchips = new ArrayList<>();

        String sql = "SELECT id_microchip, eliminado, codigo, fecha_implantacion, " +
                    "veterinaria, observaciones " +
                    "FROM microchip " +
                    "WHERE veterinaria LIKE ? AND eliminado = FALSE " +
                    "ORDER BY codigo " +
                    "LIMIT 50";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + veterinaria + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    microchips.add(mapearMicrochip(rs));
                }
            }
        }

        return microchips;
    }

    /**
     * Cuenta el total de microchips activos
     * 
     * @return Número total de microchips no eliminados
     * @throws SQLException si ocurre un error durante la operación
     */
    public int contarMicrochips() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return contarMicrochips(conn);
        }
    }

    public int contarMicrochips(Connection connection) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM microchip WHERE eliminado = FALSE";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            }
        }

        return 0;
    }
}