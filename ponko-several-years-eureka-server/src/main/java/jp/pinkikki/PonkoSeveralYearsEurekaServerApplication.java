package jp.pinkikki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class PonkoSeveralYearsEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PonkoSeveralYearsEurekaServerApplication.class, args);
	}
}
