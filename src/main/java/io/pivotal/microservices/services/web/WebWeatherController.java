package io.pivotal.microservices.services.web;

/**
 * Created by baishi on 4/17/16.
 */
import java.util.logging.Logger;

import io.pivotal.microservices.weather.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Client controller, fetches Account info from the microservice via
 * {@link WebAccountsService}.
 *
 * @author Paul Chapman
 */
@Controller
public class WebWeatherController {

    @Autowired
    protected WebWeatherService weatherService;

    protected Logger logger = Logger.getLogger(WebWeatherController.class
            .getName());

    public WebWeatherController(WebWeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping("/weather")
    public String goHome() {
        return "index";
    }

    @RequestMapping("/weather/{cityName}")
    public String byCityName(Model model,
                           @PathVariable("cityName") String cityName) {

        logger.info("web-service byCityName() invoked: " + cityName);
        Weather weather = weatherService.findByCityName(cityName);
        logger.info("web-service byCityName() found: " + weather);
        model.addAttribute("weather", weather);
        return "weather";
    }
}
