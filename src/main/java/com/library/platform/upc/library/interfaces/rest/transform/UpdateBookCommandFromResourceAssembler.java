package com.library.platform.upc.library.interfaces.rest.transform;

import com.library.platform.upc.library.domain.model.commands.UpdateBookCommand;
import com.library.platform.upc.library.interfaces.rest.resources.UpdateBookResource;

public class UpdateBookCommandFromResourceAssembler {

    public static UpdateBookCommand toCommandFromResource(Long bookId, UpdateBookResource resource) {
        return new UpdateBookCommand(bookId, resource.isbn(), resource.title(), resource.author(),resource.publishedDate(),resource.status(),resource.genreId());
    }
}
