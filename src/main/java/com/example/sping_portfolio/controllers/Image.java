package com.example.sping_portfolio.controllers;

import com.example.sping_portfolio.model.ImageStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class Image {
     @Autowired
     private ImageStorage imageStorage;

    @GetMapping("/image")
    public String image(Model model)  {

        model.addAttribute("lii", imageStorage.getLii());
        return "image";
    }

}