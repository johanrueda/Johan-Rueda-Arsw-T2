package edu.eci.arsw.weather.Services;

import edu.eci.arsw.weather.model.Weather;
import org.springframework.stereotype.Service;

@Service
public interface weatherServices {

    /**
     * Obtiene el clima de la ciudad utilizando el nombre a consultar
     * @param nombre
     * @return
     */

    Weather getWeatherOfACity(String nombre) throws weatherServiceException;

}
