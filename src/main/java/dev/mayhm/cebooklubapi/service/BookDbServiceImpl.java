package dev.mayhm.cebooklubapi.service;

import dev.mayhm.cebooklubapi.dto.GoodreadsDto;
import dev.mayhm.cebooklubapi.integration.BookClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BookDbServiceImpl implements BookDbService {

    private final BookClient bookClient;
    private final String apiKey;

    @Autowired
    public BookDbServiceImpl(BookClient bookClient,
                             @Value("${goodreads.apiKey}") String apiKey) {
        this.bookClient = bookClient;
        this.apiKey = apiKey;
    }

    @Override
    public GoodreadsDto findBookByIsbn(String isbn) {
        return bookClient.findBookByIsbn(isbn, apiKey);
    }
}
