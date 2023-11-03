package dev.mayhm.cebooklubapi.integration;

import dev.mayhm.cebooklubapi.config.FeignClientConfiguration;
import dev.mayhm.cebooklubapi.dto.GoodreadsDto;
import feign.Headers;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "openlibrary-bookcover-client",
        url = "https://covers.openlibrary.org"
)
public interface BookCoverClient {
    @GetMapping(value = "/b/isbn/{isbn}-L.jpg",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
//    @Headers("Content-Type: image/jpeg")
    Response findBookByIsbn(@PathVariable String isbn);
}
