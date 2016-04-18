package io.pivotal.microservices.services.web;

/**
 * Created by baishi on 4/17/16.
 */
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import io.pivotal.microservices.weather.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Hide the access to the microservice inside this local service.
 *
 * @author Paul Chapman
 */
@Service
public class WebWeatherService {

    @Autowired
    protected RestTemplate restTemplate;

    protected String serviceUrl;

    protected Logger logger = Logger.getLogger(WebWeatherService.class
            .getName());

    public WebWeatherService(String serviceUrl) {
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

    public Weather findByCityName(String cityName) {

        logger.info("findByCityName() invoked: for " + cityName);
        return restTemplate.getForObject(serviceUrl + "/weather/{cityName}",
                Weather.class, cityName);
    }

//    public Account findByNumber(String accountNumber) {
//
//        logger.info("findByNumber() invoked: for " + accountNumber);
//        return restTemplate.getForObject(serviceUrl + "/accounts/{number}",
//                Account.class, accountNumber);
//    }
//
//    public List<Account> byOwnerContains(String name) {
//        logger.info("byOwnerContains() invoked:  for " + name);
//        Account[] accounts = null;
//
//        try {
//            accounts = restTemplate.getForObject(serviceUrl
//                    + "/accounts/owner/{name}", Account[].class, name);
//        } catch (HttpClientErrorException e) { // 404
//            // Nothing found
//        }
//
//        if (accounts == null || accounts.length == 0)
//            return null;
//        else
//            return Arrays.asList(accounts);
//    }
}
