package company.tds.encurtador_url.controllers;

import company.tds.encurtador_url.entities.dtos.CreateRequestShortUrlDto;
import company.tds.encurtador_url.entities.dtos.CreateResponseShortUrlDto;
import company.tds.encurtador_url.entities.dtos.ResponseDto;
import company.tds.encurtador_url.service.ShortUrlService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/url")
public class ShortUrlController {
  private final ShortUrlService service;

  public ShortUrlController(ShortUrlService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<ResponseDto<CreateResponseShortUrlDto>> shortenUrl(
      @RequestBody @Valid CreateRequestShortUrlDto dto) {
    return ResponseEntity.ok(new ResponseDto<>(service.create(dto.getUrl())));
  }
}
