package company.tds.encurtador_url.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "tb_url")
public class ShortenedUrl implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String longUrl;
  private LocalDateTime createdAt;
  private LocalDateTime lastCreationAttempt;
  private LocalDateTime lastAccess;
  private Integer accessCount;
  private Integer creationsCount;

  public ShortenedUrl(String longUrl) {
    this.longUrl = longUrl;
    accessCount = 0;
    creationsCount = 1;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLongUrl() {
    return longUrl;
  }

  public void setLongUrl(String longUrl) {
    this.longUrl = longUrl;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @PrePersist
  private void onCreation() {
    createdAt = LocalDateTime.now();
  }

  public LocalDateTime getLastCreationAttempt() {
    return lastCreationAttempt;
  }

  public void addCreationAttempt() {
    lastCreationAttempt = LocalDateTime.now();
    creationsCount++;
  }

  public LocalDateTime getLastAccess() {
    return lastAccess;
  }

  public void addAccess() {
    lastAccess = LocalDateTime.now();
    accessCount++;
  }

  public Integer getAccessCount() {
    return accessCount;
  }

  public Integer getCreationsCount() {
    return creationsCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ShortenedUrl that = (ShortenedUrl) o;

    return longUrl.equals(that.longUrl);
  }

  @Override
  public int hashCode() {
    return longUrl.hashCode();
  }
}
