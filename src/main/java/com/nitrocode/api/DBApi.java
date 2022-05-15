
package com.nitrocode.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

import com.nitrocode.db.Database;
import com.nitrocode.db.DBExpection;

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

    // @PostMapping("/api/login")
    
}
