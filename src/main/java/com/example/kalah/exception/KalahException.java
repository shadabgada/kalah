package com.example.kalah.exception;

public abstract class KalahException extends Exception {
    KalahException(String message) {
        super("KalahException: " + message);
    }
}
