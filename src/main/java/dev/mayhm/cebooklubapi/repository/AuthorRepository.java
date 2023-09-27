package dev.mayhm.cebooklubapi.repository;

import dev.mayhm.cebooklubapi.entity.Author;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends ListCrudRepository<Author, Integer> {

    Author findByName(String name);
}
