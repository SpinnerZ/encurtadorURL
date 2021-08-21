package company.tds.encurtador_url.service;

import company.tds.encurtador_url.entities.ShortUrl;
import company.tds.encurtador_url.repositories.ShortUrlRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlServiceHelper {
  private final ShortUrlRepository repository;

  public ShortUrlServiceHelper(ShortUrlRepository repository) {
    this.repository = repository;
  }

  public ShortUrl createOrUpdate(String longUrl) {
    Optional<ShortUrl> response = repository.findByUrl(longUrl);
    ShortUrl url;

    if (response.isPresent()) {
      url = response.get();
      url.addCreationAttempt();
    } else {
      url = new ShortUrl(longUrl);
    }

    return repository.save(url);
  }
}
