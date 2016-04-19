package io.pivotal.microservices.services.web;


import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import io.pivotal.microservices.location.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * Created by alicesypark on 4/18/16.
 */
@Service
public class WebLocationService {


    @Autowired
    protected RestTemplate restTemplate;

    protected String serviceUrl;

    protected Logger logger = Logger.getLogger(WebLocationService.class
            .getName());

    public WebLocationService(String serviceUrl) {
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

    public Location findByLocationName(String locationName) {

        logger.info("findByLocationName() invoked: for " + locationName);
        return restTemplate.getForObject(serviceUrl + "/location/{locationName}",
                Location.class, locationName);
    }
}
