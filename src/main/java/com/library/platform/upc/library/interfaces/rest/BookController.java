package com.library.platform.upc.library.interfaces.rest;


import com.library.platform.upc.library.domain.model.aggregates.Book;
import com.library.platform.upc.library.domain.model.queries.GetAllBooksQuery;
import com.library.platform.upc.library.domain.services.BookCommandService;
import com.library.platform.upc.library.domain.services.BookQueryService;
import com.library.platform.upc.library.interfaces.rest.resources.BookResource;
import com.library.platform.upc.library.interfaces.rest.resources.CreateBookResource;
import com.library.platform.upc.library.interfaces.rest.resources.UpdateBookResource;
import com.library.platform.upc.library.interfaces.rest.transform.BookResourceFromEntityAssembler;
import com.library.platform.upc.library.interfaces.rest.transform.CreateBookCommandFromResourceAssembler;
import com.library.platform.upc.library.interfaces.rest.transform.UpdateBookCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/books", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Books", description = "Book Management Endpoints")
public class BookController {

    private final BookCommandService bookCommandService;

    private final BookQueryService bookQueryService;

    public BookController(BookCommandService bookCommandService, BookQueryService bookQueryService) {
        this.bookCommandService = bookCommandService;
        this.bookQueryService = bookQueryService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('LIBRARIAN') or hasAuthority('ADMIN')")
    public ResponseEntity<BookResource>createBook(@RequestBody CreateBookResource resource){
        Optional<Book>book = bookCommandService.handle(CreateBookCommandFromResourceAssembler.toCommandFromResource(resource));
        return book.map(source -> new ResponseEntity<>(BookResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('LIBRARIAN') or hasAuthority('ADMIN') or hasAuthority('MEMBER')" )
    public ResponseEntity<List<BookResource>>getAllBooks(){
        var getAllBooksQuery = new GetAllBooksQuery();
        var book = bookQueryService.handle(getAllBooksQuery);
        var bookResource=book.stream().map(BookResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(bookResource);
    }

    @PutMapping("/{bookId}")
    @PreAuthorize("hasAuthority('LIBRARIAN') or hasAuthority('ADMIN')")
    public ResponseEntity<BookResource>updateBook(@PathVariable Long bookId, @RequestBody UpdateBookResource resource){
        var updateBookCommand = UpdateBookCommandFromResourceAssembler.toCommandFromResource(bookId, resource);
        var book = bookCommandService.handle(updateBookCommand);
        if(book.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var bookResource = BookResourceFromEntityAssembler.toResourceFromEntity(book.get());
        return ResponseEntity.ok(bookResource);
    }

    @DeleteMapping("/{bookId}")
    @PreAuthorize("hasAuthority('LIBRARIAN') or hasAuthority('ADMIN')")
    public ResponseEntity<Void>deleteBook(@PathVariable Long bookId){
        try{
            bookCommandService.handleDeleteBook(bookId);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
