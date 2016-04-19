package io.pivotal.microservices.services.web;

import java.util.logging.Logger;

import io.pivotal.microservices.location.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  Client controller, fetches Location info from the microservice via
 * {@link WebLocationService}.
 * Created by alicesypark on 4/18/16.
 */
@Controller
public class WebLocationController {
    @Autowired
    protected WebLocationService locationService;

    protected Logger logger = Logger.getLogger(WebLocationController.class
            .getName());

    public WebLocationController(WebLocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping("/location")
    public String goHome() {
        return "index";
    }

    @RequestMapping("/location/{locationName}")
    public String byLocationName(Model model,
                                 @PathVariable("locationName") String locationName) {

        logger.info("web-service byLocationName() invoked: " + locationName);
        Location location = locationService.findByLocationName(locationName);
        logger.info("web-service byLocationName() found: " + locationName);
        model.addAttribute("location", location);
        return "location";
    }
}