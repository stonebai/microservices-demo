package io.pivotal.microservices.stock;

import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.StringBuilder;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.ui.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by elliottktan on 4/17/16.
 */
@Entity
public class Stock implements Serializable {

    @Id
    protected Long id;

    private static final String TEMPLATE =
            "http://dev.markitondemand.com/Api/v2/Quote/json?symbol=%s";

    protected String name;
    protected String symbol;
    protected BigDecimal price;
    protected BigDecimal change;
    protected BigDecimal changePercent;

    static protected Logger logger = Logger.getLogger(Stock.class
            .getName());

    @JsonCreator
    public Stock(@JsonProperty("name") String name,
                   @JsonProperty("symbol") String symbol,
                   @JsonProperty("price") BigDecimal price,
                   @JsonProperty("change") BigDecimal change,
                   @JsonProperty("changePercent") BigDecimal changePercent) {
        id = new Long(0);
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.change = change;
        this.changePercent = changePercent;
    }

    public long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
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

    public BigDecimal getChange() {
        return change;
    }

    public BigDecimal getChangePercent()  {
        return changePercent;
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

    protected void setChange(BigDecimal change) {
        this.change = change;
    }

    protected void setChangePercent(BigDecimal changePercent) {
        this.changePercent = changePercent;
    }

    public static Stock findByStockName(String stockName) {

        String url = String.format(TEMPLATE, stockName);

        while(true) {
            try {
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                int status = con.getResponseCode();
                if(status==200) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    br.close();
                    JSONObject res = new JSONObject(sb.toString());
                    String name = res.getString("Name");
                    String symbol = res.getString("Symbol");
                    BigDecimal price = res.getBigDecimal("LastPrice");
                    BigDecimal change = res.getBigDecimal("Change");
                    BigDecimal changePercent = res.getBigDecimal("ChangePercent");
                    Stock ret = new Stock(name, symbol, price, change, changePercent);
                    return ret;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s [%s]: $%.2f", name, symbol, price);
    }
}
