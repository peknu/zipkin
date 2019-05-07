package se.sbab.demo.zipkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableAutoConfiguration
@RestController
@CrossOrigin
public class Frontend {
    private static final String backendBaseUrl = "http://localhost:9000";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/")
    public String callBackend() {
        return restTemplate.getForObject(backendBaseUrl + "/api", String.class);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Frontend.class,
                "--spring.application.name=Frontend",
                "--server.port=8081"
        );
    }
}
