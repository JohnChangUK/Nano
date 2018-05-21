package com.nano.client;

public class NanoException extends RuntimeException {
    public NanoException(String message) {
        super(message);
    }

    public NanoException(String message, Throwable cause) {
        super(message, cause);
    }
}
