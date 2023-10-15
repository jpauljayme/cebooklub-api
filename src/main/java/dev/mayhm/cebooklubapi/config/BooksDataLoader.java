package dev.mayhm.cebooklubapi.config;

import dev.mayhm.cebooklubapi.dto.AuthorDto;
import dev.mayhm.cebooklubapi.dto.BookDto;
import dev.mayhm.cebooklubapi.dto.GoodreadsDto;
import dev.mayhm.cebooklubapi.entity.Book;
import dev.mayhm.cebooklubapi.repository.AuthorRepository;
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

    private final AuthorRepository authorRepository;

    @Autowired
    public BooksDataLoader(BookDbService bookDbService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookDbService = bookDbService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (bookRepository.count() == 0) {
            retrieveFromGoodreads();
        }

    }

    private void retrieveFromGoodreads(){

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
            List<Book> bookList = new ArrayList<>();
            List<Author> authors = new ArrayList<>();
            System.out.println("Here at result");

            result.forEach(goodreadsDto -> {
                BookDto bookDto = goodreadsDto.getBookDto();
                Book book = new Book(bookDto);
                List<AuthorDto> authorDtos = bookDto.getAuthors().getAuthorDto();
                List<Author> authorList = authorDtos.stream().map(authorDto -> new Author(null,
                        authorDto.getGoodreadsId(),
                        authorDto.getName(),
                        authorDto.getRole(),
                        authorDto.getImage_url())).toList();

                List<Author> authors1 = authorRepository.saveAll(authorList);
                authors1.forEach(book::addAuthor);
                bookList.add(book);
            });

//
//             books = new ArrayList<>(result.stream()
//                    .map(goodreadsDto -> new Book(goodreadsDto.getBookDto()))
//                    .toList());

//            authors.forEach(authorRepository::save);
            bookList.forEach(bookRepository::save);

        });
    }
}
