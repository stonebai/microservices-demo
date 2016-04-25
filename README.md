# microservices-demo

Clone it and either load into your favorite IDE or use maven directly.

## Using an IDE

You can run the system in your IDE by running the three servers in order: _RegistrationService_, _AccountsService_ and _WebService_.

As discussed in the Blog, open the Eureka dashboard [http://localhost:1111](http://localhost:1111) in your browser to see that the `ACCOUNTS-SERVICE`, `WEATHER-SERVICE`, `STOCK-SERVICE`, `LOCATION-SERVICE` and `WEB-SERVICE` applications have registered.  Next open the Demo Home Page [http://localhost:3333](http://localhost:3333) in and click one of the demo links.

The `localhost:3333` web-site is being handled by a Spring MVC Controller in the _WebService_ application, but you should also see logging output from other services.

## Command Line

You may find it easier to view the different applications by running them from a command line since you can place the six windows side-by-side and watch their log output

To do this, open six CMD windows (Windows) or six Terminal windows (MacOS, Linux) and arrange so you can view them conveniently.

 1. In each window, change to the directory where you cloned the demo.
 1. In the first window, build the application using `mvn clean package`
 1. In the same window run: `java -jar target/microservice-demo-0.0.1-SNAPSHOT.jar registration`
 1. Switch to the second window and run: `java -jar target/microservice-demo-0.0.1-SNAPSHOT.jar accounts`
 1. Switch to the second window and run: `java -jar target/microservice-demo-0.0.1-SNAPSHOT.jar weather`
 1. Switch to the second window and run: `java -jar target/microservice-demo-0.0.1-SNAPSHOT.jar stock`
 1. Switch to the second window and run: `java -jar target/microservice-demo-0.0.1-SNAPSHOT.jar location`
 1. In the third window run: `java -jar target/microservice-demo-0.0.1-SNAPSHOT.jar web`
 1. In your favorite browser open the same two links: [http://localhost:1111](http://localhost:1111) and [http://localhost:3333](http://localhost:3333)

You should see servers being registered in the log output of the first (registration) window.
As you interact you should logging in the second and third windows.
