package company.tds.encurtador_url.utils.url;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import company.tds.encurtador_url.exceptions.InvalidUrlExeption;
import company.tds.encurtador_url.utils.Url;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class AssumeIsValidTest {
  @Test
  @DisplayName("Valid URL")
  void assumeIsValidUrlShouldNotThrowAnException() {
    assertDoesNotThrow(
        () ->
            Url.assumeIsValidUrl(
                "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status#client_error_responses"),
        "An exception was thrown, but the URL should be valid");
  }

  @Test
  @DisplayName("Invalid URL")
  void assumeIsValidUrlShouldThrowInvalidUrlExceptionWhenUrlIsInvalid() {
    assertThrows(
        InvalidUrlExeption.class,
        () -> Url.assumeIsValidUrl("Invalid URL"),
        "An exception was not thrown, but the URL should be invalid");
  }
}
