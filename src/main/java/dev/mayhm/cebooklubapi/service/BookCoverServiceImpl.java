package dev.mayhm.cebooklubapi.service;

import dev.mayhm.cebooklubapi.integration.BookCoverClient;
import feign.Response;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class BookCoverServiceImpl implements BookCoverService {

    private final BookCoverClient bookCoverClient;

    public BookCoverServiceImpl(BookCoverClient bookCoverClient) {
        this.bookCoverClient = bookCoverClient;
    }

    @Override
    public byte[] getBookCoverByIsbn(String isbn) {

        try(Response response = bookCoverClient.findBookByIsbn(isbn)){
// Base64.getEncoder().encode(IOUtils.toByteArray(is));
            InputStream is = response.body()
                    .asInputStream();

            return IOUtils.toByteArray(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
