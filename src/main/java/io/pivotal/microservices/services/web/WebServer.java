package io.pivotal.microservices.services.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Accounts web-server. Works as a microservice client, fetching data from the
 * Account-Service. Uses the Discovery Server (Eureka) to find the microservice.
 * 
 * @author Paul Chapman
 */
@SpringBootApplication
@EnableDiscoveryClient
// Disable component scanner ...
@ComponentScan(useDefaultFilters = false)
public class WebServer {

	/**
	 * URL uses the logical name of account-service - upper or lower case,
	 * doesn't matter.
	 */
	public static final String ACCOUNTS_SERVICE_URL = "http://ACCOUNTS-SERVICE";
	public static final String STOCK_SERVICE_URL = "http://STOCK-SERVICE";
	public static final String LOCATION_SERVICE_URL = "http://LOCATION-SERVICE";
	public static final String WEATHER_SERVICE_URL = "http://WEATHER-SERVICE";

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for web-server.properties or web-server.yml
		System.setProperty("spring.config.name", "web-server");
		SpringApplication.run(WebServer.class, args);
	}

	/**
	 * The AccountService encapsulates the interaction with the micro-service.
	 * 
	 * @return A new service instance.
	 */
	@Bean
	public WebAccountsService accountsService() {
		return new WebAccountsService(ACCOUNTS_SERVICE_URL);
	}

	/**
	 * Create the controller, passing it the {@link WebAccountsService} to use.
	 * 
	 * @return
	 */
	@Bean
	public WebAccountsController accountsController() {
		return new WebAccountsController(accountsService());
	}

	/**
	 * The StockService encapsulates the interaction with the micro-service.
	 *
	 * @return A new service instance.
	 */
	@Bean
	public WebStockService stockService() {
		return new WebStockService(STOCK_SERVICE_URL);
	}

	/**
	 * Create the controller, passing it the {@link WebStockService} to use.
	 *
	 * @return
	 */
	@Bean
	public WebStockController stockController() {
		return new WebStockController(stockService());
	}

	/**
	 * The LocationService encapsulates the interaction with the micro-service.
	 *
	 * @return A new service instance.
	 */
	@Bean
	public WebLocationService locationService() {
		return new WebLocationService(LOCATION_SERVICE_URL);
	}

	/**
	 * Create the controller, passing it the {@link WebLocationService} to use.
	 *
	 * @return
	 */
	@Bean
	public WebLocationController locationController() {
		return new WebLocationController(locationService());
	}

	/**
	 * The WeatherService encapsulates the interaction with the micro-service.
	 *
	 * @return A new service instance.
	 */
	@Bean
	public WebWeatherService weatherService() {
		return new WebWeatherService(WEATHER_SERVICE_URL);
	}

	/**
	 * Create the controller, passing it the {@link WebAccountsService} to use.
	 *
	 * @return
	 */
	@Bean
	public WebWeatherController weatherController() {
		return new WebWeatherController(weatherService());
	}

	@Bean
	public HomeController homeController() {
		return new HomeController();
	}
}
