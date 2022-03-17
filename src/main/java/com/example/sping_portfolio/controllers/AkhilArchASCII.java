package com.example.sping_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class AkhilArchASCII {
    @GetMapping("/akhilArchASCII")
    public String akhilArchASCII() {
        return "akhilArchASCII";
    }

    public String[] chars = {" ", ".", ":", "-", "=", "+", "*", "#", "%", "@"};

    @PostMapping("/akhilArchASCII")
    @ResponseBody
    public String upload(
            Model model,
            @RequestParam(name = "inputFile", required = true) MultipartFile imageData,
            @RequestParam(name = "swidth", required = true) String swidth,
            @RequestParam(name = "sheight", required = true) String sheight
    ) {
        String output = null;
        model.addAttribute("output", output);
        return output;
    }


}
