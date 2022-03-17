package com.example.sping_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.Base64Utils;

import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Math;
import java.math.BigDecimal;
import java.util.HashMap;

import com.example.sping_portfolio.algorithm.*;

@Controller
public class AboutJonathan {

    @GetMapping("/aboutJonathan")
    public String jonathan(@RequestParam(name="name", required=false, defaultValue="World") String name, @RequestParam(name="x", required=false) String sX, @RequestParam(name="y", required=false) String sY, @RequestParam(name="n", required=false) String sN, Model model) {

        if (sX != null && sY != null && sN != null) {
            BigDecimal x = new BigDecimal(sX);
            int y = Integer.parseInt(sY);
            int n = Integer.parseInt(sN);

            SinWhile sinWhile = new SinWhile(x.remainder(BigDecimal.valueOf(Math.PI)), y, n);
            sinWhile.print();
            SinFor sinFor = new SinFor(x.remainder(BigDecimal.valueOf(Math.PI)), y, n);
            sinFor.print();
            SinRecurse sinRecurse = new SinRecurse(x.remainder(BigDecimal.valueOf(Math.PI)), y, n);
            sinRecurse.print();
            SinStream sinStream = new SinStream(x.remainder(BigDecimal.valueOf(Math.PI)), y, n);
            sinStream.print();

            Object[] arr = {
                sinWhile,
                sinFor,
                sinRecurse,
                sinStream,
            };

            model.addAttribute("arr", arr);
        }

        model.addAttribute("name", name); // MODEL is passed to html
        return "aboutJonathan";
    }

    @PostMapping("/jUploadFile")
    public ModelAndView jUploadFile(@RequestParam(required = false) MultipartFile file) throws IOException {
        byte[] a = file.getBytes();
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(a));
        int width = img.getWidth();
        int height = img.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color imgRGB = new Color(img.getRGB(x, y));
                int red = imgRGB.getRed();
                int green = imgRGB.getGreen();
                int blue = imgRGB.getBlue();
                red *= 0.299;
                green *= 0.587;
                blue *= 0.114;

                int p = red + green + blue;

                // Greenscale:
                // int gsRGB = new Color(red, green, blue).getRGB();

                int gsRGB = new Color(p, p, p).getRGB();
                img.setRGB(x, y, gsRGB);
            }
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(img, "PNG", out);

        byte[] bytes = out.toByteArray();
        String b64 = Base64Utils.encodeToString(bytes);
        System.out.println(b64);

        ModelAndView modelAndView = new ModelAndView("aboutJonathan");
        modelAndView.addObject("b64", b64);

        return modelAndView;

    }
}
