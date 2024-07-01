package com.library.platform.upc.library.application.internal.commandservices;

import com.library.platform.upc.library.domain.exceptions.BookAlreadyExistException;
import com.library.platform.upc.library.domain.exceptions.BookNotFoundException;
import com.library.platform.upc.library.domain.exceptions.GenreNotFoundException;
import com.library.platform.upc.library.domain.model.aggregates.Book;
import com.library.platform.upc.library.domain.model.commands.CreateBookCommand;
import com.library.platform.upc.library.domain.model.commands.UpdateBookCommand;
import com.library.platform.upc.library.domain.model.entities.Genre;
import com.library.platform.upc.library.domain.model.valueobjects.BookPublishedDate;
import com.library.platform.upc.library.domain.model.valueobjects.BookStatus;
import com.library.platform.upc.library.domain.services.BookCommandService;
import com.library.platform.upc.library.infrastructure.persistence.jpa.BookRepository;
import com.library.platform.upc.library.infrastructure.persistence.jpa.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookCommandServiceImpl implements BookCommandService {

    private final BookRepository bookRepository;

    private final GenreRepository genreRepository;

    public BookCommandServiceImpl(BookRepository bookRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public Optional<Book>handle(CreateBookCommand command) {
        Genre genre = genreRepository.findById(command.genreId())
                .orElseThrow(() -> new GenreNotFoundException("Genre not found"));
        bookRepository.findBookByIsbn(command.isbn())
                .ifPresent(book -> {
                    throw new BookAlreadyExistException("Book already exists");
                });

        var book = new Book(command, genre);
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book>handle(UpdateBookCommand command){
        var result = bookRepository.findById(command.id());
        if(result.isEmpty()){
            throw new BookNotFoundException("Book not found");
        }
        var bookToUpdate = result.get();
        try{
            Genre genre = genreRepository.findById(command.genreId())
                    .orElseThrow(() -> new GenreNotFoundException("Genre not found"));
            var updatedBook = bookRepository.save(bookToUpdate.updateInformation(command.isbn(), command.title(), command.author(), new BookPublishedDate(command.publishedDate()),new BookStatus(command.status()),genre));
            return Optional.of(updatedBook);
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while updating book: " + e.getMessage());
        }
    }

    @Override
    public boolean handleDeleteBook(Long id){
        var result = bookRepository.findById(id);
        if(result.isEmpty()){
            throw new BookNotFoundException("Book not found");
        }
        bookRepository.delete(result.get());
        return true;
    }
}
