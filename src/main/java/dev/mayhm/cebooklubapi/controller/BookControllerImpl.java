package dev.mayhm.cebooklubapi.controller;

import dev.mayhm.cebooklubapi.dto.ApiResponse;
import dev.mayhm.cebooklubapi.dto.BookDto;
import dev.mayhm.cebooklubapi.entity.Book;
import dev.mayhm.cebooklubapi.integration.BookClient;
import dev.mayhm.cebooklubapi.service.BookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/cebooklub")
@CrossOrigin
@Slf4j
public class BookControllerImpl implements BookController{
    private final BookServiceImpl bookService;

    public BookControllerImpl(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/{isbn}")
    @Override
    public ApiResponse<List<BookDto>> findByIsbn(@PathVariable String isbn){

        return new ApiResponse();
    }
}
