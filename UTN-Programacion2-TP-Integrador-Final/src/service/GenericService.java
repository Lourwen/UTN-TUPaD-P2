package service;

import java.util.List;

/**
 * Interfaz genérica para servicios con transacciones obligatorias
 * Define las operaciones básicas que deben implementar todos los servicios
 * Todas las operaciones manejan transacciones internamente
 * 
 * @param <T> Tipo de entidad que maneja el servicio
 * @author Sistema
 */
public interface GenericService<T> {
    
    /**
     * Inserta una nueva entidad en una transacción
     * Maneja automáticamente commit/rollback y autoCommit
     * 
     * @param entity La entidad a insertar
     * @return La entidad insertada con su ID generado
     * @throws Exception si la validación falla o ocurre un error en la transacción
     */
    T insertar(T entity) throws Exception;
    
    /**
     * Actualiza una entidad existente en una transacción
     * Maneja automáticamente commit/rollback y autoCommit
     * 
     * @param entity La entidad con los datos actualizados
     * @return La entidad actualizada
     * @throws Exception si la validación falla o ocurre un error en la transacción
     */
    T actualizar(T entity) throws Exception;
    
    /**
     * Elimina una entidad por su ID en una transacción
     * Maneja automáticamente commit/rollback y autoCommit
     * 
     * @param id ID de la entidad a eliminar
     * @return true si la eliminación fue exitosa
     * @throws Exception si ocurre un error en la transacción
     */
    boolean eliminar(long id) throws Exception;
    
    /**
     * Obtiene una entidad por su ID
     * No requiere transacción por ser operación de solo lectura
     * 
     * @param id ID de la entidad a obtener
     * @return La entidad encontrada o null si no existe
     * @throws Exception si ocurre un error
     */
    T getById(long id) throws Exception;
    
    /**
     * Obtiene todas las entidades activas
     * No requiere transacción por ser operación de solo lectura
     * 
     * @return Lista de todas las entidades no eliminadas
     * @throws Exception si ocurre un error
     */
    List<T> getAll() throws Exception;
}