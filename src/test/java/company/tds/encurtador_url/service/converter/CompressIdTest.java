package company.tds.encurtador_url.service.converter;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import company.tds.encurtador_url.service.Converter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CompressIdTest {
  Converter converter = new Converter();

  @Test
  @DisplayName("Compression happy path")
  void compressIdShouldReturnAnAppropriateCompressedId() {
    assertAll(
        () -> {
          assertEquals("k", converter.compressId(10));
          assertEquals("l", converter.compressId(11));
          assertEquals("bq", converter.compressId(78));
        });
  }

  @Test
  @DisplayName("Decompression happy path")
  void DescompressIdShouldReturnTheAppropriateUncompressedId() {
    assertAll(
        () -> {
          assertEquals(11, converter.uncompressId("l"));
          assertEquals(10, converter.uncompressId("k"));
          assertEquals(78, converter.uncompressId("bq"));
        });
  }
}
