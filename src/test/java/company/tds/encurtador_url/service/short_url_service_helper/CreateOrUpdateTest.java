package company.tds.encurtador_url.service.short_url_service_helper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import company.tds.encurtador_url.entities.ShortUrl;
import company.tds.encurtador_url.repositories.ShortUrlRepository;
import company.tds.encurtador_url.service.ShortUrlServiceHelper;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CreateOrUpdateTest {
  @InjectMocks ShortUrlServiceHelper serviceHelper;
  @Mock ShortUrlRepository repository;

  String longUrl = "link";
  ShortUrl url;
  LocalDateTime timeBefore;

  @BeforeEach
  void setUp() {
    when(repository.save(any())).then(returnsFirstArg());

    timeBefore = LocalDateTime.now();
  }

  @Test
  @DisplayName("URL already exists")
  void createOrUpdateShouldUpdateUrlCreationAttempt() {
    url = new ShortUrl("link");
    when(repository.findByUrl(longUrl)).thenReturn(Optional.of(url));

    url = serviceHelper.createOrUpdate(longUrl);

    assertAll(
        "Creation attempt was not saved properly",
        () -> {
          assertTrue(
              url.getLastCreationAttempt().isAfter(timeBefore)
                  || url.getLastCreationAttempt().isEqual(timeBefore));
          assertEquals(2, url.getCreationsCount());
        });
  }

  @Test
  @DisplayName("URL does not exists")
  void createOrUpdateShouldNewUrlWhenUrlDoesNotExists() {
    when(repository.findByUrl(longUrl)).thenReturn(Optional.empty());

    url = serviceHelper.createOrUpdate(longUrl);

    assertAll(
        "Creation was not saved properly",
        () -> {
          assertEquals(longUrl, url.getUrl());
          assertEquals(0, url.getAccessCount());
          assertEquals(1, url.getCreationsCount());
        });
  }
}
