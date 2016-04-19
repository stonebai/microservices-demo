package io.pivotal.microservices.location;

import io.pivotal.microservices.services.location.LocationServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.logging.Logger;

/**
 *  * The accounts web-application. This class has two uses:
 * <ol>
 * <li>Provide configuration and setup for {@link LocationServer} ... or</li>
 * <li>Run as a stand-alone Spring Boot web-application for testing (in which
 * case there is <i>no</i> microservice registration</li>
 * </ol>
 * <p>
 * To execute as a microservice, run {@link LocationServer} instead.
 *
 * Created by alicesypark on 4/18/16.
 */

@SpringBootApplication
public class LocationWebApplication {
    protected Logger logger = Logger.getLogger(LocationWebApplication.class
            .getName());

    /**
     * Run the application using Spring Boot and an embedded servlet engine.
     *
     * @param args
     *            Program arguments - ignored.
     */
    public static void main(String[] args) {
        SpringApplication.run(LocationWebApplication.class, args);
    }
}
