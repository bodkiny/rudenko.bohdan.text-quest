package ua.javarush.textquest.repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for CRUD operations
 *
 * @param <T> entity
 */
public interface Repository<T> {
    /**
     * Create entity in database
     *
     * @param entity entity
     * @return created entity
     */
    T create(T entity);

    /**
     * Read entity from database
     *
     * @param id entity id
     * @return Optional<T> - entity
     */
    Optional<T> read(int id);

    /**
     * Update entity in database
     *
     * @param entity entity
     * @return updated entity
     */
    T update(T entity);

    /**
     * Delete entity from database
     *
     * @param id entity id
     */
    void delete(int id);

    /**
     * Find all entities in database
     *
     * @return List<T> - list of entities
     */
    List<T> findAll();

    /**
     * Find an entity by name.
     * It can be used for finding users by name or overridden for finding questions or users by their ID.
     *
     * @param name entity name
     * @return Optional<T> - entity
     */
    Optional<T> findBy(String name);
}
