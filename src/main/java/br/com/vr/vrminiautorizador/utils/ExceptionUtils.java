package br.com.vr.vrminiautorizador.utils;

import br.com.vr.vrminiautorizador.models.dtos.exceptions.ExceptionDTO;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class ExceptionUtils {
    public static final String MSG_EXCEPTION_GENERIC = "Ocorreu um erro inesperado. Contate o administrador do sistema.";

    public static HashMap<String, String> getExceptionMap() {
        HashMap<String, String> map = new HashMap<>();

        // Native Exceptions
        map.put("ConstraintViolationException","There was a constraint violation in the Database");
        map.put("HttpClientErrorException","There was an issue while trying to connect to the client.");
        map.put("HttpHostConnectException","The host could not be reached.");

        return map;
    }

    public static ExceptionDTO formatException(String cause, HttpStatus exception) {
        String message = ExceptionUtils.getExceptionMap().get(cause);
        message = message == null ? MSG_EXCEPTION_GENERIC : message;
        return new ExceptionDTO(exception.value(), message);
    }
}
