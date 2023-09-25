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
        "id",
        "ratings_count",
        "text_reviews_count",
        "original_publication_day"
})
public class BestBook {

    @JsonProperty("title")
    String title;

    @JsonProperty("author")
    String author;
}
