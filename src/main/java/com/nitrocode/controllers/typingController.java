package com.nitrocode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class typingController {


    @GetMapping("/typing")
    public String greeting(Model model) {

        return "typing";

        /*
        final int numberOfQuestions = 4;

        if (questionId == 0) {
            model.addAttribute("question", "To get started, press enter.");
            // Random integer from 1 to max
            questionId = (int) (Math.floor(Math.random() * numberOfQuestions) + 1);
        }

        else if (questionId == 1) {
            model.addAttribute("question", "What is the BMI of a 6 foot person that weighs 140 pounds? Answer to the nearest tenth.");
            if (UserInputJava.equals("19.0")) {
                model.addAttribute("output", "Correct! Press enter to continue.");
                // Chooses a random questionId, but if they choose the same question, reselect the questionId.
                do {
                    questionId = (int) (Math.floor(Math.random() * numberOfQuestions) + 1);
                }
                while (questionId == 1);
            }
            else {
                model.addAttribute("output", "Wrong!");
            }
        }

        else if (questionId == 2) {
            model.addAttribute("question", "Please type 2");
            if (UserInputJava.equals("2")) {
                model.addAttribute("output", "Correct! Press enter to continue.");
                // Chooses a random questionId, but if they choose the same question, reselect the questionId.
                do {
                    questionId = (int) (Math.floor(Math.random() * numberOfQuestions) + 1);
                }
                while (questionId == 2);
            }
            else {
                model.addAttribute("output", "Wrong!");
            }
        }

        else if (questionId == 3) {
            model.addAttribute("question", "Please type 3");
            if (UserInputJava.equals("3")) {
                model.addAttribute("output", "Correct! Press enter to continue.");
                // Chooses a random questionId, but if they choose the same question, reselect the questionId.
                do {
                    questionId = (int) (Math.floor(Math.random() * numberOfQuestions) + 1);
                }
                while (questionId == 3);
            }
            else {
                model.addAttribute("output", "Wrong!");
            }
        }

        else if (questionId == 4) {
            model.addAttribute("question", "Please type 4");
            if (UserInputJava.equals("4")) {
                model.addAttribute("output", "Correct! Press enter to continue.");
                // Chooses a random questionId, but if they choose the same question, reselect the questionId.
                do {
                    questionId = (int) (Math.floor(Math.random() * numberOfQuestions) + 1);
                }
                while (questionId == 4);
            }
            else {
                model.addAttribute("output", "Wrong!");
            }
        }

        // Returns ... if the user inputs ... which is the default value
        if (UserInputJava.equals("...")) {
            model.addAttribute("output", "...");
        }
        // Return all model attributes to html
        return "freeform";
    */}
}
