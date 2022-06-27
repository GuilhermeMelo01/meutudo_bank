package com.whiz.br.resource.exceptions;

import com.whiz.br.service.exception.IllegalArgumentException;
import com.whiz.br.service.exception.ObjectNotFoundException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> handlerIllegalArgumentException(IllegalArgumentException exception, HttpServletRequest request) {
        StandardError stde = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value()
                , "illegal argument", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stde);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> handlerObjectNotFoundException(ObjectNotFoundException exception, HttpServletRequest request) {
        StandardError stde = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value()
                , "object not found", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(stde);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> handlerHttpMessageNotReadableException(HttpMessageNotReadableException exception,
                                                                                HttpServletRequest request) {
        StandardError stde = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value()
                , "value cannot be empty", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stde);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<StandardError> handlerInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException exception,
                                                                                HttpServletRequest request) {
        StandardError stde = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value()
                , "value cannot be null", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stde);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardError> handlerNoSuchElementException(NoSuchElementException exception,
                                                                                   HttpServletRequest request) {
        StandardError stde = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value()
                , "value not found", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(stde);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception,
                                                                       HttpServletRequest request) {
        StandardError stde = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value()
                , "value cannot be null", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stde);
    }
}
