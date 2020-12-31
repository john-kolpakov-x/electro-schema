package kz.pompei.electro_schema.frontend.ids;

import kz.pompei.electro_schema.frontend.util.HexStr;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.security.SecureRandom;
import java.util.Random;

@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Id {
  private final byte[] bytes;

  private static final Random random = new SecureRandom();

  public static Id rnd() {
    var bytes = new byte[12];
    random.nextBytes(bytes);
    return new Id(bytes);
  }

  public String hex() {
    return HexStr.of(bytes).toString();
  }

  public static Id parse(String hex) {
    var bytes = HexStr.parse(hex).bytes();
    if (bytes.length != 12) {
      throw new RuntimeException("u78vP44Gh1 :: bytes length MUST be 12 but was " + bytes.length);
    }
    return new Id(bytes);
  }

  @Override
  public String toString() {
    return "Id[" + hex() + ']';
  }
}
