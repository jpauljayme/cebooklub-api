package dev.mayhm.cebooklubapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({
        "isbn13",
        "asin",
        "kindle_asin",
        "marketplace_id",
        "country_code",
        "small_image_url",
        "publication_year",
        "publication_month",
        "publication_day",
        "publisher",
        "language_code",
        "is_ebook",
        "format",
        "edition_information",
        "ratings_count",
        "text_reviews_count",
        "url",
        "link"
})
public class BookDto {
//    @Id
    @JsonProperty("id")
    Integer id;
    @JsonProperty("title")
    String title;

    @JsonProperty("isbn")
    String isbn;

    @JsonProperty("image_url")
    String imageUrl;

    @JsonProperty("description")
    String description;

    @JsonProperty("work")
    Work work;

    @JsonProperty("average_rating")
    Float averageRating;

    @JsonProperty("num_pages")
    String numPages;

    @JsonProperty("authors")
    Authors authors;
}
