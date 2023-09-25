package dev.mayhm.cebooklubapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement
//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIgnoreProperties({ "Request",
        "review",
        "comments_count",
        "link"
})
public class GoodreadsDto {

//    @JsonProperty("search")
//    Search search;
//    @Id

    @JsonProperty("book")
    BookDto bookDto;

}
