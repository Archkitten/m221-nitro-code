package com.nitrocode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class typingController {
    @GetMapping("/typing")

    public String type(Model model) {
        String[] texts = {"public class Main {\npublic static void main(String[] args) {\nSystem.out.println('Hello World');\n}\n}",
                "System.out.println('Hello World!');",
                "if (20 > 18) {\nSystem.out.println('20 is greater than 18');\n}",
                "for (int i = 0; i < 5; i++) {\nSystem.out.println(i);\n}"};
        int randomNumber = (int)(Math.random() * texts.length);
        String text = texts[randomNumber];

        model.addAttribute("html", text);
        return "typing";
    }
}
