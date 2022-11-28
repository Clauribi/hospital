package com.example.persistencia.domain.exceptions;

public class DoctorNotExistException extends Exception{
    public DoctorNotExistException(String message) {
        super(message);
    }
}
