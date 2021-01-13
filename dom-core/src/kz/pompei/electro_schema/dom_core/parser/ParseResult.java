package kz.pompei.electro_schema.dom_core.parser;

import kz.pompei.electro_schema.dom_core.dom.Token;

import java.util.List;

public interface ParseResult {
  Token token();

  List<ParseError> errors();
}
