package jp.pinkikki;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
@RestController
public class PonkoSeveralYearsSushiApplication {

    @Autowired
    WebClient.Builder webClientBuilder;

    @Value("${no.one}")
    String noOne;

    public static void main(String[] args) {
        SpringApplication.run(PonkoSeveralYearsSushiApplication.class, args);
    }

    @Bean
    @LoadBalanced
    WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @GetMapping(path = "/")
    public String index() {
        return "sushi";
    }

    @GetMapping(path = "noone")
    public String withNoOne() {
        return noOne;
    }

    public Mono<String> error() {
        return Mono.just("error");
    }

    @HystrixCommand(fallbackMethod = "error")
    @GetMapping(path = "monoohagiandmozuku")
    public Mono<String> withMonoOfOhagiAndMozuku() {
        return get("http://ohagi-app")
                .mergeWith(get("http://mozuku-app"))
                .collect(Collectors.joining("-"));
    }

    @GetMapping(path = "monoohagiandmozuku2")
    public Mono<String> withMonoOfOhagiAndMozuku2() {
        return get("http://ohagi-app")
                .mergeWith(Mono.just("-"))
                .mergeWith(get("http://mozuku-app"))
                .collect(Collectors.joining());
    }

    @GetMapping(path = "monoohagiandmozuku3")
    public Mono<String> withMonoOfOhagiAndMozuku3() {
        return get("http://ohagi-app")
                .flatMap(s -> Mono.just(s + "-"))
                .mergeWith(get("http://mozuku-app"))
                .collect(Collectors.joining());
    }

    @GetMapping(path = "fluxohagiandmozuku")
    public Flux<String> withFluxOfOhagiAndMozuku() {
        return webClientBuilder
                .build()
                .get()
                .uri("http://ohagi-app")
                .retrieve()
                .bodyToMono(String.class)
                .mergeWith(webClientBuilder
                        .build()
                        .get()
                        .uri("http://mozuku-app")
                        .retrieve()
                        .bodyToMono(String.class));
    }

    private Mono<String> get(String uri) {
        return webClientBuilder
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class);
    }


}
