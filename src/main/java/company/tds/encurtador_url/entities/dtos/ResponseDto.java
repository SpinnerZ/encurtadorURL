package company.tds.encurtador_url.entities.dtos;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public class ResponseDto<T> implements Serializable {
  private static final long serialVersionUID = 1L;

  private final UUID messageId = UUID.randomUUID();
  private final Instant instant = Instant.now();
  private final String message = "";
  private final T data;

  public ResponseDto(T data) {
    this.data = data;
  }

  public UUID getMessageId() {
    return messageId;
  }

  public Instant getInstant() {
    return instant;
  }

  public String getMessage() {
    return message;
  }

  public T getData() {
    return data;
  }
}
