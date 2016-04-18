package io.pivotal.microservices.stock;

/**
 * Created by elliottktan on 4/17/16.
 */
import io.pivotal.microservices.services.stock.StockServer;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The accounts web-application. This class has two uses:
 * <ol>
 * <li>Provide configuration and setup for {@link StockServer} ... or</li>
 * <li>Run as a stand-alone Spring Boot web-application for testing (in which
 * case there is <i>no</i> microservice registration</li>
 * </ol>
 * <p>
 * To execute as a microservice, run {@link StockServer} instead.
 *
 * @author Elliott Tan
 */
@SpringBootApplication
public class StockWebApplication {

    protected Logger logger = Logger.getLogger(StockWebApplication.class
            .getName());

    /**
     * Run the application using Spring Boot and an embedded servlet engine.
     *
     * @param args
     *            Program arguments - ignored.
     */
    public static void main(String[] args) {
        SpringApplication.run(StockWebApplication.class, args);
    }

}
