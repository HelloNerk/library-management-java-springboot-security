package com.library.platform.upc.library.infrastructure.persistence.jpa;

import com.library.platform.upc.library.domain.model.aggregates.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book>findBookByIsbn(String isbn);

}
