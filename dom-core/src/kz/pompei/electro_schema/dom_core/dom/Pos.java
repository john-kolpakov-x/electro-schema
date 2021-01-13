package kz.pompei.electro_schema.dom_core.dom;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("ClassCanBeRecord")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Pos {
  public final int line;
  public final int col;

  public static Pos at(int line, int col) {
    return new Pos(line, col);
  }

  @Override
  public String toString() {
    return "Pos{" + (line + 1) + ':' + (col + 1) + '}';
  }

}
