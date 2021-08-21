package company.tds.encurtador_url.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import company.tds.encurtador_url.repositories.ShortUrlRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ShortUrlTest {
  ShortUrl url;
  @Autowired
  ShortUrlRepository repository;

  @BeforeEach
  void setUp() {
    url = new ShortUrl("URL");
  }

  @Test
  @DisplayName("Creation time")
  void onCreationShouldSaveCreationTimeWhenSaved() {
    LocalDateTime timeBefore = LocalDateTime.now();
    url = repository.save(url);
    repository.delete(url);

    assertTrue(
        url.getCreatedAt().isAfter(timeBefore) || url.getCreatedAt().isEqual(timeBefore),
        "Creation time was not set properly");
  }

  @Test
  @DisplayName("Creation Attempt -> URL already in repository")
  void addCreationAttemptShouldSaveCreationAttemptData() {
    LocalDateTime timeBefore = LocalDateTime.now();
    url.addCreationAttempt();

    assertTrue(
        url.getLastCreationAttempt().isAfter(timeBefore)
            || url.getLastCreationAttempt().isEqual(timeBefore),
        "Last creation attempt time was not set properly");
    assertEquals(2, url.getCreationsCount(), "Creations count did not increase properly");
  }

  @Test
  @DisplayName("Access URL value")
  void addAccessAttemptShouldSaveAccessData() {
    LocalDateTime timeBefore = LocalDateTime.now();
    url.addAccess();

    assertTrue(
        url.getLastAccess().isAfter(timeBefore) || url.getLastAccess().isEqual(timeBefore),
        "Last access time was not set properly");
    assertEquals(1, url.getAccessCount(), "Access count did not increase properly");
  }
}
