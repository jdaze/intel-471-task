package dev.jdsoft.intel471.iprecords;

import dev.jdsoft.intel471.repository.InMemoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/** ip records in-memory repository */
@Repository
class IpRecordRepository extends InMemoryRepository<IpRecord> {

  List<IpRecord> findByValue(String value) {
    return records.values().stream()
        .filter(record -> record.getValue().equals(value))
        .collect(Collectors.toList());
  }
}
