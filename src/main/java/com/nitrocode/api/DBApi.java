
package com.nitrocode.api;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nitrocode.db.DBExpection;
import com.nitrocode.db.Database;
import com.nitrocode.db.Hashing;
import com.nitrocode.sessions.sessionToken;

@Controller
public class DBApi {

    @PostMapping("/api/adduser")
    @ResponseBody
    public String addUser(
        @RequestParam(name="username", required=true) String username, 
        @RequestParam(name="nickname", required=true) String nickname,
        @RequestParam(name="password", required=true) String password, 
        Model model) {

        // add user
        try {
            Database.addUser(
                username, 
                password,
                "user",
                nickname
            );
        } catch(DBExpection e) {
            return "{ \"status\": 409,\"message\": \"" + e.getMessage() + "\" }";
        }

        return "{ \"status\": 201,\"message\": \"user created successfully\" }";
    }

    @PostMapping("/api/login")
    @ResponseBody
    public String login(
        @RequestParam(name="username", required=true) String username, 
        @RequestParam(name="password", required=true) String password, 
        HttpServletResponse response,
        HttpServletRequest request) {

        // do nothing if username and password are empty
        if (username.isEmpty() || password.isEmpty()) {
            return "{ \"status\": 400,\"message\": \"username and password cannot be empty\" }";
        }

        // login
        try {
            // Database.login(username, password);
            String hashedPassword = Database.get("user_profiles", username, "password");

            if(hashedPassword.isEmpty()) {
                return "{ \"status\": 401,\"message\": \"username or password is incorrect\" }";
            } 
            
            if(!Hashing.checkPassword(password, hashedPassword)) {
                return "{ \"status\": 401,\"message\": \"invalid username or password\" }";
            }

        } catch(DBExpection e) {
            return "{ \"status\": 401,\"message\": \"" + e.getMessage() + "\" }";
        }


        // check if sessionToken exists
        String token = sessionToken.get(username);
        if(token == null) {
            token = sessionToken.set(username);
        }

        System.out.println(token);

        Cookie tokenCookie = new Cookie("token", token);
        tokenCookie.setMaxAge(Integer.MAX_VALUE);

        // set tokenCookies domain to current domain
        // get request domain
        tokenCookie.setDomain(request.getServerName());
        // System.out.println(request.getServerName());
        // tokenCookie.setDomain(".localhost");

        response.addCookie(
            tokenCookie
        );

        Cookie usernameCookie = new Cookie("username", username);
        usernameCookie.setMaxAge(Integer.MAX_VALUE);

        response.addCookie(
            usernameCookie
        );

        return "{ \"status\": 200,\"message\": \"login successful\", \"username\": \"" 
                    + username + "\"}";
    }

    @PostMapping("/api/logout")
    @ResponseBody
    public String logout(
        @RequestParam(name="username", required=true, defaultValue="...") String username, 
        HttpServletRequest request) {

        // extract all cookies from request 
        Cookie[] cookies = request.getCookies();

        // get cookie with name "token"
        Cookie tokenCookie = null;
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("token")) {
                tokenCookie = cookie;
            }
        }

        if(tokenCookie == null) {
            return "{ \"status\": 401,\"message\": \"no token cookie found\" }";
        }

        String token = tokenCookie.getValue();

        if (sessionToken.compare(username, token)) {
            sessionToken.remove(username);
        } else {
            return "{ \"status\": 401,\"message\": \"invalid token\" }";
        }

        return "{ \"status\": 200,\"message\": \"logout successful\" }";
    }

    @PostMapping("/api/register")
    @ResponseBody
    public String register(
        @RequestParam(name="username", required=true) String username, 
        @RequestParam(name="nickname", required=true) String nickname,
        @RequestParam(name="password", required=true) String password, 
        Model model) {

        // register
        try {
            Database.addUser(
                username, 
                password,
                "user", // only 2 types of roles can exist: admin and user
                nickname
            );
        } catch(DBExpection e) {
            return "{ \"status\": 409,\"message\": \"" + e.getMessage() + "\" }";
        }

        return "{ \"status\": 200,\"message\": \"user created successfully\" }";
    }

    @PostMapping("/api/speed")
    @ResponseBody
    public String setSpeed(
        @RequestBody String payload,
        HttpServletRequest request) {
            // get cookie
            String username = null, 
                   sessionTokenHash = null;
            Cookie[] cookies = request.getCookies();
            for(Cookie c : cookies) {
                if(c.getName().equals("token")) {
                    sessionTokenHash = c.getValue();
                } else if(c.getName().equals("username")) {
                    username = c.getValue();
                }

            }

            // check username and sessionTokenHash exists
            if(username == null || sessionTokenHash == null) {
                return "{ \"status\": 401,\"message\": \"no token cookie found\" }";
            }

            // check sessionTokenHash is valid
            if(!sessionToken.compare(username, sessionTokenHash)) {
                // return sessionTokenhash and username
                return "{ \"status\": 401,\"message\": \"invalid token\", \"correct\": \"" + sessionToken.get(username)+ "\", \"username\": \"" + username + "\", \"got\": \"" + sessionTokenHash + "\" }";
                // return "{ \"status\": 401,\"message\": \"invalid token\"}";
            }

            // payload is a json string
            // parse json string
            String wpm;
            try {
                JSONObject json = new JSONObject(payload);
                wpm = json.getString("speed");
            } catch(JSONException e) {
                return "{ \"status\": 400,\"message\": \"invalid payload\" }";
            }


            // set speed for user
            try {
                Database.setSpeed(username, wpm);
            } catch(DBExpection e) {
                return "{ \"status\": 401,\"message\": \"" + e.getMessage() + "\" }";
            }

            return "{ \"status\": 200,\"message\": \"speed set successfully\" }";

        }

        @GetMapping("/api/getspeed")
        @ResponseBody
        public String getSpeed(
            HttpServletRequest request) {
                // get speed for user
                try {
                    // get all users' speed
                    ArrayList<String> users = Database.getAllUsers();

                    HashMap<String, String> speeds = new HashMap<String, String>();
                    for(String user : users) {
                        String speed = Database.get("typing_speed", user, "speed");
                        if(!speed.isEmpty()) speeds.put(user, speed);
                    }

                    if(speeds.size() == 0) {
                        return "{ \"status\": 401,\"message\": \"no data found in typing_speed\" }";
                    }

                    return new JSONObject(speeds).toString();
                } catch(DBExpection e) {
                    return "{ \"status\": 401,\"message\": \"" + e.getMessage() + "\" }";
                }

        }

}
