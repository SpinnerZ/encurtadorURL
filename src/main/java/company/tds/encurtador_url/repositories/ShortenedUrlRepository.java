package company.tds.encurtador_url.repositories;

import company.tds.encurtador_url.entities.ShortenedUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl, Long> {}
