package br.com.vr.vrminiautorizador.exceptions;

import br.com.vr.vrminiautorizador.models.dtos.exceptions.ExceptionDTO;
import br.com.vr.vrminiautorizador.utils.ExceptionUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.NotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptions {
    @ResponseBody
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ExceptionDTO handleConstraintViolationException(final RuntimeException e) {
        String cause = e.getCause() == null ? e.getMessage() : e.getCause().toString();
        return ExceptionUtils.formatException(cause, INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ValidationException.class)
    public ExceptionDTO handleValidationExceptionOnUnprocessableEntity(final ValidationException e) {
        String cause = e.getMessage();
        return new ExceptionDTO(UNPROCESSABLE_ENTITY.value(), cause);
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionDTO handleValidationExceptiOnMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        String cause = errors.get(0);
        return new ExceptionDTO(BAD_REQUEST.value(), cause);
    }

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ExceptionDTO handleValidationExceptionOnNotFoundException(final EntityNotFoundException e) {
        String cause = e.getMessage();
        return new ExceptionDTO(NOT_FOUND.value(), cause);
    }
}
