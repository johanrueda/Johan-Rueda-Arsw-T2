package edu.eci.arsw.weather.Services.impl;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.weather.Services.HttPConnectionService;
import edu.eci.arsw.weather.persistence.Cache;
import edu.eci.arsw.weather.model.Weather;
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
    public Weather getWeatherOfACity(String nombre) {
        Weather weather = null;
        try {
            if (cacheInterface.isOnCache(nombre)) {
                weather = cacheInterface.getWeatherOfACity(nombre);
            } else {
                weather = httpConnectionService.getWeatherOfACity(nombre);
                weather.setLocalDateTime(LocalDateTime.now());
                cacheInterface.putOnCache(weather);
            }
            return weather;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return weather;
    }
        
    }

