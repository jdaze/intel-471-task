package dev.jdsoft.intel471.exception;

import java.util.Collections;
import java.util.List;

/** Dto for handled exceptions */
public class ApiExceptionDto {

  private final List<String> errors;

  public ApiExceptionDto(List<String> errors) {
    super();
    this.errors = errors;
  }

  public ApiExceptionDto(String error) {
    super();
    errors = Collections.singletonList(error);
  }

  public List<String> getErrors() {
    return errors;
  }
}
