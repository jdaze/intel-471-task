package dev.jdsoft.intel471.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Validates if date string is in given format
 */
public class DateFormatValidator implements ConstraintValidator<ValidDateFormat, String> {

  private String pattern;

  @Override
  public void initialize(ValidDateFormat annotation) {
    this.pattern = annotation.pattern();
  }

  @Override
  public boolean isValid(String dateStr, ConstraintValidatorContext cxt) {
    if (dateStr == null || dateStr.isBlank()) {
      return false;
    }
    try {
      DateTimeFormatter.ofPattern(pattern).parse(dateStr);
      return true;
    } catch (DateTimeParseException e) {
      return false;
    }
  }
}
