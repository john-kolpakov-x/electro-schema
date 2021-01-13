package kz.pompei.electro_schema.dom_core.parser;

import kz.pompei.electro_schema.dom_core.dom.Pos;
import kz.pompei.electro_schema.dom_core.dom.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CharCollector {

  public final List<ParseError> errorList = new ArrayList<>();

  private TokenAppender current = null;

  private boolean finished = false;

  public void finish(Supplier<Pos> pos) {
    if (current != null) {
      current.finish(pos);
    }
  }

  public Token result() {
    return current;
  }

  public void acceptChar(char c, Supplier<Pos> pos) {
    if (finished) {
      return;
    }

    if (current == null) {
      if (c == '{' || c == '[' || c == '(') {
        current = new TokenAppender(c, pos, errorList::add);
        return;
      }
      return;
    }

    if (current.acceptChar(c, pos)) {
      finished = true;
      return;
    }

  }
}
