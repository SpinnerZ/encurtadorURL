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
class GetShortUrlAndUpdateTest {

  @InjectMocks ShortUrlServiceHelper serviceHelper;
  @Mock ShortUrlRepository repository;

  Long id = 1L;
  ShortUrl url = new ShortUrl("anyLong.link");
  Optional<ShortUrl> optionalResponse;
  LocalDateTime timeBefore;

  @BeforeEach
  void setUp() {
    when(repository.save(any())).then(returnsFirstArg());

    timeBefore = LocalDateTime.now();
  }

  @Test
  @DisplayName("Happy path - id exists")
  void getShortUrlAndUpdateShouldReturnAndUpdateAccess() {
    when(repository.findById(id)).thenReturn(Optional.of(url));

    optionalResponse = serviceHelper.getShortUrlAndUpdate(id);
    ShortUrl shortResponse = optionalResponse.orElseThrow();

    assertAll(
        "Did not retrieve and/or log the access count",
        () -> {
          assertEquals(url.getUrl(), shortResponse.getUrl());
          assertEquals(1, shortResponse.getAccessCount());
          assertTrue(
              shortResponse.getLastAccess().isAfter(timeBefore)
                  || shortResponse.getLastAccess().isEqual(timeBefore));
        });
  }

  @Test
  @DisplayName("Id not found")
  void getShortUrlAndUpdateShouldReturnAnEmptyOptionalWhenIdDoesNotMatchesAnEntry() {
    when(repository.findById(id)).thenReturn(Optional.empty());

    optionalResponse = serviceHelper.getShortUrlAndUpdate(id);

    assertTrue(optionalResponse.isEmpty());
  }
}
