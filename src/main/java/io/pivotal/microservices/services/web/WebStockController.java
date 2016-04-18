package io.pivotal.microservices.services.web;

/**
 * Created by elliottktan on 4/17/16.
 */
import java.util.logging.Logger;

import io.pivotal.microservices.stock.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Client controller, fetches Stock info from the microservice via
 * {@link WebStockService}.
 *
 * @author Paul Chapman
 */
@Controller
public class WebStockController {

    @Autowired
    protected WebStockService stockService;

    protected Logger logger = Logger.getLogger(WebStockController.class
            .getName());

    public WebStockController(WebStockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping("/stock")
    public String goHome() {
        return "index";
    }

    @RequestMapping("/stock/{stockName}")
    public String byStockName(Model model,
                           @PathVariable("stockName") String stockName) {

        logger.info("web-service byStockName() invoked: " + stockName);
        Stock stock = stockService.findByStockName(stockName);
        logger.info("web-service byStockName() found: " + stockName);
        model.addAttribute("stock", stock);
        return "stock";
    }
}
