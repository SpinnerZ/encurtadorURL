package company.tds.encurtador_url.service.shortened_url_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import company.tds.encurtador_url.entities.ShortUrl;
import company.tds.encurtador_url.exceptions.UrlNotFoundException;
import company.tds.encurtador_url.service.Converter;
import company.tds.encurtador_url.service.ShortUrlService;
import company.tds.encurtador_url.service.ShortUrlServiceHelper;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RetrieveTest {

  @InjectMocks ShortUrlService service;
  @Mock ShortUrlServiceHelper helper;
  @Mock Converter converter;

  String longUrl = "https://duckduckgo.com/";
  String shortUrl = "Olt";
  Long id = 1L;
  ShortUrl urlObject = new ShortUrl(longUrl);

  @BeforeEach
  void setUp() {
    when(converter.uncompressId(shortUrl)).thenReturn(id);
  }

  @Test
  @DisplayName("Happy path - url exists")
  void retrieveShouldReturnLongUrlWhenRequestStringMatchesAnId() {
    when(helper.getShortUrlAndUpdate(id)).thenReturn(Optional.of(urlObject));

    assertEquals(longUrl, service.retrieve(shortUrl), "Does not return the expected long url");
  }

  @Test
  @DisplayName("Url does not exists")
  void retrieveShouldThrowUrlNotFoundExceptionWhenShortenUrlDoesNotHaveAnExistentIdInRepository() {
    when(helper.getShortUrlAndUpdate(id)).thenReturn(Optional.empty());

    assertThrows(
        UrlNotFoundException.class,
        () -> service.retrieve(shortUrl),
        "Does not throw the expected exception when given short url does not matches an existent id");
  }
}
