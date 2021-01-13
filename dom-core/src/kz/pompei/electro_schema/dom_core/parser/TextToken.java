package kz.pompei.electro_schema.dom_core.parser;

import kz.pompei.electro_schema.dom_core.dom.Pos;
import kz.pompei.electro_schema.dom_core.dom.Token;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TextToken implements Token {
  private final String text;
  private final Pos    begin;
  private final Pos    end;

  @Override
  public char charType() {
    return 'T';
  }

  @Override
  public String text() {
    return text;
  }

  @Override
  public Pos begin() {
    return begin;
  }

  @Override
  public Pos end() {
    return end;
  }

  @Override
  public List<Token> children() {
    return List.of();
  }

  @Override
  public void print(StringBuilder out) {
    out.append(text);
  }

  @Override
  public String toString() {
    return text;
  }
}
