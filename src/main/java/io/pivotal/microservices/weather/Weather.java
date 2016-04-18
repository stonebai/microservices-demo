package io.pivotal.microservices.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
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
 * Created by baishi on 4/17/16.
 */
@Entity
public class Weather implements Serializable {

    @Id
    protected Long id;

    private static final String TEMPLATE =
            "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=40ffe49d8e33ecc0112e7d14f117d9fa";

    protected String location;
    protected String description;
    protected BigDecimal temperature;

    @JsonCreator
    public Weather(@JsonProperty("location") String location,
                   @JsonProperty("description") String description,
                   @JsonProperty("temperature") BigDecimal temperature) {
        id = new Long(0);
        this.location = location;
        this.description = description;
        this.temperature = temperature;
    }

    public long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    protected void setLocation(String location) {
        this.location = location;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public static Weather findByCityName(String cityName) {

        String url = String.format(TEMPLATE, cityName);

        while(true) {
            try {
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                int status = con.getResponseCode();
                if(status==200) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String line = br.readLine();
                    br.close();
                    JSONObject res = new JSONObject(line);
                    JSONArray weathers = res.getJSONArray("weather");
                    String description = weathers.getJSONObject(0).getString("description");
                    BigDecimal temperature = res.getJSONObject("main").getBigDecimal("temp").subtract(
                            new BigDecimal(273.15));
                    Weather ret = new Weather(cityName, description, temperature);
                    return ret;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s [%s]: $%.2f", location, description, temperature);
    }
}
