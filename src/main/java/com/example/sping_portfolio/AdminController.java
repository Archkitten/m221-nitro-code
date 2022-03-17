package com.example.sping_portfolio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.example.sping_portfolio.model.UserTaskE;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
public class AdminController {
    @Autowired
    public InMemoryUserDetailsManager inMemoryUserDetailsManager;
    @Autowired
    public PasswordEncoder passwordEncoder;

    @GetMapping("adminCheck")
    public String checkUser(@RequestParam(name = "username") String username, Model model) {
        boolean flag = inMemoryUserDetailsManager.userExists(username);
        String result;
        if (flag) {
            result = "\"" + username + "\" existed!";
            result += "roles: " + inMemoryUserDetailsManager.loadUserByUsername(username).getAuthorities();
        } else
            result = "\"" + username + "\" does not exist in InMemoryUserDetailsManager";

        model.addAttribute("result", result);
        return "adminPage";
    }

    private boolean ifUserExists(String username) {
        return inMemoryUserDetailsManager.userExists(username);
    }


    @PostMapping("/adminAdd")
    public String addUser(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password,
                          @RequestParam(name = "role") String role, Model model) {
        System.out.println("  ### need add a user: " + username + "/" + password + "/" + role);
        if (ifUserExists(username)) {
            model.addAttribute("userExists", true);
        } else {
            String roleStr = role.toUpperCase();
            if (!roleStr.startsWith("ROLE_")) {
                roleStr = "ROLE_" + roleStr;
            }
            ArrayList<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
            grantedAuthoritiesList.add(new SimpleGrantedAuthority(roleStr));
            inMemoryUserDetailsManager
                    .createUser(new UserTaskE(username, passwordEncoder.encode(password), grantedAuthoritiesList));
            model.addAttribute("result", "user " + username + " was added");
        }
        return "adminPage";

    }

    @PostMapping("/adminDelete")
    public String deleteUser(@RequestParam("username") String username) {
        inMemoryUserDetailsManager.deleteUser(username);
        return "adminPage";
    }

    @PostMapping("/adminUpdate")
    public String updateUser(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password,
                             @RequestParam(name = "role") String role, Model model) {
        String result;
        if (ifUserExists(username)) {
            ArrayList<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
            grantedAuthoritiesList.add(new SimpleGrantedAuthority(role));
            inMemoryUserDetailsManager
                    .updateUser(new UserTaskE(username, passwordEncoder.encode(password), grantedAuthoritiesList));
            result = "\"" + username + "\" has been updated successfully!";
        }
        else {
            result = "\"" + username + "\" does not exist in InMemoryUserDetailsManager";
        }
        model.addAttribute("result", result);
        return "adminPage";
    }

}