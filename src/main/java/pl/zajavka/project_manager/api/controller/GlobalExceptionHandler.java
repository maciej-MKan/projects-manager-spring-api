package pl.zajavka.project_manager.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.zajavka.project_manager.api.dto.ErrorMessage;
import pl.zajavka.project_manager.api.dto.ValidationErrorResponse;
import pl.zajavka.project_manager.domian.exception.InvalidCredentialsError;

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
        log.error("Validation error [{}]",message);

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

    @ExceptionHandler({InvalidCredentialsError.class, UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorMessage> handleInvalidCredentials(Exception ex){
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        log.error("Response of invalid credentials w/m [{}]", ex.getMessage());

        ErrorMessage response = ErrorMessage.of(status.value(), "Invalid Credentials");

        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> handleAnotherErrors(Exception ex){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        log.error("Unsupported error [{}]", ex.getMessage());

        ErrorMessage response = ErrorMessage.of(status.value(), "Something wrong");
        return new ResponseEntity<>(response, status);
    }
}
