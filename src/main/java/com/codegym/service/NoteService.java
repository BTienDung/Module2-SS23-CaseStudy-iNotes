package com.codegym.service;

import com.codegym.model.Note;
import com.codegym.model.NoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteService {
    Note findById(Long id);
    Page<Note> findAll(Pageable pageable);
    void save(Note note);
    void delete(Long id);
    Iterable<Note> findByNoteType(NoteType noteType);
}
