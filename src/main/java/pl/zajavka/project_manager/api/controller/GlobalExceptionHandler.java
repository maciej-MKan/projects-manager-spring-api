package pl.zajavka.project_manager.api.controller;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.zajavka.project_manager.api.dto.ValidationErrorResponse;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(Exception ex) {
        String message = ex.getLocalizedMessage();
        List<String> errors = new ArrayList<>();
        log.error("Validation error when new user add [{}]",message);

        ValidationErrorResponse response = ValidationErrorResponse.of(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                errors
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationErrorResponse> handleUniqueDataException(Exception ex) {
        String message = ex.getLocalizedMessage();
        List<String> errors = new ArrayList<>();
        log.error("Send data not unique [{}]",message);

        ValidationErrorResponse response = ValidationErrorResponse.of(
                HttpStatus.BAD_REQUEST.value(),
                "Send data not unique",
                errors
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
