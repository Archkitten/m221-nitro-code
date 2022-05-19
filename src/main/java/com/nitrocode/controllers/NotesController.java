package com.nitrocode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class NotesController {
    @GetMapping("/notes")
    // CONTROLLER handles GET request for /binary, maps it to binary() and does variable bindings
    public String controlFunction() {
        return "notes"; // returns HTML VIEW (binary)
    }
}
