package com.example.persistencia.domain.exceptions;

public class OutOfTimeWindow extends Exception{
    public OutOfTimeWindow(String message) {
        super(message);
    }
}
