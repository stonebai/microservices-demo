package io.pivotal.microservices.location;

import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.StringBuilder;
//import java.math.Double;
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
 * Created by alicesypark on 4/18/16.
 */
@Entity
public class Location implements Serializable {
    @Id
    protected Long id;

    private static final String TEMPLATE =
            "https://maps.googleapis.com/maps/api/geocode/json?address=%s";

    protected double longitude;
    protected double latitude;
    protected String formatted_address;

    static protected Logger logger = Logger.getLogger(Location.class
            .getName());

    @JsonCreator
    public Location(@JsonProperty("longitude") double longitude,
                   @JsonProperty("latitude") double latitude,
                   @JsonProperty("formatted_address") String formatted_address) {
        id = new Long(0);
        this.longitude = longitude;
        this.latitude = latitude;
        this.formatted_address = formatted_address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public static Location findByLocationName(String name) {

        String url = String.format(TEMPLATE, name);

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
                    String jsonString = sb.toString();
                    logger.info("HERE - " + jsonString);
                    JSONObject res = new JSONObject(jsonString);
                    JSONArray results = res.getJSONArray("results");

                    String address = results.getJSONObject(0).getString("formatted_address");
                    JSONObject geometry = results.getJSONObject(0).getJSONObject("geometry");
                    JSONObject loc = geometry.getJSONObject("location");
                    double longitude = loc.getDouble("lng");
                    double latitude = loc.getDouble("lat");

                    Location ret = new Location(longitude, latitude, address);

                    return ret;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f: $%.2f", formatted_address, longitude, latitude);
    }
}
