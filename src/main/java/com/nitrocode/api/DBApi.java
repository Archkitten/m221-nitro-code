
package com.nitrocode.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nitrocode.db.Database;
import com.nitrocode.db.DBExpection;
import com.nitrocode.db.Hashing;

@Controller
public class DBApi {

    @PostMapping("/api/adduser")
    @ResponseBody
    public String addUser(
        @RequestParam(name="username", required=true, defaultValue="...") String username, 
        @RequestParam(name="nickname", required=true, defaultValue="...") String nickname,
        @RequestParam(name="password", required=true, defaultValue="...") String password, 
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
            return "{ \"code\": 409,\"message\": \" " + e.getMessage() + "\" }";
        }

        return "{ \"code\": 201,\"message\": \"user created successfully\" }";
    }

    @PostMapping("/api/login")
    @ResponseBody
    public String login(
        @RequestParam(name="username", required=true, defaultValue="...") String username, 
        @RequestParam(name="password", required=true, defaultValue="...") String password, 
        Model model) {

        // login
        try {
            // Database.login(username, password);
            String hashedPassword = Database.get("user_profiles", username, "password");

            if(!Hashing.checkPassword(password, hashedPassword)) {
                return "{ \"code\": 401,\"message\": \"login failed\" }";
            }

        } catch(DBExpection e) {
            return "{ \"code\": 401,\"message\": \" " + e.getMessage() + "\" }";
        }

        return "{ \"code\": 200,\"message\": \"login successful\" }";
    }
    
}
