package com.example.sping_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class SinAlgorithm {
    @GetMapping("/SinAlgorithm")    // CONTROLLER handles GET request for /greeting, maps it to greeting() and does variable bindings
    public String binary(@RequestParam(name="angle", required=false, defaultValue="0.0") double angle , Model model) {
        // @RequestParam handles required and default values, name and model are class variables, model looking like JSON
        model.addAttribute("myAnswer", sin(angle)); // MODEL is passed to html
        model.addAttribute("angle", angle); // MODEL is passed to html
        return "SinAlgorithm"; // returns HTML VIEW (greeting)
    }

    public int factorial(int n) {
        int result = 1;
        for(; n > 0; n--) {

            result *= n;
        }
        return result;
    }

    public double sin(double angle) {
        // angle has to be in radian
        double PI = 3.14;
        int percition = 6;
        angle = angle % ( 2 * PI ); // a very stupid way to do bc its going to slow down the algorithm
        double result = 0;

        for(int i = 0; i < percition; i++) {
            result += Math.pow(-1, i) * Math.pow(angle, 1 + i * 2) / (double)(factorial(2 * i + 1));
        }
        return result;
    }
}

