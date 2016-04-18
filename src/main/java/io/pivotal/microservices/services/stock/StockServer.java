package io.pivotal.microservices.services.stock;

/**
 * Created by elliottktan on 4/17/16.
 */
import io.pivotal.microservices.stock.StockWebApplication;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 * <p>
 * Note that the configuration for this application is imported from
 * {@link StockWebApplication}. This is a deliberate separation of concerns
 * and allows the application to run:
 * <ul>
 * <li>Standalone - by executing {@link StockWebApplication#main(String[])}</li>
 * <li>As a microservice - by executing {@link StockServer#main(String[])}</li>
 * </ul>
 *
 * @author Elliott Tan
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(StockWebApplication.class)
public class StockServer {

    protected Logger logger = Logger.getLogger(StockServer.class.getName());

    /**
     * Run the application using Spring Boot and an embedded servlet engine.
     *
     * @param args
     *            Program arguments - ignored.
     */
    public static void main(String[] args) {
        // Tell server to look for accounts-server.properties or
        // accounts-server.yml
        System.setProperty("spring.config.name", "stock-server");

        SpringApplication.run(StockServer.class, args);
    }
}

