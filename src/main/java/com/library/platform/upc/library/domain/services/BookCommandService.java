package com.library.platform.upc.library.domain.services;

import com.library.platform.upc.library.domain.model.aggregates.Book;
import com.library.platform.upc.library.domain.model.commands.CreateBookCommand;
import com.library.platform.upc.library.domain.model.commands.UpdateBookCommand;

import java.util.Optional;

public interface BookCommandService {

    Optional<Book>handle(CreateBookCommand command);

    Optional<Book>handle(UpdateBookCommand command);

    boolean handleDeleteBook(Long id);
}
