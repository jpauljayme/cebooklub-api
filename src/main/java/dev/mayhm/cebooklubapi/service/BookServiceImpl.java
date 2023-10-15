package dev.mayhm.cebooklubapi.service;

import dev.mayhm.cebooklubapi.entity.Book;
import dev.mayhm.cebooklubapi.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> findAllBooks() {
        return repository.findAll();
    }

    public Optional<List<Book>> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public List<Book> findByIsbn(String isbn){
        return repository.findByIsbn(isbn);
    }

    @Override
    public void addBookToLibrary(List<Book> bookList) {

    }
}
