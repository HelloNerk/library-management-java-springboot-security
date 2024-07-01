package com.library.platform.upc.library.domain.model.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String genre;

    public Genre(String genre) {
        this.genre=genre;
    }

    public Genre() {
    }

    public Long getGenreId(){
        return id;
    }

}
