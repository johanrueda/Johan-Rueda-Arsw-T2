package edu.eci.arsw.weather.Services;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.weather.Services.HttPConnectionService;
import edu.eci.arsw.weather.persistence.Cache;
import edu.eci.arsw.weather.model.Weather;
import edu.eci.arsw.weather.persistence.weatherPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import edu.eci.arsw.weather.Services.weatherServices;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

@Service("openWeatherServices")
public class weatherServicesImpl implements  weatherServices{

    @Autowired
    @Qualifier("connection")
    HttPConnectionService httpConnectionService = null;

    @Autowired
    @Qualifier("openWeatherCache")
    Cache cacheInterface;
    /**
     * Obtiene el clima de la ciudad utilizando el nombre a consultar
     *
     * @param nombre
     * @return
     */
    @Override
    public Weather getWeatherOfACity(String nombre) throws weatherServiceException {
        Weather weather;
        try {
            if (cacheInterface.isOnCache(nombre)) {
                weather = cacheInterface.getWeatherOfACity(nombre);
            } else {
                weather = httpConnectionService.getWeatherOfACity(nombre);
                weather.setLocalDateTime(LocalDateTime.now());
                cacheInterface.putOnCache(weather);
            }
        } catch (weatherServiceException | weatherPersistenceException | UnsupportedEncodingException e) {
            throw new weatherServiceException(e.getMessage(), e);
        }
        return weather;
    }
        
    }

