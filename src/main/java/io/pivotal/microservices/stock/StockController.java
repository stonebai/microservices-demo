package io.pivotal.microservices.stock;

/**
 * Created by elliottktan on 4/17/16.
 */

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A RESTFul controller for accessing stock information.
 *
 * @author Elliott Tan
 */
@RestController
public class StockController {

    protected Logger logger = Logger.getLogger(StockController.class
            .getName());

    /**
     * Fetch current stock information with the specified stockName.
     *
     * @param stockName
     *            e.g. MSFT, GOOG
     * @return The stock information if found.
     */
    @RequestMapping("/stock/{stockName}")
    public Stock byStockName(@PathVariable("stockName") String stockName) {

        logger.info("stock-service byStockName() invoked: " + stockName);
        Stock stock = Stock.findByStockName(stockName);
        logger.info("stock-service byStockName() found: " + stock);

        return stock;
    }
}

