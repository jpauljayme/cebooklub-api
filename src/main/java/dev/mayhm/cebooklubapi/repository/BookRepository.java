package dev.mayhm.cebooklubapi.repository;

import dev.mayhm.cebooklubapi.entity.Book;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ListCrudRepository<Book, Integer> {
}
