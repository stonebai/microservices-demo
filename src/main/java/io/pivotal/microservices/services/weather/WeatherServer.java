package io.pivotal.microservices.services.weather;

/**
 * Created by baishi on 4/17/16.
 */
import io.pivotal.microservices.weather.WeatherWebApplication;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 * <p>
 * Note that the configuration for this application is imported from
 * {@link WeatherWebApplication}. This is a deliberate separation of concerns
 * and allows the application to run:
 * <ul>
 * <li>Standalone - by executing {@link WeatherWebApplication#main(String[])}</li>
 * <li>As a microservice - by executing {@link WeatherServer#main(String[])}</li>
 * </ul>
 *
 * @author Shi Bai
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(WeatherWebApplication.class)
public class WeatherServer {

    protected Logger logger = Logger.getLogger(WeatherServer.class.getName());

    /**
     * Run the application using Spring Boot and an embedded servlet engine.
     *
     * @param args
     *            Program arguments - ignored.
     */
    public static void main(String[] args) {
        // Tell server to look for accounts-server.properties or
        // accounts-server.yml
        System.setProperty("spring.config.name", "weather-server");

        SpringApplication.run(WeatherServer.class, args);
    }
}

