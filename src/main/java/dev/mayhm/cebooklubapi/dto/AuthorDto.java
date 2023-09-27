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
        "small_image_url",
        "link",
        "ratings_count",
        "text_reviews_count"
})
public class AuthorDto {

    @JsonProperty("id")
    String goodreadsId;

    @JsonProperty("name")
    String name;

    @JsonProperty("role")
    String role;

    @JsonProperty("image_url")
    String image_url;
}
