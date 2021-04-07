package edu.eci.arsw.weather.persistence;

import edu.eci.arsw.weather.model.Weather;
import org.springframework.stereotype.Service;

@Service
public interface Cache {

    Weather getWeatherOfACity(String city) throws weatherPersistenceException;

    void putOnCache(Weather weather) throws weatherPersistenceException;

    void removeFromCache(Weather weather) throws weatherPersistenceException;

    boolean isOnCache(String city) throws weatherPersistenceException;

}
