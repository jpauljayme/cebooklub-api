package dev.mayhm.cebooklubapi.service;

import dev.mayhm.cebooklubapi.dto.ApiResponse;

public interface BookCoverService {

    byte[] getBookCoverByIsbn(String isbn);
}
