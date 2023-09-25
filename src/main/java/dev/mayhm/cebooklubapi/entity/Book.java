package dev.mayhm.cebooklubapi.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mayhm.cebooklubapi.dto.Authors;
import dev.mayhm.cebooklubapi.dto.BookDto;
import dev.mayhm.cebooklubapi.dto.GoodreadsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@Table("tbl_book")
public class Book {

    @Id
    @Column("fld_book_id")
    Integer id;

    @Column("fld_title")
    String title;

    @Column("fld_isbn")
    String isbn;

    @Column("fld_image_url")
    String imageUrl;

    @Column("fld_description")
    String description;

    @Column("fld_original_publication_date")
    LocalDate original_publication_date;

    @Column("fld_average_rating")
    Float averageRating;

    @Column("fld_num_of_pages")
    Integer numPages;

//    @MappedCollection
//    List<Author> authors;
    List<AuthorRef> authors;
    public Book(BookDto object){
        this.title = object.getTitle();
        this.isbn = object.getIsbn();
        this.imageUrl = object.getImageUrl();
        this.description = object.getDescription();

        int year = 1;
        if(object.getWork().getOriginalPublicationYear() != null){
            year = object.getWork().getOriginalPublicationYear();
        }

        int month = 1;
        if(object.getWork().getOriginalPublicationMonth() != null){
            month = object.getWork().getOriginalPublicationMonth();
        }
//        int day = object.getWork().getOriginalPublicationDay();
        this.original_publication_date = LocalDate.of(year,month, 1);

        this.averageRating = object.getAverageRating();

        if(!object.getNumPages().isBlank()){
            this.numPages = Integer.valueOf(object.getNumPages());
        }else{
            this.numPages = 0;
        }


        Authors authors1 = object.getAuthors();

//        authors = authors1
//                .getAuthor()
//                .stream()
//                .map(authorDto -> new Author(
//                        null,
//                        authorDto.getGoodreadsId(),
//                        authorDto.getName(),
//                        authorDto.getRole(),
//                        authorDto.getImage_url()
//                )).toList();

    }

    public void addAuthor(Author author) {
        authors.add(createAuthorRef(author));
    }

    private AuthorRef createAuthorRef(Author author) {

        Assert.notNull(author, "Author must not be null");
        Assert.notNull(author.id, "Author id, must not be null");

        AuthorRef authorRef = new AuthorRef();
        authorRef.author = author.id;
        return authorRef;
    }
}
