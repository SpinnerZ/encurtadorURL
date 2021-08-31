package company.tds.encurtador_url.service.shortened_url_service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import company.tds.encurtador_url.entities.ShortUrl;
import company.tds.encurtador_url.entities.dtos.CreateResponseShortUrlDto;
import company.tds.encurtador_url.exceptions.InvalidUrlExeption;
import company.tds.encurtador_url.service.Converter;
import company.tds.encurtador_url.service.ShortUrlService;
import company.tds.encurtador_url.service.ShortUrlServiceHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CreateTest {
  @InjectMocks ShortUrlService service;
  @Mock ShortUrlServiceHelper helper;
  @Mock Converter converter;

  String longUrl;
  CreateResponseShortUrlDto resultDto;
  ShortUrl shortUrl;

  @Value("${tds.url-prefix:null}")
  String urlPrefix;

  @BeforeEach
  void setUp() {
    longUrl = "https://duckduckgo.com/";
    resultDto = null;

    shortUrl = new ShortUrl("https://duckduckgo.com/");
    shortUrl.setId(56155L);

    when(converter.compressId(shortUrl.getId())).thenReturn("Olt");
  }

  @Test
  @DisplayName("Valid URL - Happy Path")
  void createShouldSaveTheUrlAndReturnAShortenedUrl() {
    when(helper.createOrUpdate(longUrl)).thenReturn(shortUrl);

    resultDto = service.create(longUrl);

    assertAll(
        "Response DTO was not returned as expected",
        () -> {
          assertEquals(urlPrefix + "url/" + "Olt", resultDto.getShortUrl());
          assertEquals(longUrl, resultDto.getLongUrl());
        });
  }

  @Test
  @DisplayName("Invalid URL")
  void createShouldThrowInvalidUrlExceptionWhenUrlIsMalformed() {
    longUrl = "Not a Valid URL";

    assertThrows(InvalidUrlExeption.class, () -> service.create(longUrl));
  }
}
