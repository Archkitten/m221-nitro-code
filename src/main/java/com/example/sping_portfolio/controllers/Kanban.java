package com.example.sping_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class Kanban {
    @GetMapping("/kanban")
    // CONTROLLER handles GET request for /kanban, maps it to kanban() and does variable bindings
    public String login() {
        return "kanban"; // returns HTML VIEW (kanban)
    }
}

