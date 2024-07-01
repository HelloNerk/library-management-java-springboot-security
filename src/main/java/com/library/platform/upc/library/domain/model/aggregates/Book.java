package com.library.platform.upc.library.domain.model.aggregates;

import com.library.platform.upc.library.domain.model.commands.CreateBookCommand;
import com.library.platform.upc.library.domain.model.entities.Genre;
import com.library.platform.upc.library.domain.model.valueobjects.BookPublishedDate;
import com.library.platform.upc.library.domain.model.valueobjects.BookStatus;
import com.library.platform.upc.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Book extends AuditableAbstractAggregateRoot<Book> {

    @Column(nullable = false)
    @NotNull(message = "Isbn is required")
    private String isbn;

    @Column(nullable = false)
    @NotNull(message = "Title is required")
    private String title;

    @Column(nullable = false)
    @NotNull(message = "Author is required")
    private String author;

    @Embedded
    @Column(nullable = false)
    @NotNull(message = "Published date is required")
    private BookPublishedDate publishedDate;

    @Embedded
    @Column(nullable = false)
    @NotNull(message = "Status is required")
    private BookStatus status;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    @NotNull(message = "Genre is required")
    private Genre genreId;

    public Book() {
    }

    public Book(CreateBookCommand command, Genre genre) {
        this.isbn = command.isbn();
        this.title = command.title();
        this.author = command.author();
        this.publishedDate = new BookPublishedDate(command.publishedDate());
        this.status = new BookStatus(command.status());
        this.genreId = genre;
    }

    public Book updateInformation(String isbn, String title, String author, BookPublishedDate publishedDate, BookStatus status, Genre genre) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.status = status;
        this.genreId = genre;
        return this;
    }

    public Long getGenreId() {
        return genreId.getId();
    }

    public Long getGenreName() {
        return genreId.getGenreId();
    }

    public String getStatus() {
        return status.getBookStatus();
    }

    public Date getPublishedDate() {
        return publishedDate.getBookPublishedDate();
    }




}
