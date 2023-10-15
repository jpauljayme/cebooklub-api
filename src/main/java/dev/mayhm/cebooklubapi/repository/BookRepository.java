package dev.mayhm.cebooklubapi.repository;

import dev.mayhm.cebooklubapi.entity.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends ListCrudRepository<Book, Integer> {


    @Override
    @Cacheable("books")
    List<Book> findAll();

    @Override
    Optional<Book> findById(Integer id);

    @Override
    long count();

    @Cacheable("books")
    Optional<List<Book>> findByTitle(String title);

    List<Book> findByIsbn(String isbn);
}
