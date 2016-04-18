package io.pivotal.microservices.weather;

/**
 * Created by baishi on 4/17/16.
 */

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A RESTFul controller for accessing account information.
 *
 * @author Paul Chapman
 */
@RestController
public class WeatherController {

    protected Logger logger = Logger.getLogger(WeatherController.class
            .getName());

    /**
     * Fetch current weather with the specified cityName.
     *
     * @param cityName
     *            e.g. London, Pittsburgh.
     * @return The weather if found.
     */
    @RequestMapping("/weather/{cityName}")
    public Weather byCityName(@PathVariable("cityName") String cityName) {

        logger.info("weather-service byCityName() invoked: " + cityName);
        Weather weather = Weather.findByCityName(cityName);
        logger.info("weather-service byCityName() found: " + weather);

        return weather;
    }
}

