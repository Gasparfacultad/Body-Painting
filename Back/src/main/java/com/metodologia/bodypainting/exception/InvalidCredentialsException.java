package com.metodologia.bodypainting.exception;

/**
 * Excepción lanzada cuando las credenciales ingresadas son incorrectas.
 */
public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String message) {
        super(message);
    }
}