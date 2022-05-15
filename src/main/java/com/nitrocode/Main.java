package com.nitrocode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.nitrocode.db.Database;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Main {

    public static void main(String[] args) { // The "Strings[] args" means that strings are going to be passed to this method
        // Database.runTests();
        SpringApplication.run(Main.class, args);
    }
    // Testing Testing 1 2 3
}
