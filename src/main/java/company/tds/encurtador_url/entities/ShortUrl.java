package company.tds.encurtador_url.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "tb_url")
public class ShortUrl implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String url;

  @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
  private LocalDateTime createdAt;

  @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
  private LocalDateTime lastCreationAttempt;

  @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
  private LocalDateTime lastAccess;

  private Integer accessCount;
  private Integer creationsCount;

  public ShortUrl() {}

  public ShortUrl(String url) {
    this.url = url;
    accessCount = 0;
    creationsCount = 1;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getLastCreationAttempt() {
    return lastCreationAttempt;
  }

  public LocalDateTime getLastAccess() {
    return lastAccess;
  }

  public Integer getAccessCount() {
    return accessCount;
  }

  public Integer getCreationsCount() {
    return creationsCount;
  }

  @PrePersist
  private void onCreation() {
    createdAt = LocalDateTime.now();
  }

  public void addCreationAttempt() {
    lastCreationAttempt = LocalDateTime.now();
    creationsCount++;
  }

  public void addAccess() {
    lastAccess = LocalDateTime.now();
    accessCount++;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ShortUrl that = (ShortUrl) o;

    return url.equals(that.url);
  }

  @Override
  public int hashCode() {
    return url.hashCode();
  }
}
