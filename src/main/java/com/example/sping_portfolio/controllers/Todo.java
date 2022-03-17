package com.example.sping_portfolio.controllers;

import com.example.sping_portfolio.model.SQL.Person;
import com.example.sping_portfolio.model.UserTaskE;
import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class Todo {
    @GetMapping("/todo")
    // CONTROLLER handles GET request for /greeting, maps it to greeting() and does variable bindings
    public String todo() {
        return "todo"; // returns HTML VIEW (leaderboard)
    }

    @PostMapping("/todoPost")
    @ResponseBody
    public void storeTasks(
            Model model,
            @RequestParam(required = false) String TTitle,
            @RequestParam(required = false) String TContents,
            @RequestParam(required = false) String TDate) throws ParseException, IOException {
        System.out.println(TTitle);
        System.out.println(TContents);
        Date TDateAsDate = stringToDate(TDate);
        long TDateUnix = TDateAsDate.getTime();
        System.out.println(TDateUnix);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() != "anonymousUser") {
            User currentUser = (User) authentication.getPrincipal();
            System.out.println(currentUser.getUsername());
        }

        JSONObject task = new JSONObject();
        task.put("title", TTitle);
        task.put("content", TContents);
    }

    public Date stringToDate(String sdate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(sdate);
        return date;
    }

    public void addTask(@Valid UserTaskE user, JSONObject task, long unixDate) {
        user.addTask(task, unixDate);
    }
}