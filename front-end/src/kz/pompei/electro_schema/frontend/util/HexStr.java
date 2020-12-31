package kz.pompei.electro_schema.frontend.util;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HexStr {
  private final byte[] bytes;

  public static HexStr of(byte[] bytes) {
    return new HexStr(bytes);
  }

  public byte[] bytes() {
    return bytes;
  }

  @SuppressWarnings("SpellCheckingInspection")
  private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);

  @Override
  public String toString() {
    byte[] hexChars = new byte[bytes.length * 2];
    for (int j = 0; j < bytes.length; j++) {
      int v = bytes[j] & 0xFF;
      hexChars[j * 2]     = HEX_ARRAY[v >>> 4];
      hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
    }
    return new String(hexChars, StandardCharsets.UTF_8);
  }

  public static HexStr parse(String hexStr) {
    int    len   = hexStr.length();
    byte[] bytes = new byte[len / 2];

    for (int i = 0; i < len; i = i + 2) {
      int endIndex = i + 2;
      if (endIndex > len) {
        endIndex = len - 1;
      }
      bytes[i / 2] = (byte) Integer.parseInt(hexStr.substring(i, endIndex), 16);
    }
    return new HexStr(bytes);
  }
}
