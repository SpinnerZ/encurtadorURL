package company.tds.encurtador_url.repositories;

import company.tds.encurtador_url.entities.ShortUrl;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
  Optional<ShortUrl> findByUrl(String url);
}
