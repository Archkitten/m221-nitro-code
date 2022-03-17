package com.example.sping_portfolio.controllers;
/* MVC code that shows defining a simple Model, calling View, and this file serving as Controller
 * Web Content with Spring MVCSpring Example: https://spring.io/guides/gs/serving-web-con
 */

import com.example.sping_portfolio.model.SQL.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.lang.InterruptedException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Vector;

@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class Leaderboard {
    @GetMapping("/leaderboard")    // CONTROLLER handles GET request for /leaderboard, maps it to leaderboard() and does variable bindings
    public String leaderboard(@RequestParam(name="newRow", required=false, defaultValue="World") String newRow, Model model) throws IOException, InterruptedException, ParseException {
        // @RequestParam handles required and default values, name and model are class variables, model looking like JSON
        model.addAttribute("name", newRow); // MODEL is passed to html
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/sql/people/get"))
            .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("ResponseCode: " + response.statusCode());
        String data = "";
        if (response.statusCode() == 200) {
            System.out.println("Response: " + response.body());
            data = response.body();
        }

        JSONParser parser = new JSONParser();

        List<JSONObject> arr = (List<JSONObject>) parser.parse(data);
        model.addAttribute("arr", arr);

        return "leaderboard"; // returns HTML VIEW (leaderboard)
    }
}
