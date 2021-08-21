package company.tds.encurtador_url.entities.dtos;

import javax.validation.constraints.NotBlank;

public class CreateRequestShortUrlDto {
  @NotBlank(message = "The URL to be shortened cannot be blank!")
  private String url;

  public CreateRequestShortUrlDto() {}

  public CreateRequestShortUrlDto(String url) {
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

    CreateRequestShortUrlDto that = (CreateRequestShortUrlDto) o;

    return url.equals(that.url);
  }

  @Override
  public int hashCode() {
    return url.hashCode();
  }
}
