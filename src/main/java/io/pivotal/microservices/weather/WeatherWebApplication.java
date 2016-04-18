package io.pivotal.microservices.weather;

/**
 * Created by baishi on 4/17/16.
 */
import io.pivotal.microservices.services.weather.WeatherServer;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The accounts web-application. This class has two uses:
 * <ol>
 * <li>Provide configuration and setup for {@link WeatherServer} ... or</li>
 * <li>Run as a stand-alone Spring Boot web-application for testing (in which
 * case there is <i>no</i> microservice registration</li>
 * </ol>
 * <p>
 * To execute as a microservice, run {@link WeatherServer} instead.
 *
 * @author Paul Chapman
 */
@SpringBootApplication
public class WeatherWebApplication {

    protected Logger logger = Logger.getLogger(WeatherWebApplication.class
            .getName());

    /**
     * Run the application using Spring Boot and an embedded servlet engine.
     *
     * @param args
     *            Program arguments - ignored.
     */
    public static void main(String[] args) {
        SpringApplication.run(WeatherWebApplication.class, args);
    }

}
