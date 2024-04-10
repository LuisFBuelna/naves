package com.buelna.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NaveExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException notFoundException) {
        ExceptionResponse response = new ExceptionResponse(
                notFoundException.getMessage(),
                HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception exception) {
        ExceptionResponse response = new ExceptionResponse(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NaveSinTripulantesException.class})
    public ResponseEntity<Object> handleSinTripulanteException(NaveSinTripulantesException exception) {
        ExceptionResponse response = new ExceptionResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NaveSinTripulantesException.class})
    public ResponseEntity<Object> handleBadRequestException(BadRequestException exception) {
        ExceptionResponse response = new ExceptionResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
