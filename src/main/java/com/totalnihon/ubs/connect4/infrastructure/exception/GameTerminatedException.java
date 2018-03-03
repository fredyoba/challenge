package com.totalnihon.ubs.connect4.infrastructure.exception;

public class GameTerminatedException extends Exception {
    public GameTerminatedException(String msg) {
        super(msg);
    }
}
