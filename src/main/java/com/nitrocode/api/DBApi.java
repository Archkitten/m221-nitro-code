
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
        Model model) {

        // login
        try {
            // Database.login(username, password);
            String hashedPassword = Database.get("user_profiles", username, "password");

            if(!Hashing.checkPassword(password, hashedPassword)) {
                return "{ \"status\": 401,\"message\": \"login failed\" }";
            }

        } catch(DBExpection e) {
            return "{ \"status\": 401,\"message\": \"" + e.getMessage() + "\" }";
        }

        return "{ \"status\": 200,\"message\": \"login successful\" }";
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

        return "{ \"status\": 201,\"message\": \"user created successfully\" }";
    }

}
