package com.codegym.controller;

import com.codegym.model.Note;
import com.codegym.model.NoteType;
import com.codegym.service.NoteService;
import com.codegym.service.NoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoteController {
    @Autowired
    private NoteTypeService noteTypeService;
    @ModelAttribute("noteTypes")
    public Page<NoteType> noteTypes(Pageable pageable){
        return noteTypeService.findAll(pageable);
    }
    @Autowired
    private NoteService noteService;
    @GetMapping("/create-note")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/note/create");
        modelAndView.addObject("note", new Note());
        return modelAndView;
    }
    @PostMapping("/create-note")
    public ModelAndView createNote(@ModelAttribute Note note){
        ModelAndView modelAndView = new ModelAndView("/note/create");
        modelAndView.addObject("message", "Create success!");
        noteService.save(note);
        return modelAndView;
    }

    @GetMapping("/list-note")
    public ModelAndView showAll(@PageableDefault(size = 2) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/note/list");
        Page<Note> notes = noteService.findAll(pageable);
        modelAndView.addObject("notes", notes);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/note/edit");
        Note note = noteService.findById(id);
        modelAndView.addObject("note", note);
        return modelAndView;
    }

    @PostMapping("/edit-note")
    public ModelAndView editNote(@ModelAttribute Note note){
        ModelAndView modelAndView = new ModelAndView("/note/edit");
        noteService.save(note);
        modelAndView.addObject("message", "Edit success!");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/note/delete");
        Note note = noteService.findById(id);
        modelAndView.addObject("note", note);
        return modelAndView;
    }

    @PostMapping("/delete-note")
    public ModelAndView delete(@ModelAttribute Note note, Pageable pageable){
        noteService.delete(note.getId());
        Page<Note> notes = noteService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/note/list");
        modelAndView.addObject("message", "Delete success");
        modelAndView.addObject("notes", notes);
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/note/detail");
        Note note = noteService.findById(id);
        modelAndView.addObject("note", note);
        return modelAndView;
    }

    @GetMapping("/search-note")
    public ModelAndView search(@RequestParam("noteType") String name, Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/note/search");
        NoteType noteType = noteTypeService.findByName(name);
        Iterable<Note> notePage = noteService.findByNoteType(noteType);
        modelAndView.addObject("notes", notePage);
        return modelAndView;
    }
}
