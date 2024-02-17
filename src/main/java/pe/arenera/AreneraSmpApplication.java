package pe.arenera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// DEPENDENCIA AGREGADA (exclude = SecurityAutoConfiguration.class)
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AreneraSmpApplication {

	public static void main(String[] args) {
		SpringApplication.run(AreneraSmpApplication.class, args);
	}

}
