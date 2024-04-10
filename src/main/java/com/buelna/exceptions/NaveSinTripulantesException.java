package com.buelna.exceptions;

public class NaveSinTripulantesException extends RuntimeException {

    public NaveSinTripulantesException(String message) {
        super(message);
    }

    public NaveSinTripulantesException(String message, Throwable cause) {
        super(message, cause);
    }
}
