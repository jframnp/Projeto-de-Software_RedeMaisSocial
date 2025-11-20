package br.edu.ufcg.voluntarios.exceptions;

public class EntidadeDuplicadaException extends RuntimeException {
    public EntidadeDuplicadaException(String message) {
        super(message);
    }
}