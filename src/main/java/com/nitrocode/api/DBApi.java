
package com.nitrocode.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DBApi {

    @PostMapping("/api/adduser")
    @ResponseBody
    public String addUser(
        @RequestParam(name="username", required=false, defaultValue="...") String username, 
        @RequestParam(name="password", required=false, defaultValue="...") String password, 
        Model model) {
        // model.addAttribute("username", username);
        // model.addAttribute("password", password);
        // model.addAttribute("output", Database.addUser(username, password));

        return "lmao";
    }
    
}
