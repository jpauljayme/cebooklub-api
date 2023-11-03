package dev.mayhm.cebooklubapi.controller;

import dev.mayhm.cebooklubapi.dto.ApiResponse;
import dev.mayhm.cebooklubapi.dto.GoodreadsDto;
import dev.mayhm.cebooklubapi.entity.Book;

import java.util.List;

public interface BookController {

    ApiResponse<List<Book>> findByIsbn(String isbn);
    ApiResponse<List<Book>> findAll();

    byte[] findBookCoverByIsbn(String isbn);

}
