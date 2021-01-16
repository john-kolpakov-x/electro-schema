package kz.pompei.electro_schema.dom_core.parser.token;

import kz.pompei.electro_schema.dom_core.dom.Pos;

import java.util.List;

public interface Token {

  TokenType type();

  String text();

  Pos begin();

  Pos end();

  List<Token> children();

  String innerText();

  void print(StringBuilder out);

}
