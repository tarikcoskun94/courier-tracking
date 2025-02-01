package com.migrosone.couriertracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CourierTrackingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourierTrackingApplication.class, args);
    }

}
