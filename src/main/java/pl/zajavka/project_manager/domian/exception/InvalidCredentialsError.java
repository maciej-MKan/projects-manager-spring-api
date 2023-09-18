package pl.zajavka.project_manager.domian.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class InvalidCredentialsError extends RuntimeException {
    public InvalidCredentialsError(String message, BadCredentialsException exception) {
        super(message, exception.getCause());
    }
}
