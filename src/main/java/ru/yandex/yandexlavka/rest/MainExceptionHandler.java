package ru.yandex.yandexlavka.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;
import ru.yandex.yandexlavka.rest.exceptions.TooManyConnectionsException;

import java.nio.file.AccessDeniedException;
import java.util.Map;
import java.util.TreeMap;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class MainExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = { ServerWebInputException.class, NumberFormatException.class, IllegalArgumentException.class })
    public ResponseEntity<Object> handleNumberFormatException(Exception ex) {
        return new ResponseEntity<>(getBody(BAD_REQUEST, ex, ex.getMessage()), new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex) {
        return new ResponseEntity<>(getBody(FORBIDDEN, ex, ex.getMessage()), new HttpHeaders(), FORBIDDEN);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception ex) {
        return new ResponseEntity<>(getBody(NOT_FOUND, ex, ex.getMessage()), new HttpHeaders(), NOT_FOUND);
    }

    @ExceptionHandler(TooManyConnectionsException.class)
    public ResponseEntity<Object> handleTooManyConnectionsException(Exception ex) {
        return new ResponseEntity<>(getBody(TOO_MANY_REQUESTS, ex, ex.getMessage()), new HttpHeaders(), TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception ex) {
        return new ResponseEntity<>(getBody(INTERNAL_SERVER_ERROR, ex, ex.getMessage()), new HttpHeaders(), INTERNAL_SERVER_ERROR);
    }

    public Map<String, Object> getBody(HttpStatus status, Exception ex, String message) {

        log.info("Exception resolved: " + message + " of type " + ex.getClass().getName());

        Map<String, Object> body = new TreeMap<>();

        // return empty body

        /*
        body.put("message", message);
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("exception", ex.toString());

        Throwable cause = ex.getCause();
        if (cause != null) {
            body.put("exceptionCause", ex.getCause().toString());
        }
        */

        return body;
    }

}