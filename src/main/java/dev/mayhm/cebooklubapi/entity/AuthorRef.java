package dev.mayhm.cebooklubapi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

@Table("tbl_book_author")
public class AuthorRef {

    @Id
    Integer id;

    AggregateReference<Author,Integer> authorId;
}
