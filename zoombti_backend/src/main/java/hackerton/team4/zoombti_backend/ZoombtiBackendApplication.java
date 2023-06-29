package hackerton.team4.zoombti_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@SpringBootApplication
public class ZoombtiBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZoombtiBackendApplication.class, args);
	}

}
