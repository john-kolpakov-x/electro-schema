package kz.pompei.electro_schema.dom_core.parser.token;

import java.util.List;

public interface TokenParseResult {
  Token token();

  List<ParseError> errors();
}
