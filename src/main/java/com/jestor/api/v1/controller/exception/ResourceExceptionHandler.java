package com.jestor.api.v1.controller.exception;

import com.jestor.infrastructure.service.exception.ResourceAlreadyExistsException;
import com.jestor.infrastructure.service.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError error = new StandardError();
        error.setError("Not found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatus(status.value());
        error.setTimestamp(Instant.now());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<StandardError> resourceAlreadyExists(ResourceAlreadyExistsException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError();
        error.setError("Bad Request");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatus(status.value());
        error.setTimestamp(Instant.now());

        return ResponseEntity.status(status).body(error);
    }
}