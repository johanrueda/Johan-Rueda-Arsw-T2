package edu.eci.arsw.weather.persistence;

import edu.eci.arsw.weather.model.Weather;
import org.springframework.stereotype.Service;

@Service
public interface Cache {
    Weather getWeatherOfACity(String city) ;

    void putOnCache(Weather weather) ;

    void removeFromCache(Weather weather) ;

    boolean isOnCache(String city) ;

}
