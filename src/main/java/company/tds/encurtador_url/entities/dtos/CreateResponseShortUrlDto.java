package company.tds.encurtador_url.entities.dtos;

public class CreateResponseShortUrlDto {
  private String shortUrl;
  private String longUrl;

  public CreateResponseShortUrlDto() {
  }

  public CreateResponseShortUrlDto(String shortUrl, String longUrl) {
    this.shortUrl = shortUrl;
    this.longUrl = longUrl;
  }

  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }

  public String getLongUrl() {
    return longUrl;
  }

  public void setLongUrl(String longUrl) {
    this.longUrl = longUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CreateResponseShortUrlDto that = (CreateResponseShortUrlDto) o;

    if (!shortUrl.equals(that.shortUrl)) {
      return false;
    }
    return longUrl.equals(that.longUrl);
  }

  @Override
  public int hashCode() {
    int result = shortUrl.hashCode();
    result = 31 * result + longUrl.hashCode();
    return result;
  }
}
