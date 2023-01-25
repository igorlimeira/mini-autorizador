package br.com.vr.vrminiautorizador.validators;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class EntityValidator {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static ValidationException customException(String message) {
        return new ValidationException(message);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public static ValidationException invalidProcessException(String message) {
        return new ValidationException(message);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static EntityNotFoundException notFoundException(String message) {
        return new EntityNotFoundException(message);
    }
}
