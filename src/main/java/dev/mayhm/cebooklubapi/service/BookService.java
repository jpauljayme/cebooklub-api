package dev.mayhm.cebooklubapi.service;

import dev.mayhm.cebooklubapi.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAllBooks();

    Optional<List<Book>> findByTitle(String title);

//    Optional<List<Book>> findBookByName(String name);

    void addBookToLibrary(List<Book> bookList);
}
