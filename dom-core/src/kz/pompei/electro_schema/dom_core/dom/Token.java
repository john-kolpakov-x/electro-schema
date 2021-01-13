package kz.pompei.electro_schema.dom_core.dom;

import java.util.List;

public interface Token {

  char charType();

  String text();

  Pos begin();

  Pos end();

  List<Token> children();

  void print(StringBuilder out);

}
