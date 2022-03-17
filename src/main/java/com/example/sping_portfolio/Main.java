package com.example.sping_portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Main {

    public static void main(String[] args) { // The "Strings[] args" means that strings are going to be passed to this method
        SpringApplication.run(Main.class, args);
    }
    // Testing Testing 1 2 3
}
