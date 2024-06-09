package pl.mkan.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.mkan.client.NBP.exception.CurrencyNotFoundException;
import pl.mkan.controller.dto.ExceptionMessage;

import java.util.Map;
import java.util.UUID;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Map<Class<?>, HttpStatus> EXCEPTION_STATUS = Map.of(
            ConstraintViolationException.class, HttpStatus.BAD_REQUEST,
            DataIntegrityViolationException.class, HttpStatus.BAD_REQUEST,
            CurrencyNotFoundException.class, HttpStatus.NOT_FOUND,
            IllegalArgumentException.class, HttpStatus.BAD_GATEWAY
    );

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            @NonNull Exception ex,
            Object body,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode statusCode,
            @NonNull WebRequest request
    ) {
        final String errorId = UUID.randomUUID().toString();
        log.error("Exception: ID={}, HttpStatus={}", errorId, statusCode, ex);
        return super.handleExceptionInternal(
                ex,
                new ExceptionMessage(errorId, "Other error. Please contact with system administrator"),
                headers,
                statusCode,
                request
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodValidationException(
            @NonNull MethodValidationException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request) {
        final String errorId = UUID.randomUUID().toString();
        log.error("Exception: ID={}, HttpStatus={}", errorId, status, ex);
        return ResponseEntity.badRequest().body(new ExceptionMessage(errorId, ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        final String errorId = UUID.randomUUID().toString();
        log.error("Exception: ID={}, HttpStatus={}", errorId, status, ex);
        return ResponseEntity.badRequest().body(new ExceptionMessage(errorId, "Validation error"));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            @NonNull HttpMessageNotReadableException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        final String errorId = UUID.randomUUID().toString();
        log.error("Exception: ID={}, HttpStatus={}", errorId, status, ex);
        return ResponseEntity.badRequest().body(new ExceptionMessage(errorId, "Bad request"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(final Exception exception) {
        return doHandle(exception, getHttpStatusFromException(exception.getClass()));
    }

    private ResponseEntity<Object> doHandle(final Exception exception, final HttpStatus status) {
        final String errorId = UUID.randomUUID().toString();
        log.error("Exception: ID={}, HttpStatus={}", errorId, status, exception);

        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ExceptionMessage(errorId, exception.getMessage()));
    }

    public HttpStatus getHttpStatusFromException(final Class<?> exception) {
        return EXCEPTION_STATUS.getOrDefault(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
