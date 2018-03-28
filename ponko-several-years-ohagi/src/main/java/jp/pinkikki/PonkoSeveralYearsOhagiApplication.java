package jp.pinkikki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class PonkoSeveralYearsOhagiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PonkoSeveralYearsOhagiApplication.class, args);
	}

	@GetMapping(path = "/")
	public String index() {
		return "ohagi";
	}
}
