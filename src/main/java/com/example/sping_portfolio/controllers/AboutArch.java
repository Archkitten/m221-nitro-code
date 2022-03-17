package com.example.sping_portfolio.controllers;

import com.example.sping_portfolio.model.ImageInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class AboutArch {
    @GetMapping("/aboutArch")
    // CONTROLLER handles GET request for /greeting, maps it to greeting() and does variable bindings
    public String arch(
            @RequestParam(name="name", required=false, defaultValue="World") String name, Model model,
            @RequestParam(name="number", required=false, defaultValue="0") int number) {
        // @RequestParam handles required and default values, name and model are class variables, model looking like JSON
        model.addAttribute("name", name); // MODEL is passed to html
        model.addAttribute("number", number); // MODEL is passed to html

        List<AlgorithmData> algorithmDataList = new ArrayList<>();
        AlgorithmData algorithmDataFor = halfLifeFor(number);
        algorithmDataList.add(algorithmDataFor);
        AlgorithmData algorithmDataWhile = halfLifeWhile(number);
        algorithmDataList.add(algorithmDataWhile);
        AlgorithmData algorithmDataRecursion = halfLifeRecursion(number);
        algorithmDataList.add(algorithmDataRecursion);
        AlgorithmData algorithmDataStream = halfLifeStream(number);
        algorithmDataList.add(algorithmDataStream);
        model.addAttribute("halfLifeAlgorithmList", algorithmDataList); // MODEL is passed to html

        // RGB
        //String web_server = "http://localhost:8080/";
        String web_server = "https://csa.nighthawkcodingsociety.com";
        List<ImageInfo> lii = new ArrayList<>();

        // Removed all the images to reduce loading time for other projects.
        /*
        String file0 = "/images/Mona_Lisa.png";
        lii.add(new ImageInfo(file0, web_server+file0, 12));
        lii.get(0).read_image();

        String file1 = "/images/bulb_on.gif";
        lii.add(new ImageInfo(file1, web_server+file1, 2));
        lii.get(1).read_image();

        String file2 = "/images/bulb_off.png";
        lii.add(new ImageInfo(file2, web_server+file2, 7));
        lii.get(2).read_image();
        */

        model.addAttribute("lii", lii);

        // Return statement
        return "aboutArch";
    }


    public AlgorithmData halfLifeFor(int n) {
        AlgorithmData algorithmData = new AlgorithmData();
        // The method used is "For Loop"
        algorithmData.setMethod("ForLoop");
        // Define starting value and array
        double radioactivity = 14.0;
        double[] rValues = new double[n];
        // Start timer
        long startTimeInNano = System.nanoTime();
        // For loop
        for(int i = 0; i < n; i++) {
            rValues[i] = radioactivity / Math.pow(2, i);
        }
        // Spent time
        long totalUsedInNano = System.nanoTime() - startTimeInNano;
        // System.out.println(totalUsedInNano);

        // Return result as array OLD CODE
        // return Arrays.toString(rValues);

        // Return result as class
        algorithmData.setTotalTimeInNano(totalUsedInNano);
        algorithmData.setResult(Arrays.toString(rValues));
        return algorithmData;
    }

    public AlgorithmData halfLifeWhile(int n) {
        AlgorithmData algorithmData = new AlgorithmData();
        // The method used is "While Loop"
        algorithmData.setMethod("WhileLoop");
        // Define starting value and array
        double radioactivity = 14.0;
        double[] rValues = new double[n];
        // Start timer
        long startTimeInNano = System.nanoTime();
        // While loop
        int i = 0;
        while(i < n) {
            rValues[i] = radioactivity / Math.pow(2, i);
            i++;
        }
        // Spent time
        long totalUsedInNano = System.nanoTime() - startTimeInNano;
        // System.out.println(totalUsedInNano);
        // Return result as class
        algorithmData.setTotalTimeInNano(totalUsedInNano);
        algorithmData.setResult(Arrays.toString(rValues));
        return algorithmData;
    }

    public AlgorithmData halfLifeRecursion(int n) {
        AlgorithmData algorithmData = new AlgorithmData();
        // The method used is "Recursion"
        algorithmData.setMethod("Recursion");
        // Define starting value and array
        double radioactivity = 14.0;
        double[] rValues = new double[n];
        // Start timer
        long startTimeInNano = System.nanoTime();
        // Recursion
        List<Double> list = new ArrayList<>();
        cutHalf(list, radioactivity, n);
        // Spent time
        long totalUsedInNano = System.nanoTime() - startTimeInNano;
        // System.out.println(totalUsedInNano);
        // Return result as class
        algorithmData.setTotalTimeInNano(totalUsedInNano);
        algorithmData.setResult(list.toString());
        return algorithmData;
    }
    public void cutHalf(List list, double radioactivity, int n) {
        if (n > 0) {
            list.add(radioactivity);
            radioactivity /= 2;
            // System.out.println(n);
            // System.out.println(radioactivity);
            cutHalf(list, radioactivity, n - 1);
        }
        else {
            return;
        }
    }

    public AlgorithmData halfLifeStream(int n) {
        AlgorithmData algorithmData = new AlgorithmData();
        // The method used is "Stream"
        algorithmData.setMethod("Stream");
        // Define starting value and array
        double radioactivity = 14.0;
        double[] rValues;
        // Start timer
        long startTimeInNano = System.nanoTime();
        // For loop -> Light Bulb -> toArray -> Stream (IDE MAGIC!!! :D)
        rValues = IntStream.range(0, n).mapToDouble(i -> radioactivity / Math.pow(2, i)).toArray();
        // Spent time
        long totalUsedInNano = System.nanoTime() - startTimeInNano;
        // System.out.println(totalUsedInNano);
        // Return result as class
        algorithmData.setTotalTimeInNano(totalUsedInNano);
        algorithmData.setResult(Arrays.toString(rValues));
        return algorithmData;
    }

    /*
    public double halfLife(double n) {
        double radioactivity = 14.0 / Math.pow(2, n);
        return radioactivity;
    }
     */

    @Getter
    @Setter
    static class AlgorithmData {
        String method;
        long totalTimeInNano;
        String result;

    }
}

