package dev.mayhm.cebooklubapi.controller;

import dev.mayhm.cebooklubapi.dto.GoodreadsDto;
import dev.mayhm.cebooklubapi.integration.BookClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/cebooklub")
@CrossOrigin
@Slf4j
public class BookController {

    private final BookClient bookClient;

    public BookController(BookClient bookClient) {
        this.bookClient = bookClient;
    }

    @GetMapping("/book/{isbn}")
    public GoodreadsDto findBookByIsb(@PathVariable String isbn){
        return bookClient.findBookByIsbn(isbn, "5xfXFYst4ekRDaQBBCncsw");
    }
}
