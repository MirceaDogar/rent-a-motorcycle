package ro.rentamotorcycle.rentamotorcycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebSecurity
@EnableWebMvc
public class RentAMotorcycleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentAMotorcycleApplication.class, args);
	}

}
