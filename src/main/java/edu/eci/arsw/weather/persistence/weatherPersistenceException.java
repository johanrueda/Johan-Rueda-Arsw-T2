package edu.eci.arsw.weather.persistence;

public class weatherPersistenceException extends  Exception{

    public weatherPersistenceException(String message) {
        super(message);
    }

    public weatherPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
