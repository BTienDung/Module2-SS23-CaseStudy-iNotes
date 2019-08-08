package com.codegym.service;

import com.codegym.model.Note;
import com.codegym.model.NoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteTypeService {
    NoteType findById(Long id);
    Page<NoteType> findAll(Pageable pageable);
    void save(NoteType noteType);
    void delete(Long id);
    NoteType findByName(String name);
}
