package com.example.sping_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class Login {
    @GetMapping("/login")
    // CONTROLLER handles GET request for /greeting, maps it to greeting() and does variable bindings
    public String login() {
        return "login"; // returns HTML VIEW (login)
    }
}

