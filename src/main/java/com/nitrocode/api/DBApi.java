
package com.nitrocode.api;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
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

        String token = sessionToken.getSessionToken();

        try {
            sessionToken.set(username, token);
        } catch(DBExpection e) {
            return "{ \"status\": 401,\"message\": \"" + e.getMessage() + "\" }";
        }

        Cookie tokenCookie = new Cookie("token", token);
        tokenCookie.setMaxAge(Integer.MAX_VALUE);

        // set tokenCookies domain to current domain
        // get request domain
        tokenCookie.setDomain(request.getServerName());
        System.out.println(request.getServerName());
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
        Model model) {

        // logout
        // try {
        //     // Database.logout(username);
        //     // todo
        // } catch(DBExpection e) {
        //     return "{ \"code\": 401,\"message\": \" " + e.getMessage() + "\" }";
        // }

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
                "user",
                nickname
            );
        } catch(DBExpection e) {
            return "{ \"status\": 409,\"message\": \"" + e.getMessage() + "\" }";
        }

        return "{ \"status\": 200,\"message\": \"user created successfully\" }";
    }

}
