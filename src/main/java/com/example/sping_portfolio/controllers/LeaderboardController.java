package com.example.sping_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class LeaderboardController {
    @GetMapping("/leaderboard")
    // CONTROLLER handles GET request for /binary, maps it to binary() and does variable bindings
    public String controlFunction() {
        return "leaderboard"; // returns HTML VIEW (binary)
    }
}
