package dev.jdsoft.intel471.iprecords;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.jdsoft.intel471.repository.Entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static dev.jdsoft.intel471.constants.DateTimeConstants.ISO_8601_DATE_TIME_FORMAT;

/** ip record entity class
 *
 */
// FYI: In prod grade application I wouldn't use this class as controller response.
// I'd create separate dto class for this purpose.
class IpRecord implements Entity {

  private UUID id;
  private final String type;
  private final String value;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ISO_8601_DATE_TIME_FORMAT)
  private final LocalDateTime firstSeen;

  private final int totalCount;

  IpRecord(String type, String value, LocalDateTime firstSeen, int totalCount) {
    this.type = type;
    this.value = value;
    this.firstSeen = firstSeen;
    this.totalCount = totalCount;
  }

  @Override
  public UUID getId() {
    return id;
  }

  @Override
  public void setId(UUID id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public String getType() {
    return type;
  }

  public int getTotalCount() {
    return totalCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IpRecord ipRecord = (IpRecord) o;
    return totalCount == ipRecord.totalCount
        && Objects.equals(id, ipRecord.id)
        && Objects.equals(type, ipRecord.type)
        && Objects.equals(value, ipRecord.value)
        && Objects.equals(firstSeen, ipRecord.firstSeen);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, value, firstSeen, totalCount);
  }

  @Override
  public String toString() {
    return "IpRecord{"
        + "id="
        + id
        + ", type='"
        + type
        + '\''
        + ", value='"
        + value
        + '\''
        + ", firstSeen="
        + firstSeen
        + ", totalCount="
        + totalCount
        + '}';
  }
}
