package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz genérica para operaciones DAO básicas
 * Define las operaciones CRUD estándar que deben implementar todos los DAOs
 * 
 * @param <T> Tipo de entidad que maneja el DAO
 * @author Sistema
 */
public interface GenericDao<T> {
    
    /**
     * Crea una nueva entidad en la base de datos
     * 
     * @param entity La entidad a crear
     * @return La entidad creada con su ID generado
     * @throws SQLException si ocurre un error durante la operación
     */
    T crear(T entity) throws SQLException;
    
    /**
     * Crea una nueva entidad en la base de datos usando una conexión externa
     * Permite participar en transacciones externas
     * 
     * @param entity La entidad a crear
     * @param connection Conexión externa para usar en la operación
     * @return La entidad creada con su ID generado
     * @throws SQLException si ocurre un error durante la operación
     */
    T crear(T entity, Connection connection) throws SQLException;
    
    /**
     * Lee una entidad por su ID
     * 
     * @param id ID de la entidad a buscar
     * @return La entidad encontrada o null si no existe
     * @throws SQLException si ocurre un error durante la operación
     */
    T leer(long id) throws SQLException;
    
    /**
     * Lee una entidad por su ID usando una conexión externa
     * 
     * @param id ID de la entidad a buscar
     * @param connection Conexión externa para usar en la operación
     * @return La entidad encontrada o null si no existe
     * @throws SQLException si ocurre un error durante la operación
     */
    T leer(long id, Connection connection) throws SQLException;
    
    /**
     * Lee todas las entidades no eliminadas
     * 
     * @return Lista de todas las entidades activas
     * @throws SQLException si ocurre un error durante la operación
     */
    List<T> leerTodos() throws SQLException;
    
    /**
     * Lee todas las entidades no eliminadas usando una conexión externa
     * 
     * @param connection Conexión externa para usar en la operación
     * @return Lista de todas las entidades activas
     * @throws SQLException si ocurre un error durante la operación
     */
    List<T> leerTodos(Connection connection) throws SQLException;
    
    /**
     * Actualiza una entidad existente
     * 
     * @param entity La entidad con los datos actualizados
     * @return La entidad actualizada
     * @throws SQLException si ocurre un error durante la operación
     */
    T actualizar(T entity) throws SQLException;
    
    /**
     * Actualiza una entidad existente usando una conexión externa
     * 
     * @param entity La entidad con los datos actualizados
     * @param connection Conexión externa para usar en la operación
     * @return La entidad actualizada
     * @throws SQLException si ocurre un error durante la operación
     */
    T actualizar(T entity, Connection connection) throws SQLException;
    
    /**
     * Elimina lógicamente una entidad por su ID
     * 
     * @param id ID de la entidad a eliminar
     * @return true si la eliminación fue exitosa
     * @throws SQLException si ocurre un error durante la operación
     */
    boolean eliminar(long id) throws SQLException;
    
    /**
     * Elimina lógicamente una entidad por su ID usando una conexión externa
     * 
     * @param id ID de la entidad a eliminar
     * @param connection Conexión externa para usar en la operación
     * @return true si la eliminación fue exitosa
     * @throws SQLException si ocurre un error durante la operación
     */
    boolean eliminar(long id, Connection connection) throws SQLException;
}