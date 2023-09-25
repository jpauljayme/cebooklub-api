package dev.mayhm.cebooklubapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({
        "small_image_url",
        "link",
        "ratings_count",
        "text_reviews_count"
})
public class Author {

    @JsonProperty("id")
    String goodreadsId;

    @JsonProperty("name")
    String name;

    @JsonProperty("role")
    String role;

    @JsonProperty("image_url")
    String image_url;
}
