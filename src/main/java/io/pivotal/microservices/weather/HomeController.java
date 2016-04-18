package io.pivotal.microservices.weather;

/**
 * Created by baishi on 4/17/16.
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home page controller.
 *
 * @author Paul Chapman
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

}
