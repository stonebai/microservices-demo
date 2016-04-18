package io.pivotal.microservices.services.web;

/**
 * Created by elliottktan on 4/17/16.
 */
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.math.BigDecimal;

/**
 * Stock DTO - used to interact with the {@link WebStockService}.
 *
 * @author Elliott Tan
 */
@JsonRootName("Stock")
public class Stock {

    protected String name;
    protected String symbol;
    protected BigDecimal price;

    @JsonCreator
    public Stock(@JsonProperty("name") String name,
                   @JsonProperty("symbol") String symbol,
                   @JsonProperty("price") BigDecimal price) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    protected void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s [%s]: $%.2f", name, symbol, price);
    }

}
