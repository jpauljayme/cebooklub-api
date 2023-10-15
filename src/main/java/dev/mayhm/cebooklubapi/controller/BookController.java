package dev.mayhm.cebooklubapi.controller;

import dev.mayhm.cebooklubapi.dto.ApiResponse;
import dev.mayhm.cebooklubapi.dto.GoodreadsDto;

public interface BookController {

    ApiResponse findByIsbn(String isbn);


}
