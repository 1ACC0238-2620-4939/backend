package com.trakto.traktoroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TraktoRouteApplication {

    public static void main(String[] args) {
        SpringApplication.run(TraktoRouteApplication.class, args);
    }

}
