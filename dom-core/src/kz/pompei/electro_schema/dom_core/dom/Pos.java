package kz.pompei.electro_schema.dom_core.dom;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Pos {
  public final int line;
  public final int col;

  public static Pos at(int line, int col) {
    return new Pos(line, col);
  }

}
