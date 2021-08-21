package company.tds.encurtador_url.exceptions;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public class StandardError implements Serializable {
  private static final long serialVersionUID = 1L;

  private final UUID messageId = UUID.randomUUID();
  private final Instant instant = Instant.now();
  private String message;
  private final String data = "";

  public StandardError(String message) {
    this.message = message;
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

  public String getData() {
    return data;
  }
}
