package io.pivotal.microservices.services.web;

/**
 * Created by elliottktan on 4/17/16.
 */
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import io.pivotal.microservices.stock.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Hide the access to the microservice inside this local service.
 *
 * @author Paul Chapman
 */
@Service
public class WebStockService {

    @Autowired
    protected RestTemplate restTemplate;

    protected String serviceUrl;

    protected Logger logger = Logger.getLogger(WebStockService.class
            .getName());

    public WebStockService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
                : "http://" + serviceUrl;
    }

    /**
     * The RestTemplate works because it uses a custom request-factory that uses
     * Ribbon to look-up the service to use. This method simply exists to show
     * this.
     */
    @PostConstruct
    public void demoOnly() {
        // Can't do this in the constructor because the RestTemplate injection
        // happens afterwards.
        logger.warning("The RestTemplate request factory is "
                + restTemplate.getRequestFactory());
    }

    public Stock findByStockName(String stockName) {

        logger.info("findByStockName() invoked: for " + stockName);
        return restTemplate.getForObject(serviceUrl + "/stock/{stockName}",
                Stock.class, stockName);
    }
}
