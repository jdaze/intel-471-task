package dev.jdsoft.intel471.repository;

import java.util.UUID;

/** Should be implemented by all entities used with Repository interface */
public interface Entity {

  UUID getId();

  void setId(UUID id);
}
