package com.library.platform.upc.library.application.internal.queryservices;

import com.library.platform.upc.library.domain.model.aggregates.Book;
import com.library.platform.upc.library.domain.model.queries.GetAllBooksQuery;
import com.library.platform.upc.library.domain.services.BookQueryService;
import com.library.platform.upc.library.infrastructure.persistence.jpa.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookQueryServiceImpl implements BookQueryService {

    private final BookRepository bookRepository;

    public BookQueryServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> handle(GetAllBooksQuery query) {
        return bookRepository.findAll();
    }
}
