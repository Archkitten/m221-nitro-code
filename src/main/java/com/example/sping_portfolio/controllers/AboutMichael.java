package com.example.sping_portfolio.controllers;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.*;

// image stuff
import javax.imageio.ImageIO;
import java.io.UncheckedIOException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.awt.image.*;
import java.awt.Image;
import java.util.Base64;
import java.io.ByteArrayInputStream;

// minilabs
import com.example.sping_portfolio.MichaelMinilabs.checkPasswordWhile;
import com.example.sping_portfolio.MichaelMinilabs.checkPasswordRecursive;
import com.example.sping_portfolio.MichaelMinilabs.checkPasswordFor;
import com.example.sping_portfolio.MichaelMinilabs.checkPasswordInputStream;
import com.example.sping_portfolio.MichaelMinilabs._checkPassword;

import java.util.ArrayList;
import java.util.List;

@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class AboutMichael {
    public List<_checkPassword> cpInit(String password) {
        //Fibonacci objects created with different initializers
        List<_checkPassword> cpList = new ArrayList<>();
        cpList.add(new checkPasswordFor(password));
        cpList.add(new checkPasswordWhile(password));
        cpList.add(new checkPasswordRecursive(password));
        cpList.add(new checkPasswordInputStream(password));

        return cpList;
    }

    @GetMapping("/aboutMichael")
    // CONTROLLER handles GET request for /greeting, maps it to greeting() and does variable bindings
    public String michael(@RequestParam(name="name", required=false, defaultValue="World") String name, @RequestParam(name="password", required=false, defaultValue="") String password, Model model) {
        // @RequestParam handles required and default values, name and model are class variables, model looking like JSON

        model.addAttribute("name", name); // MODEL is passed to html

        model.addAttribute("cpList", cpInit(password));
	
        // List<_checkPassword> lol = cpInit(password);
        // System.out.println(lol.get(0).getTimeElapsed());
        // System.out.println(new checkPasswordFor("lmao").isInWordlists());
        // System.out.println(lol.get(2).isInWordlists());
        // System.out.println(String.valueOf(lol.get(3).isInWordlists()));
        return "aboutMichael";
    }

    @PostMapping("/uploadfile")
    @ResponseBody
    public String michaelPost(@RequestParam(name="imageData", required=true) MultipartFile imageData) {
        // try to write test 
        // try {
        //     FileWriter fff = new FileWriter("C:\\Users\\le139\\Desktop\\fuckyou.txt");
        //     fff.write(greyScaleToPNG(imageData));
        //     fff.close();
        // } catch (IOException e) {
        //     System.out.println("An error occurred.");
        //     e.printStackTrace();
        // }

        
        return greyScaleToPNG(imageData);
    }

    private static String greyScaleToPNG(MultipartFile imageData) {
        int[] rgbArray;
        int imgWidth, imgHeight;
        BufferedImage img;

        try {
            img = createImageFromBytes(imageData.getBytes());

            imgWidth = img.getWidth();
            imgHeight = img.getHeight();
            rgbArray = new int[imgWidth * imgHeight];

            img.getRGB(0, 0, imgWidth, imgHeight, rgbArray, 0, imgWidth);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "Not an image";
        }

        // grey scale althrithm

        for (int i = 0; i < rgbArray.length; i++) {
            int rgb = rgbArray[i];

            int linear = (int)(((rgb >> 16)  & 0xFF) * 0.2126 + //red
                         ((rgb >>  8)  & 0xFF) * 0.7152 + //green
                         ((rgb      )  & 0xFF) * 0.0722);  //blue
            int p = (rgb  & 0xFF000000) | (linear << 16) | (linear << 8) | (linear);
            img.setRGB(i % imgWidth, i / imgWidth, p);
        }

        return imgToBase64String(img, "png");
    }

    public static Image arrayToImage(int[] pixels, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = (WritableRaster) image.getData();
        raster.setPixels(0,0,width,height,pixels);
        return image;
    }

    public static String imgToBase64String(final RenderedImage img, final String formatName) {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            ImageIO.write(img, formatName, os);
            return Base64.getEncoder().encodeToString(os.toByteArray());
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }
    private static BufferedImage createImageFromBytes(byte[] imageData) {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        try {
            return ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

