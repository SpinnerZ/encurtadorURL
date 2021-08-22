package company.tds.encurtador_url.Controllers.short_url_controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class ShortenUrlTest {
  @Autowired MockMvc mvc;
  JSONObject jsonRequest;
  JSONObject jsonResponse;

  @Value("${tds.url-prefix:https://tds.company/}")
  String urlPrefix;

  @BeforeEach
  void setUp() {
    jsonRequest = new JSONObject();
    jsonResponse = new JSONObject();
  }

  @Test
  @DisplayName("Happy Path - Get shorten URL")
  void shortenUrlShouldReturnAShortenUrlWhenUrlIsValid() throws Exception {
    jsonRequest.put("url", "https://devdocs.io/");

    jsonResponse =
        new JSONObject(
            mvc.perform(
                    post("/url")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());

    assertAll(
        "Response JSON does not contains the expected structure or the returning data is wrong",
        () -> {
          assertEquals(urlPrefix + "b", jsonResponse.getJSONObject("data").getString("shortUrl"));
          assertEquals(
              "https://devdocs.io/", jsonResponse.getJSONObject("data").getString("longUrl"));
        });
  }
}
