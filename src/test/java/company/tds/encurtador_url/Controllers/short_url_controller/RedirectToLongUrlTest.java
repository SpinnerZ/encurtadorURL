package company.tds.encurtador_url.Controllers.short_url_controller;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import company.tds.encurtador_url.entities.ShortUrl;
import company.tds.encurtador_url.repositories.ShortUrlRepository;
import company.tds.encurtador_url.service.Converter;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class RedirectToLongUrlTest {
  @Autowired MockMvc mvc;
  @Autowired ShortUrlRepository repository;
  @Autowired Converter converter;

  @Value("${tds.url-prefix:https://tds.company/}")
  String urlPrefix;

  String longUrl = "https://duckduckgo.com/";

  @Test
  @DisplayName("Happy path - URL exists")
  void redirectToLongUrlShouldRedirectToExistentLongUrl() throws Exception {
    ShortUrl url = repository.save(new ShortUrl(longUrl));

    mvc.perform(get("/url/" + converter.compressId(url.getId())))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl(longUrl));
  }

  @Test
  @DisplayName("When URL does not exist")
  void redirectToLongUrlShouldReturnAMessageWithErrorWhenUrlDoesNotExists() throws Exception {
    JSONObject json =
        new JSONObject(
            mvc.perform(get("/url/a"))
                .andExpect(status().is4xxClientError())
                .andReturn()
                .getResponse()
                .getContentAsString());

    assertNotEquals("", json.get("message"), "Error message was blank");
  }
}
