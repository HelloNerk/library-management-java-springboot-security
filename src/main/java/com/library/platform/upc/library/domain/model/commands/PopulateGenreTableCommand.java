package com.library.platform.upc.library.domain.model.commands;

public class PopulateGenreTableCommand {

    private final Long id;

    private final String genre;

    public PopulateGenreTableCommand(Long id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }
}
