package dev.mayhm.cebooklubapi.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mayhm.cebooklubapi.dto.Authors;
import dev.mayhm.cebooklubapi.dto.BookDto;
import dev.mayhm.cebooklubapi.dto.GoodreadsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("tbl_book")
public class Book {

    @Id
//    @Column("fld_book_id")
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
    @MappedCollection(idColumn = "book_id",
            keyColumn = "author_id"
    )
    Set<AuthorRef> authors = new HashSet<>();

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


//        Authors authors1 = object.getAuthors();

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

    public Book(String title,
                String isbn,
                String imageUrl,
                String description,
                LocalDate original_publication_date,
                Float averageRating,
                int numPages) {

        this.title = title;
        this.isbn = isbn;
        this.imageUrl = imageUrl;
        this.description = description;
        this.original_publication_date = original_publication_date;
        this.averageRating = averageRating;
        this.numPages = numPages;
    }
    /**
     * The aggregate root should take care of whatever logic is necessary to add a course.
     */
    public void addAuthor(Author author) {
        final AuthorRef authorRef = new AuthorRef();
        authorRef.authorId = AggregateReference.to(author.id);

    }
}
