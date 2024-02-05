package com.project.lotus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class LotusApplication {

    public static void main(String[] args) {
        SpringApplication.run(LotusApplication.class, args);
    }

}
