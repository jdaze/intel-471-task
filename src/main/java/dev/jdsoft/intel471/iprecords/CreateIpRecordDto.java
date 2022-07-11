package dev.jdsoft.intel471.iprecords;

import dev.jdsoft.intel471.validator.ValidDateFormat;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static dev.jdsoft.intel471.constants.DateTimeConstants.ISO_8601_DATE_TIME_FORMAT;

/**
 * Dto for ip record creation
 * @param type - type of ip record
 * @param value - value of ip
 * @param firstSeen - iso 8601 formatted date of when the record was first seen
 * @param totalCount - amount of how many ip records have been recorded for given value
 */
// Why firstSeen is a String instead of LocalDateTime or OffsetDateTime:
// Because it's impossible to validate date format before deserialization and I want to have all validation messages returned at once
//
record CreateIpRecordDto(
        String type,
        @NotEmpty(message = "value cannot be empty")
        String value,
        @NotNull(message = "firstSeen cannot be empty")
        @ValidDateFormat(pattern = ISO_8601_DATE_TIME_FORMAT, message = "firstSeen should be in " + ISO_8601_DATE_TIME_FORMAT + " format")
        String firstSeen,
        @Range(max = 100, message = "totalCount should be between 0 and 100 inclusive") int totalCount
) {

    IpRecord toIpRecord(){
        return new IpRecord(type, value, LocalDateTime.from(DateTimeFormatter.ofPattern(ISO_8601_DATE_TIME_FORMAT).parse(firstSeen)), totalCount);
    }
}
