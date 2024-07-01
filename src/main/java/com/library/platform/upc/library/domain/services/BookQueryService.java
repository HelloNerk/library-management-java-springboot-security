package com.library.platform.upc.library.domain.services;

import com.library.platform.upc.library.domain.model.aggregates.Book;
import com.library.platform.upc.library.domain.model.queries.GetAllBooksQuery;

import java.util.List;

public interface BookQueryService {
    List<Book>handle(GetAllBooksQuery query);
}
