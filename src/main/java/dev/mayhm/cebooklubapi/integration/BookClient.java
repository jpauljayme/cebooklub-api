package dev.mayhm.cebooklubapi.integration;

import dev.mayhm.cebooklubapi.config.FeignClientConfiguration;
import dev.mayhm.cebooklubapi.dto.GoodreadsDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "goodreads-client",
        url = "https://www.goodreads.com",
        configuration = FeignClientConfiguration.class
)
public interface BookClient {

//    @GetMapping(value = "/search.xml?key={apiKey}&q={isbn}",
////        consumes = MediaType.APPLICATION_XML_VALUE,
//            produces = MediaType.APPLICATION_XML_VALUE
//    )
//    @Headers("Content-Type: application/xml")
//    GoodreadsResponse findBookByIsbn(@RequestParam String isbn,
//                                     @RequestParam String apiKey);

    @GetMapping(value = "/book/isbn/{isbn}?key={apiKey}&format=xml",
            produces = MediaType.APPLICATION_XML_VALUE
    )
    @Headers("Content-Type: application/xml")
    GoodreadsDto findBookByIsbn(@PathVariable String isbn,
                                @PathVariable String apiKey);
}
