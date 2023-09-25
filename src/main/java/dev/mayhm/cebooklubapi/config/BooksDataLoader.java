package dev.mayhm.cebooklubapi.config;

import dev.mayhm.cebooklubapi.dto.GoodreadsDto;
import dev.mayhm.cebooklubapi.entity.Book;
import dev.mayhm.cebooklubapi.repository.BookRepository;
import dev.mayhm.cebooklubapi.service.BookDbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import dev.mayhm.cebooklubapi.entity.Author;
@Component
@Slf4j
public class BooksDataLoader implements CommandLineRunner {

    private final BookDbService bookDbService;
    private final BookRepository bookRepository;

    @Autowired
    public BooksDataLoader(BookDbService bookDbService, BookRepository bookRepository) {
        this.bookDbService = bookDbService;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("Here at run.");
        retrieveFromGoodreads();
    }

    private void retrieveFromGoodreads(){

        int total_books = 6;

        List<String> isbnList = Arrays.asList("0060837020",
                "9780099458326");
//                "0811204812",
//                "158799190X",
//                "0449911438");

        final List<CompletableFuture<GoodreadsDto>> completableFutures =
                new ArrayList<>();

        /**
         * Combine all completablefuture requests in a loop and add to
         * completableFutures.
         */
        for(int index = 0 ; index < isbnList.size() ; index++){

            final int bookIndex = index;
            CompletableFuture<GoodreadsDto> requestCompletableFuture = CompletableFuture
                    .supplyAsync(() -> bookDbService.findBookByIsbn(isbnList.get(bookIndex)));

            completableFutures.add(requestCompletableFuture);
        }

        /**
         * Container for combined completed future results.
         */
        CompletableFuture[] futureResultArray = completableFutures.toArray(new CompletableFuture[0]);

        /**
         * Combine all completed futures.
         */
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(futureResultArray);

        /**
         * thenApply() is useful when we want to transform the result of a CompletableFuture call:
         * Retrieve all Movie Page results once all requests are completed.
         */
        CompletableFuture<List<GoodreadsDto>> finalResults = combinedFuture
                .thenApply(voidd ->
                        completableFutures.stream()
                                .map(CompletableFuture::join)
                                .collect(Collectors.toList()));


        /**
         * Map from dto to entity and save to database.
         */
        finalResults.thenAccept(result -> {
            List<Book> books;
            System.out.println("Here at result");
             books = new ArrayList<>(result.stream()
                    .map(goodreadsDto -> new Book(goodreadsDto.getBookDto()))
                    .toList());

            result.stream().map(goodreadsDto -> goodreadsDto
                    .getBookDto()
                    .getAuthors()
                    .getAuthor().stream().map(author -> new Author(
                        null,
                        author.getGoodreadsId(),
                        author.getName(),
                        author.getRole(),
                        author.getImage_url()
                ))).collect(Collectors.toList());


            log.info(books.toString());
            books.forEach(bookRepository::save);
        });
    }
}
