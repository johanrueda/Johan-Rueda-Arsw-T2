package edu.eci.arsw.weather.controller;
import  edu.eci.arsw.weather.controller.weatherController;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.weather.Services.weatherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/weather")
public class weatherController {

    @Autowired
    @Qualifier("openWeatherServices")
    private weatherServices openWeatherServices;

    /**
     * Obtiene los datos climáticos de una ciudad del mundo
     * @param nombre de la ciudad
     * @return Un JSON con el código del país, ciudad, clima, descripción, temperatura, sensación térmica, latitud, longitud y LocalDateTime de la consulta
     */
    @GetMapping("/{nombre}")
    public ResponseEntity<?> getWeatherOfACity(@PathVariable String nombre) {
        return new ResponseEntity<>(openWeatherServices.getWeatherOfACity(nombre), HttpStatus.ACCEPTED);
    }

}
}
