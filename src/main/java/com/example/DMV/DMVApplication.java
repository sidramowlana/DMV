package com.example.DMV;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DMVApplication {

    public static void main(String[] args) {
        SpringApplication.run(DMVApplication.class, args);
        System.out.println("Started DMV");
    }

}
