package kz.pompei.electro_schema.dom_core.dom;

import kz.pompei.electro_schema.dom_core.parser.token.ParseError;

import java.util.List;

public interface ParseResult {

  Branch branch();

  List<ParseError> parseErrors();

  List<DomError> domErrors();

}
