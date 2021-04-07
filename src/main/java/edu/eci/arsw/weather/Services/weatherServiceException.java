package edu.eci.arsw.weather.Services;

public class weatherServiceException extends Exception {
    public weatherServiceException(String message) {
        super(message);
    }

    public weatherServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
