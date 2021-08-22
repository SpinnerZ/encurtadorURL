package company.tds.encurtador_url.service;

import company.tds.encurtador_url.entities.ShortUrl;
import company.tds.encurtador_url.entities.dtos.CreateResponseShortUrlDto;
import company.tds.encurtador_url.exceptions.UrlNotFoundException;
import company.tds.encurtador_url.utils.Url;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShortUrlService {
  private final ShortUrlServiceHelper helper;
  private final Converter converter;

  private final String urlPrefix;

  public ShortUrlService(
      ShortUrlServiceHelper helper,
      Converter converter,
      @Value("${tds.url-prefix:https://tds.company/}") String urlPrefix) {
    this.helper = helper;
    this.converter = converter;
    this.urlPrefix = urlPrefix;
  }

  @Transactional(rollbackFor = Exception.class)
  public CreateResponseShortUrlDto create(String longUrl) {
    Url.assumeIsValidUrl(longUrl);

    ShortUrl url = helper.createOrUpdate(longUrl);
    String shortenUrl = urlPrefix + converter.compressId(url.getId());

    return new CreateResponseShortUrlDto(shortenUrl, longUrl);
  }

  @Transactional(readOnly = true)
  public String retrieve(String shortUrl) {
    Long id = converter.uncompressId(shortUrl);

    ShortUrl url = helper.getShortUrlAndUpdate(id)
        .orElseThrow(() -> new UrlNotFoundException(shortUrl));

    return url.getUrl();
  }
}
