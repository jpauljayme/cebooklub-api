package dev.mayhm.cebooklubapi.service;

import dev.mayhm.cebooklubapi.dto.GoodreadsDto;
import org.springframework.stereotype.Service;

public interface BookDbService {
    GoodreadsDto findBookByIsbn(String isbn);
}
