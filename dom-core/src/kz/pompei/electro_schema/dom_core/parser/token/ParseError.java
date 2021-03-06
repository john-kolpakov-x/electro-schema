package kz.pompei.electro_schema.dom_core.parser.token;

import kz.pompei.electro_schema.dom_core.dom.Pos;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SuppressWarnings("ClassCanBeRecord")
public class ParseError {
  public final String posCode;
  public final String message;
  public final Pos    begin;
  public final Pos    end;

  @Override
  public String toString() {
    return "ParseError{" + message + '}';
  }
}
