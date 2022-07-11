package dev.jdsoft.intel471.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/** Global exception handler for different types of exceptions */
@ControllerAdvice
public class ValidationExceptionHandler {

  @ExceptionHandler(BindException.class)
  public ResponseEntity<ApiExceptionDto> handleBindExceptions(BindException e) {
    List<String> errors = new ArrayList<>();
    for (FieldError error : e.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }
    for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }
    return ResponseEntity.badRequest().body(new ApiExceptionDto(errors));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiExceptionDto> handleOtherExceptions(Exception ex) {
    return ResponseEntity.internalServerError().body(new ApiExceptionDto(ex.getMessage()));
  }
}
