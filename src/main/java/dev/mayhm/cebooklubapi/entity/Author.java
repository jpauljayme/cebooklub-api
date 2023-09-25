package dev.mayhm.cebooklubapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
public class Author {


    @Id
    @Column("fld_author_id")
    Integer id;

    @Column("fld_goodreads_id")
    String goodreadsId;

    @Column("fld_name")
    String name;

    @Column("fld_role")
    String role;

    @Column("fld_image_url")
    String imageUrl;
}
