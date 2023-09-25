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
        "books_count",
        "best_book_id",
        "reviews_count",
        "ratings_sum",
        "ratings_count",
        "text_reviews_count",
        "original_language_id",
        "media_type",
        "rating_dist",
        "desc_user_id",
        "default_chaptering_book_id",
        "default_description_language_code",
        "work_uri",
        "original_title",
        "image_url"
})
public class Work {
    @JsonProperty("id")
    Integer id;

//    @JsonProperty("image_url")
//    String image_url;

    @JsonProperty("original_publication_year")
    Integer originalPublicationYear;

    @JsonProperty("original_publication_month")
    Integer originalPublicationMonth;

    @JsonProperty("original_publication_day")
    Integer originalPublicationDay;

    @JsonProperty("average_rating")
    Float averageRating;

    BestBook bestBook;
}
