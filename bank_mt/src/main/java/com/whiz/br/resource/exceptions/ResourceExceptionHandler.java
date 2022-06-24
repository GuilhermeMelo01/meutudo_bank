package com.whiz.br.resource.exceptions;

import com.whiz.br.service.exception.IllegalArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> file(IllegalArgumentException iae, HttpServletRequest request) {

        StandardError stde = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value()
                , "Illegal Argument", iae.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stde);
    }
}
