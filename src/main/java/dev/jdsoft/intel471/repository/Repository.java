package dev.jdsoft.intel471.repository;

/**
 * Basic repository interface
 * @param <Entity> -> type of Entity
 */
public interface Repository<Entity> {

  /**
   * Saves new or updates existing entity
   * @param entity to save or update
   * @return saved or updated entity
   */
  Entity save(Entity entity);
}
