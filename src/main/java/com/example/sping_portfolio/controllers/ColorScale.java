package com.example.sping_portfolio.controllers;

import com.example.sping_portfolio.model.ImageInfo;
import com.example.sping_portfolio.model.ImageInfo2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class ColorScale {
    @GetMapping("/colorScale")
    // CONTROLLER handles GET request for /greeting, maps it to greeting() and does variable bindings
    public String colorscale() {return "colorScale";}

    @PostMapping("/colorScale")
    @ResponseBody
    public String colorUpload(
            Model model,
            @RequestParam(required = false) MultipartFile file,
            @RequestParam(value="color", required = true) String action
            ) throws IOException {
        List<ImageInfo2> lii = new ArrayList<>();
        String filename = file.getOriginalFilename();
        double rScale = 1.0;
        double gScale = 1.0;
        double bScale = 1.0;
        switch(action) {
            case "gray":
                rScale = 0.299;
                gScale = 0.587;
                bScale = 0.114;
                break;
            case "red":
                rScale = 1.0;
                gScale = 0.25;
                bScale = 0.25;
                break;
            case "indigo":
                rScale = 0.25;
                gScale = 0.25;
                bScale = 1.0;
                break;
            case "green":
                rScale = 0.25;
                gScale = 1.0;
                bScale = 0.25;
                break;
            case "yellow":
                rScale = 1.0;
                gScale = 1.0;
                bScale = 0.25;
                break;
            case "cyan":
                rScale = 0.25;
                gScale = 1.0;
                bScale = 1.0;
                break;
            case "magenta":
                rScale = 1.0;
                gScale = 0.25;
                bScale = 1.0;
                break;
            case "orange":
                rScale = 1.0;
                gScale = 0.70;
                bScale = 0.25;
                break;
            case "blue":
                rScale = 0.25;
                gScale = 0.60;
                bScale = 1.0;
                break;
            case "purple":
                rScale = 0.6;
                gScale = 0.25;
                bScale = 1.0;
                break;
            default:
                break;
        }
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
                red *= rScale;
                green *= gScale;
                blue *= bScale;
                int p = 1;
                int gsRGB = 1;

                switch(action) {
                    case "gray":
                        p = red + green + blue;
                        gsRGB = new Color(p, p, p).getRGB();
                        img.setRGB(x, y, gsRGB);
                        break;
                    case "red":
                    case "blue":
                    case "green":
                    case "yellow":
                    case "cyan":
                    case "purple":
                    case "orange":
                    case "magenta":
                    case "indigo":
                        gsRGB = new Color(red, green, blue).getRGB();
                        img.setRGB(x, y, gsRGB);
                        break;
                    default:
                        break;
                }
            }
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(img, "PNG", out);

        byte[] bytes = out.toByteArray();
        String b64 = Base64Utils.encodeToString(bytes);

        lii.add(new ImageInfo2(bytes, filename, 12));
        for (int ii=0;ii<lii.size();ii++) {lii.get(ii).read_image();}
        model.addAttribute("lii", lii);

        return b64;

    }
}
