package io.pivotal.microservices.services.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 *
 * Location DTO - used to interact with the {@link WebLocationService}.

 * Created by alicesypark on 4/18/16.
 */

@JsonRootName("Location")
public class Location {
    protected double longitude;
    protected double latitude;
    protected String formatted_address;

    @JsonCreator
    public Location(@JsonProperty("longitude") double longitude,
                    @JsonProperty("latitude") double latitude,
                    @JsonProperty("formatted_address") String formatted_address) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.formatted_address = formatted_address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    @Override
    public String toString() {
        return String.format("%s [%.2f]: $%.2f", formatted_address, longitude, latitude);
    }
}
