package company.tds.encurtador_url.utils;

import company.tds.encurtador_url.exceptions.InvalidUrlExeption;
import org.apache.commons.validator.routines.UrlValidator;

public class Url {
  private Url() {}

  private static final UrlValidator validator = new UrlValidator();

  public static void assumeIsValidUrl(String url) {
    if (!validator.isValid(url)) {
      throw new InvalidUrlExeption(url);
    }
  }
}
