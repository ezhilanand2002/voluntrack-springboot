package com.voluntrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.voluntrack.entity")
@EnableJpaRepositories(basePackages = "com.voluntrack.repository")
public class VolunTrackApplication {
    public static void main(String[] args) {
        SpringApplication.run(VolunTrackApplication.class, args);
        System.out.println("\n=======================================================");
        System.out.println("VolunTrack Backend Server Started Successfully!");
        System.out.println("Open Frontend Application at: http://localhost:8080");
        System.out.println("=======================================================\n");
    }
}

