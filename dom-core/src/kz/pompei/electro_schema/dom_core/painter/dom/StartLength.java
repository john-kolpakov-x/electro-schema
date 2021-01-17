package kz.pompei.electro_schema.dom_core.painter.dom;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class StartLength {
  public final int start;
  public final int length;

  public static @NonNull StartLength from(Integer start, Integer length, Integer end, int topStart, int topLength) {
    if (start != null) {

      if (length != null) {
        return new StartLength(topStart + start, length);
      }

      if (end != null) {
        return new StartLength(topStart + start, topLength - start - end);
      }

      return new StartLength(topStart + start, topLength - start);

    }

    if (length != null) {

      if (end != null) {

        return new StartLength(topStart + topLength - length - end, length);

      }

      return new StartLength(topStart + topLength - length, length);

    }

    if (end != null) {
      return new StartLength(topStart, topLength - end);
    }

    return new StartLength(topStart, topLength);

  }
}
