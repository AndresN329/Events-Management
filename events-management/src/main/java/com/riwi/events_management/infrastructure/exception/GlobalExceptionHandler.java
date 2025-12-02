package com.riwi.events_management.infrastructure.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.Instant;
import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ProblemDetail buildProblemDetail(
            HttpStatus status,
            String detail,
            HttpServletRequest request
    ) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);

        problem.setTitle(status.getReasonPhrase());
        problem.setProperty("timestamp", Instant.now().toString());
        problem.setProperty("traceId", UUID.randomUUID().toString());
        problem.setProperty("instance", request.getRequestURI());
        problem.setType(URI.create("https://api.events-management.com/errors/" + status.value()));

        return problem;
    }

    // Manejo de validaciones @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse("Datos inválidos");

        return buildProblemDetail(HttpStatus.BAD_REQUEST, errorMessage, request);
    }

    // Manejo de validaciones en parametros @RequestParam
    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolation(
            ConstraintViolationException ex,
            HttpServletRequest request
    ) {
        return buildProblemDetail(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    // Manejo entidad no encontrada
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFound(
            NotFoundException ex,
            HttpServletRequest request
    ) {
        return buildProblemDetail(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    // Manejo de conflictos (unicidad, FK, etc.)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrity(
            DataIntegrityViolationException ex,
            HttpServletRequest request
    ) {
        return buildProblemDetail(HttpStatus.CONFLICT, "Conflicto de datos", request);
    }

    // Manejo genérico
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(
            Exception ex,
            HttpServletRequest request
    ) {
        return buildProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request);
    }
}
