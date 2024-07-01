package com.library.platform.upc.library.domain.model.commands;

import java.util.Date;

public record CreateBookCommand(String isbn,
                                String title,
                                String author,
                                Date publishedDate,
                                String status,
                                Long genreId
) {

}