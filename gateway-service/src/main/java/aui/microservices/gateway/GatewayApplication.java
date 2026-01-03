package aui.microservices.gateway; // <--- Upewnij się, że ta nazwa pakietu pasuje do twoich folderów!

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	// --- TO JEST TA CZĘŚĆ, KTÓREJ BRAKUJE ---
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				// Konfiguracja dla Reżyserów (Port 8081)
				.route("director-service", r -> r.path("/api/directors/**")
						.uri("http://localhost:8081"))

				// Konfiguracja dla Filmów (Port 8082)
				.route("movie-service", r -> r.path("/api/movies/**")
						.uri("http://localhost:8082"))
				.build();
	}
}