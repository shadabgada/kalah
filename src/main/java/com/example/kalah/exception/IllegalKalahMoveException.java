package com.example.kalah.exception;

public class IllegalKalahMoveException extends KalahException {
    public IllegalKalahMoveException(String message) {
        super("Illegal move :" + message);
    }
}
