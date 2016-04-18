package io.pivotal.microservices.services.web;

/**
 * Created by baishi on 4/17/16.
 */
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.math.BigDecimal;

/**
 * Weather DTO - used to interact with the {@link WebWeatherService}.
 *
 * @author Shi Bai
 */
@JsonRootName("Weather")
public class Weather {

    protected String location;
    protected String description;
    protected BigDecimal temperature;

    @JsonCreator
    public Weather(@JsonProperty("location") String location,
                   @JsonProperty("description") String description,
                   @JsonProperty("temperature") BigDecimal temperature) {
        this.location = location;
        this.description = description;
        this.temperature = temperature;
    }

    public String getLocation() {
        return location;
    }

    protected void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    protected void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return String.format("%s [%s]: $%.2f", location, description, temperature);
    }

}
