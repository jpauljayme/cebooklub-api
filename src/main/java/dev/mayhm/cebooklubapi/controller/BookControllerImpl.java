package dev.mayhm.cebooklubapi.controller;

import dev.mayhm.cebooklubapi.dto.ApiResponse;
import dev.mayhm.cebooklubapi.entity.Book;
import dev.mayhm.cebooklubapi.service.BookCoverServiceImpl;
import dev.mayhm.cebooklubapi.service.BookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/cebooklub")
@CrossOrigin
@Slf4j
public class BookControllerImpl implements BookController{
    private final BookServiceImpl bookService;
    private final BookCoverServiceImpl bookCoverService;

    public BookControllerImpl(BookServiceImpl bookService, BookCoverServiceImpl bookCoverService) {
        this.bookService = bookService;
        this.bookCoverService = bookCoverService;
    }

    @GetMapping("/book/{isbn}")
    @Override
    public ApiResponse<List<Book>> findByIsbn(@PathVariable String isbn){

        return new ApiResponse<>(HttpStatus.OK, bookService.findByIsbn(isbn));
    }


    @Override
    @GetMapping(value = "/books")

    public ApiResponse<List<Book>> findAll() {
        return new ApiResponse<>(HttpStatus.OK, bookService.findAllBooks());
    }


    @Override
    @GetMapping(value = "/covers/{isbn}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] findBookCoverByIsbn(@PathVariable String isbn){
        return bookCoverService.getBookCoverByIsbn(isbn);
    }

}
