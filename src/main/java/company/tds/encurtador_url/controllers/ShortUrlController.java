package company.tds.encurtador_url.controllers;

import company.tds.encurtador_url.entities.dtos.CreateResponseShortUrlDto;
import company.tds.encurtador_url.entities.dtos.RequestShortUrlDto;
import company.tds.encurtador_url.entities.dtos.ResponseDto;
import company.tds.encurtador_url.service.ShortUrlService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/url")
public class ShortUrlController {
  private final ShortUrlService service;

  public ShortUrlController(ShortUrlService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<ResponseDto<CreateResponseShortUrlDto>> shortenUrl(
      @RequestBody @Valid RequestShortUrlDto dto) {
    return ResponseEntity.ok(new ResponseDto<>(service.create(dto.getUrl())));
  }

  @GetMapping("/{shortUrl}")
  public RedirectView redirectToLongUrl(
      @PathVariable String shortUrl) {
    return new RedirectView(service.retrieve(shortUrl));
  }
}
