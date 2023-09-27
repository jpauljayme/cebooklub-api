package dev.mayhm.cebooklubapi;

import dev.mayhm.cebooklubapi.entity.Author;
import dev.mayhm.cebooklubapi.entity.Book;
import dev.mayhm.cebooklubapi.repository.AuthorRepository;
import dev.mayhm.cebooklubapi.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class CebooklubApiApplicationTests {

	@Autowired
	BookRepository repository;

	@Autowired
	AuthorRepository authorRepository;

	Book bookTest = null;

	Author authorTest = null;
//	@BeforeEach
	void setup() {

		bookTest = new Book(
				"The Bell Jar",
				"1234567",
				"Imageurlo",
				"BEST BOOOK EVER",
				LocalDate.now(),
                4.5f,
				300);

		authorTest = new Author(null,
				"123",
				"Sylvia Plath",
				"Author",
				"Dumb_btch"
				);

		Author author = authorRepository.save(authorTest);

		bookTest.addAuthor(author);

		bookTest = repository.save(bookTest);

	}

	@Test
	void testBookAuthor(){
		Book book = repository.findById(1)
				.orElseThrow();
		Assertions.assertNotNull(book);

		Author author = authorRepository.findByName("Sylvia Plath");
		Assertions.assertNotNull(author);
	}
}
