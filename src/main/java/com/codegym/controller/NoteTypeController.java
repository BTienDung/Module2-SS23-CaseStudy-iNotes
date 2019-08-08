package com.codegym.controller;

import com.codegym.model.NoteType;
import com.codegym.service.NoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoteTypeController {
    @Autowired
    private NoteTypeService noteTypeService;
    @GetMapping("/list-type")
    public ModelAndView showAll(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/noteType/list-type");
        Page<NoteType> noteTypes = noteTypeService.findAll(pageable);
        modelAndView.addObject("NoteType", noteTypes);
        return modelAndView;
    }

    @GetMapping("/create-type")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/noteType/create-type");
        modelAndView.addObject("NoteType", new NoteType());
        return modelAndView;
    }

    @PostMapping("/create-type")
    public ModelAndView createType(@ModelAttribute NoteType noteType){
        ModelAndView modelAndView = new ModelAndView("/noteType/create-type");
        noteTypeService.save(noteType);
        modelAndView.addObject("message", "Create Success");
        modelAndView.addObject("NoteType", new NoteType());
        return modelAndView;
    }

    @GetMapping("/edit-type/{id}")
    public ModelAndView formEdit(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/noteType/edit-type");
        modelAndView.addObject("noteType", noteTypeService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit-type")
    public ModelAndView editType(@ModelAttribute NoteType noteType){
        ModelAndView modelAndView = new ModelAndView("/noteType/edit-type");
        noteTypeService.save(noteType);
        modelAndView.addObject("message", "Sửa thành công");
        return modelAndView;
    }
    @GetMapping("/delete-type/{id}")
    public ModelAndView formDelete(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/noteType/delete-type");
        modelAndView.addObject("noteType", noteTypeService.findById(id));
        return modelAndView;
    }

    @PostMapping("/delete-type")
    public ModelAndView deleteNoteType(@ModelAttribute NoteType noteType){
        ModelAndView modelAndView = new ModelAndView("/noteType/delete-type");
        noteTypeService.delete(noteType.getId());
        modelAndView.addObject("message", "Xóa thành công");
        return modelAndView;
    }
}
