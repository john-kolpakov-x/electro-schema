package kz.pompei.electro_schema.dom_core.parser;

import kz.pompei.electro_schema.dom_core.dom.Pos;
import kz.pompei.electro_schema.dom_core.parser.token.ParseError;
import kz.pompei.electro_schema.dom_core.parser.token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static kz.pompei.electro_schema.dom_core.util.CharUtil.pairBr;

public class TokenAppender implements Token {

  private final char                 charType;
  private final Pos                  begin;
  private final Consumer<ParseError> errorAcceptor;

  public TokenAppender(char c, Supplier<Pos> pos, Consumer<ParseError> errorAcceptor) {

    if (c != '{' && c != '[' && c != '(') {
      throw new RuntimeException("ckiNiCL13j :: Illegal char " + c);
    }

    charType           = c;
    begin              = pos.get();
    this.errorAcceptor = errorAcceptor;
  }

  @Override
  public char charType() {
    return charType;
  }

  @Override
  public String text() {
    return null;
  }

  @Override
  public Pos begin() {
    return begin;
  }

  private Pos end = null;

  @Override
  public Pos end() {
    return end;
  }

  private final List<Token> children = new ArrayList<>();

  @Override
  public List<Token> children() {
    return children;
  }

  private TokenAppender current   = null;
  private StringBuilder text      = null;
  private Pos           textBegin = null;

  public boolean acceptChar(char c, Supplier<Pos> pos) {
    if (current != null) {
      if (current.acceptChar(c, pos)) {
        current = null;
      }
      return false;
    }
    if (c == '{' || c == '[' || c == '(') {
      endText(pos);
      current = new TokenAppender(c, pos, errorAcceptor);
      children.add(current);
      return false;
    }
    if (c == '}' || c == ']' || c == ')') {

      Pos end = pos.get();
      if (pairBr(c) == charType) {
        this.end = end;
        endText(pos);
        return true;
      }

      errorAcceptor.accept(new ParseError("UbGUlD0rYo",
                                          "Illegal close bracket `" + c + "`, need `" + pairBr(charType) + '`', end, end));
      return false;
    }

    {
      if (text == null) {
        text      = new StringBuilder();
        textBegin = pos.get();
      }
      text.append(c);
      return false;
    }
  }

  private void endText(Supplier<Pos> pos) {
    if (text == null) return;
    children.add(new TextToken(text.toString(), textBegin, pos.get()));
    text = null;
  }

  @Override
  public void print(StringBuilder out) {
//    out.append('.').append(charType).append('.');
//    children.forEach(x -> x.print(out));
//    out.append('.').append(CharUtil.pair(charType)).append('.');

    out.append(charType);
    children.forEach(x -> x.print(out));
    out.append(pairBr(charType));
  }

  public void finish(Supplier<Pos> pos) {
    endText(pos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    print(sb);
    return sb.toString();
  }
}
