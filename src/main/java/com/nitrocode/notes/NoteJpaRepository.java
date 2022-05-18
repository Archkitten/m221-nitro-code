package com.nitrocode.notes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteJpaRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByPerson(Person p);
}
