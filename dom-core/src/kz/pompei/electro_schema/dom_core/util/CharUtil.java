package kz.pompei.electro_schema.dom_core.util;

public class CharUtil {
  public static char pairBr(char c) {
    return switch (c) {
      case '{' -> '}';
      case '(' -> ')';
      case '[' -> ']';
      case ']' -> '[';
      case ')' -> '(';
      case '}' -> '{';
      default -> c;
    };
  }
}
