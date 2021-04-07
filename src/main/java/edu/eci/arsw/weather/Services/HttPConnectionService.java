package edu.eci.arsw.weather.Services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.weather.model.Weather;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service("connection")
public class HttPConnectionService {
    public Weather getWeatherOfACity(String nombre) throws weatherServiceException, UnsupportedEncodingException {
        HttpResponse<JsonNode> response;
        String encodedQuery = URLEncoder.encode(nombre, StandardCharsets.UTF_8.toString());
        nombre = encodedQuery.replace("+", "%20");
        try {
            response = Unirest
                    .get("https://api.openweathermap.org/data/2.5/weather?q=" + nombre + "&appid=f30cfe4149c9d630e1bc1b2a2410c27a")
                    .asJson();
        } catch (UnirestException e) {
            throw new weatherServiceException("Error de conexion con Open Weather", e);
        }
        JSONObject jsonObject = response.getBody().getObject();
        if (jsonObject.getInt("cod") == 404) {
            throw new weatherServiceException("Ciudad no encontrada");
        }
        return getWeather(jsonObject);
    }


    private Weather getWeather(JSONObject jsonObject) {
        String countryCode = jsonObject.getJSONObject("sys").getString("country");
        String city = jsonObject.getString("name");
        String weather = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
        String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
        double temperature = Math.round((jsonObject.getJSONObject("main").getDouble("temp") - 273.15) * 10d) / 10d;
        double thermalSensation = Math.round((jsonObject.getJSONObject("main").getDouble("feels_like") - 273.15) * 10d) / 10d;
        double latitud = Math.round(jsonObject.getJSONObject("coord").getDouble("lat") * 10d) / 10d;
        double longitud = Math.round(jsonObject.getJSONObject("coord").getDouble("lon") * 10d) / 10d;

        return new Weather(countryCode, city, weather, description, temperature, thermalSensation, latitud, longitud);
    }

}
