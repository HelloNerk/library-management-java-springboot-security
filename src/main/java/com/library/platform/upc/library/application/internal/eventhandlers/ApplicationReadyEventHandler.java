package com.library.platform.upc.library.application.internal.eventhandlers;

import com.library.platform.upc.library.domain.model.commands.PopulateGenreTableCommand;
import com.library.platform.upc.library.domain.model.entities.Genre;
import com.library.platform.upc.library.infrastructure.persistence.jpa.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("libraryApplicationReadyEventHandler")
public class ApplicationReadyEventHandler {

    private static final List<PopulateGenreTableCommand>INITIAL_GENRE = List.of(
            new PopulateGenreTableCommand(1L, "FICTION"),
            new PopulateGenreTableCommand(2L, "NON-FICTION"),
            new PopulateGenreTableCommand(3L, "SCIENCE"),
            new PopulateGenreTableCommand(4L, "FANTASY"),
            new PopulateGenreTableCommand(5L, "MYSTERY")
    );

    @Autowired
    private GenreRepository genreRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void populateGenreTable() {
        for (PopulateGenreTableCommand command : INITIAL_GENRE) {
            if (!genreRepository.existsById(command.getId())) {
                Genre genre = new Genre(command.getGenre());
                genre.setId(command.getId());
                genreRepository.save(genre);
            }
        }
    }
}
