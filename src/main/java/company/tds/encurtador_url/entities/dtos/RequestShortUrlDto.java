package company.tds.encurtador_url.entities.dtos;

import javax.validation.constraints.NotBlank;

public class RequestShortUrlDto {
  @NotBlank(message = "The URL to be shortened cannot be blank!")
  private String url;

  public RequestShortUrlDto() {}

  public RequestShortUrlDto(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    RequestShortUrlDto that = (RequestShortUrlDto) o;

    return url.equals(that.url);
  }

  @Override
  public int hashCode() {
    return url.hashCode();
  }
}
