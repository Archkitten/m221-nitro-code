package com.example.sping_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class Binary {
    @GetMapping("/binary")    // CONTROLLER handles GET request for /greeting, maps it to greeting() and does variable bindings
    public String binary(@RequestParam(name="number", required=false, defaultValue="8") int myBits, Model model) {
        // @RequestParam handles required and default values, name and model are class variables, model looking like JSON
        model.addAttribute("mybit", myBits); // MODEL is passed to html
        return "binary"; // returns HTML VIEW (greeting)
    }
}

