package kz.pompei.electro_schema.dom_core.painter.dom;

import java.util.Optional;

public class IntUtil {
  public static Integer readInt(String str, int size) {
    if (str == null) return null;
    var trimmedStr = str.trim();

    if (trimmedStr.endsWith("%")) {
      String withoutProc = trimmedStr.substring(0, trimmedStr.length() - 1).trim();
      return strToFloat(withoutProc).map(p -> percent(p, size)).orElse(null);
    }

    return strToInt(trimmedStr).orElse(null);
  }

  private static Optional<Integer> strToInt(String str) {
    try {
      return Optional.of(Integer.parseInt(str));
    } catch (NumberFormatException e) {
      return Optional.empty();
    }
  }

  public static int percent(float percent, int size) {
    return Math.round(percent * (float) size / 100f);
  }

  private static Optional<Float> strToFloat(String str) {
    throw new RuntimeException("17.01.2021 13:55 Not impl yet: IntUtil.strToInt");
  }
}
