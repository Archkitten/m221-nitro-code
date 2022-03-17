package com.example.sping_portfolio.controllers;

import com.example.sping_portfolio.model.ImageInfo;
import com.example.sping_portfolio.model.ImageStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class ImageGrayscale {

    @Autowired
    private ImageStorage imageStorage;

    @GetMapping("/image/grayscale")
    public String image_grayscale(Model model) {

        List<ImageInfo> lii = imageStorage.getLii();

        for (int ii = 0; ii<lii.size(); ii++) {
            model.addAttribute("str" + ii, lii.get(ii).grayscale());
        }
        return "image_grayscale";
    }
}