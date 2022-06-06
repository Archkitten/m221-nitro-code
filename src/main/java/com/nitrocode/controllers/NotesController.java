package com.nitrocode.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.nitrocode.notes.*;
import com.nitrocode.notes.ScrumViewController;
import com.nitrocode.notes.NoteViewController;

import java.util.List;

@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class NotesController {
    @Autowired
    private NoteJpaRepository noteRepository;
    @Autowired
    private ModelRepository modelRepository;

    @GetMapping("/notes")
    public String notes(@PathVariable("id") Long id, Model model) {

        Person person = modelRepository.get(id);
        List<Note> notes = noteRepository.findAllByPerson(person);
        Note note = new Note();
        note.setPerson(person);

        for (Note n : notes)
            n.setText(NoteViewController.convertMarkdownToHTML(n.getText()));

        model.addAttribute("person", person);
        model.addAttribute("notes", notes);
        model.addAttribute("note", note);
        return "notes";
    }
}
