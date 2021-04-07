package edu.eci.arsw.weather.model;

public class weatherException extends Exception{
    public weatherException(String message) {
        super(message);
    }

    public weatherException(String message, Throwable cause) {
        super(message, cause);
    }
}
