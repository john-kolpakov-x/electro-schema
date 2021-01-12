package kz.pompei.electro_schema.dom_core.parser;

import kz.pompei.electro_schema.dom_core.dom.Pos;
import kz.pompei.electro_schema.dom_core.dom.Token;

import java.util.List;
import java.util.function.Supplier;

public class TokenAppender implements Token {

  private final char charType;
  private final Pos  begin;

  public TokenAppender(char c, Supplier<Pos> pos) {
    if (c != '{' && c != '[' && c != '(') {
      throw new RuntimeException("ckiNiCL13j :: Illegal char " + c);
    }

    charType = c;
    begin    = pos.get();
  }

  @Override
  public char charType() {
    return charType;
  }

  @Override
  public String text() {
    throw new RuntimeException("2021-01-12 21:41 Not impl yet: TokenAppender.text");
  }

  @Override
  public Pos begin() {
    throw new RuntimeException("2021-01-12 21:41 Not impl yet: TokenAppender.begin");
  }

  @Override
  public Pos end() {
    throw new RuntimeException("2021-01-12 21:41 Not impl yet: TokenAppender.end");
  }

  @Override
  public List<Token> children() {
    throw new RuntimeException("2021-01-12 21:41 Not impl yet: TokenAppender.children");
  }

  public boolean acceptChar(char c, Supplier<Pos> pos) {
    throw new RuntimeException("2021-01-12 21:54 Not impl yet: TokenAppender.acceptChar");
  }
}
