package io.pivotal.microservices.location;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by alicesypark on 4/18/16.
 */
@RestController
public class LocationController {
    protected Logger logger = Logger.getLogger(LocationController.class
            .getName());

    /**
     * Fetch current stock information with the specified stockName.
     *
     * @param locationName
     *            e.g. MSFT, GOOG
     * @return The location information if found.
     */
    @RequestMapping("/location/{locationName}")
    public Location byLocationName(@PathVariable("locationName") String locationName) {

        logger.info("location-service byLocationName() invoked: " + locationName);
        Location location = Location.findByLocationName(locationName);
        logger.info("location-service byLocationName() found: " + location);

        return location;
    }
}
