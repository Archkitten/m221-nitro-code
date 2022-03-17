package com.example.sping_portfolio.controllers;

import com.example.sping_portfolio.model.ImageInfo;
import com.example.sping_portfolio.model.ImageInfo2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.Integer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class AsciiArt {
    @GetMapping("/asciiArt")
    // CONTROLLER handles GET request for /greeting, maps it to greeting() and does variable bindings
    public String asciiart() {
        return "asciiArt";
    }

    // public char[] charList = {' ', '.', ',', ';', '}', '+', '=', '$', '&', '%', '#', '@'};
    // public char[] charList = {'@','#','B','Q','8','g','$','&','0','D','E','R','9','6','N','O','d','b','Z','M','5','%','S','p','q','A','W','G','H','P','3','a','K','f','m','e','s','U','4','h','I','F','X','o','j','C','t','z','k','2','J','w','y','V','c','n','u','1','l','T','{','}','Y','7','L','i','[',']','x','v','\\','/','|','?','(',')','r','*','^','<','+','>',';','~','=','!',':','"',',','_','-','.','\'','`',' '}; 
    public char[] charList = {' ', '`', '\'', '.', '-', '_', ',', '"', ':', '!', '=', '~', ';', '>', '+', '<', '^', '*', 'r', ')', '(', '?', '|', '/', '\\', 'v', 'x', ']', '[', 'i', 'L', '7', 'Y', '}', '{', 'T', 'l', '1', 'u', 'n', 'c', 'V', 'y', 'w', 'J', '2', 'k', 'z', 't', 'C', 'j', 'o', 'X', 'F', 'I', 'h', '4', 'U', 's', 'e', 'm', 'f', 'K', 'a', '3', 'P', 'H', 'G', 'W', 'A', 'q', 'p', 'S', '%', '5', 'M', 'Z', 'b', 'd', 'O', 'N', '6', '9', 'R', 'E', 'D', '0', '&', '$', 'g', '8', 'Q', 'B', '#', '@'};
    @PostMapping("/asciiArt")
    @ResponseBody
    public String asciiUpload(
            @RequestParam(name="inputFile", required = true) MultipartFile imageData,
            @RequestParam(name="swidth", required = true) String swidth,
            @RequestParam(name="sheight", required = true) String sheight
    ){
        if (imageData == null || swidth == null || sheight == null) {
            return "asciiArt";
        }
        // String filename = file.getOriginalFilename();
        BufferedImage img;
        int[] rgbArray;
        int width, height, imgWidth, imgHeight;
        width = Integer.parseInt(swidth);
        height = Integer.parseInt(sheight);

        try {             
            img = createImageFromBytes(imageData.getBytes());
            imgWidth = img.getWidth();
            imgHeight = img.getHeight();
            rgbArray = new int[imgWidth * imgHeight];
            img.getRGB(0, 0, imgWidth, imgHeight, rgbArray, 0, imgWidth);
        } catch(IOException e) {
            System.out.println(e);
            return e.toString();
        }
        // System.out.println(filename);
        System.out.println("Width: " + width);
        System.out.println("Height: " + height);
        System.out.println("imgWidth: " + imgWidth);
        System.out.println("imgHeight: " + imgHeight);
        System.out.println("test: " + rgbArray[0]);
        // for(int ch : rgbArray) {
        //     System.out.println(ch);
        // }
        
        // smallest pixel cannot be less than 1
        float stepx = Math.max(1.0f, imgWidth / (float) width);
        float stepy = Math.max(1.0f, imgHeight / (float) height);
        if(stepx == 1.0) width = imgWidth;
        if(stepy == 1.0) height = imgHeight;
        char[][] asciiArt = new char[height][width];

        float iy = 0.0f, ix = 0.0f;

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                // asciiArt[y][x] = ConvertToChar(img.getRGB((int) Math.floor(ix), (int) Math.floor(iy)));
                asciiArt[y][x] = ConvertToChar(
                    rgbArray[(int) (Math.floor(iy) * imgWidth) + (int) Math.floor(ix)]
                );
                ix += stepx;
            }
            ix = 0.0f;
            iy += stepy;
        }

        String f = "";

        for(char ch[] : asciiArt) {
            f += new String(ch);
            f += "\n";
        }

        return f;
    }

    /* 
        @description          - Takes in a color value and returns a character
        @param       argb     - alpha, red, green, and blue values stored inside a single integer
        @return               - a character based on the character list (index of darker color will be smaller and vice versa for lighter color pixel)
    */

    private char ConvertToChar(int argb) {
        float lightLevel = grayScale(argb) / 255.0f;

        return charList[(int) Math.floor(lightLevel * (charList.length - 1))];
    }

    int grayScale(int rgb) {
        // int alpha = (rgb >> 24) & 0xFF; // not going to process alpha channel
        int red   = (rgb >> 16) & 0xFF;
        int green = (rgb >>  8) & 0xFF;
        int blue  = (rgb      ) & 0xFF;
        int linear = Math.round(red * 0.2126f + green * 0.7152f + blue * 0.0722f);
        return linear;
    }

    private BufferedImage createImageFromBytes(byte[] imageData) {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        try {
            return ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}