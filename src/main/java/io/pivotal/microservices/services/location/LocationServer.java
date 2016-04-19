package io.pivotal.microservices.services.location;

import java.util.logging.Logger;

import io.pivotal.microservices.location.LocationWebApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 * <p>
 * Note that the configuration for this application is imported from
 * {@link LocationWebApplication}. This is a deliberate separation of concerns
 * and allows the application to run:
 * <ul>
 * <li>Standalone - by executing {@link LocationWebApplication#main(String[])}</li>
 * <li>As a microservice - by executing {@link LocationServer#main(String[])}</li>
 * </ul>
 *
 * Created by alicesypark on 4/18/16.
 */

@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(LocationWebApplication.class)
public class LocationServer {
    protected Logger logger = Logger.getLogger(LocationServer.class.getName());

    /**
     * Run the application using Spring Boot and an embedded servlet engine.
     *
     * @param args
     *            Program arguments - ignored.
     */
    public static void main(String[] args) {
        // Tell server to look for accounts-server.properties or
        // accounts-server.yml
        System.setProperty("spring.config.name", "location-server");

        SpringApplication.run(LocationServer.class, args);
    }
}
