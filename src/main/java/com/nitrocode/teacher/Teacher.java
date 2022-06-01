package com.nitrocode.teacher;

import com.nitrocode.db.DBExpection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.nitrocode.db.Database;

@Controller
public class Teacher {
    @GetMapping("/teacher")
    public String teacher(){
        return "teacher";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam(name = "assignment", defaultValue = "") String text){
        // verify if user is teacher

        // if is teacher store text to database

        // else do nothing


        // parse cookie
        // get username from cookie

        String username = "";
        String role;
        try {
            role = Database.get("user_profiles", username, "role");

        } catch (DBExpection e) {
            System.err.println(e);
            return "Jiggy";
        }

        if(role.equals("teacher")){

        }

        return "Jiggy";
    }

}
