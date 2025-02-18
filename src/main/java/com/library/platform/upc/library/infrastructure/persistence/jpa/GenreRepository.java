package com.library.platform.upc.library.infrastructure.persistence.jpa;

import com.library.platform.upc.library.domain.model.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    boolean existsById(Long Id);
}
