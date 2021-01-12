package kz.pompei.electro_schema.dom_core.parser;

import kz.pompei.electro_schema.dom_core.dom.Pos;
import kz.pompei.electro_schema.dom_core.dom.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CharCollector {

  private TokenAppender current = null;

  private boolean finished = false;

  public void finish() {
    throw new RuntimeException("2021-01-12 21:33 Not impl yet: CharCollector.finish");
  }

  public Token result() {
    throw new RuntimeException("2021-01-12 21:34 Not impl yet: CharCollector.result");
  }

  public void acceptChar(char c, Supplier<Pos> pos) {
    if (finished) {
      return;
    }

    if (current == null) {
      if (c == '{' || c == '[' || c == '(') {
        current = new TokenAppender(c, pos);
        return;
      }
      return;
    }

    if (current.acceptChar(c,pos)) {
      return;
    }

    finished = true;

  }
}
