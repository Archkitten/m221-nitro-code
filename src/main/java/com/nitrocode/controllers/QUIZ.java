package com.nitrocode.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class QUIZ {
    @GetMapping("/QUIZ")
    // CONTROLLER handles GET request for /binary, maps it to binary() and does variable bindings
    public String controlFunction() {
        return "QUIZ"; // returns HTML VIEW (binary)
    }
}
