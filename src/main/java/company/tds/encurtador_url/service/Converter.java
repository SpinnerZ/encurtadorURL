package company.tds.encurtador_url.service;

import org.springframework.stereotype.Service;

@Service
public class Converter {
  private static final String BASE_62_STRING_ALPHABET =
      "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private static final char[] BASE_62_CHAR_ALPHABET = BASE_62_STRING_ALPHABET.toCharArray();
  private static final int ALPHABET_BASE = BASE_62_CHAR_ALPHABET.length;

  public String compressId(long id) {
    StringBuilder encodedString = new StringBuilder();

    if (id == 0) {
      return String.valueOf(BASE_62_CHAR_ALPHABET[0]);
    }

    while (id > 0) {
      encodedString.append(BASE_62_CHAR_ALPHABET[(int) (id % ALPHABET_BASE)]);
      id = id / ALPHABET_BASE;
    }

    return encodedString.reverse().toString();
  }

  public long uncompressId(String compressedId) {
    char[] compressedIdCharacters = compressedId.toCharArray();
    int length = compressedIdCharacters.length;

    long uncompressedId = 0;

    for (int i = 0; i < length; i++) {
      uncompressedId +=
          BASE_62_STRING_ALPHABET.indexOf(compressedIdCharacters[i])
              * Math.pow(ALPHABET_BASE, length - (i + 1));
    }
    return uncompressedId;
  }
}
