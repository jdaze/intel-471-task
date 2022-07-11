package dev.jdsoft.intel471.repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple thread-safe in-memory implemention of repository
 *
 * @param <T> -> type of Entity
 */
public class InMemoryRepository<T extends Entity> implements Repository<T> {

  protected final Map<UUID, T> records = new ConcurrentHashMap<>();

  @Override
  public T save(T entity) {
    var uuid = UUID.randomUUID();
    entity.setId(uuid);
    records.put(entity.getId(), entity);
    return entity;
  }
}
