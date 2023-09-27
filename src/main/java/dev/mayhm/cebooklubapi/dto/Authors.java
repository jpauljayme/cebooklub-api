package dev.mayhm.cebooklubapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authors {

    @JsonProperty("author")
    @JacksonXmlElementWrapper(useWrapping = false)
    List<AuthorDto> authorDto;
}
