package kz.pompei.electro_schema.dom_core.parser;

import kz.pompei.electro_schema.dom_core.parser.token.ParseError;
import kz.pompei.electro_schema.dom_core.parser.token.TokenParseResult;
import org.testng.annotations.Test;

public class TokenParserTest {

  @Test
  public void text_001() {

    String in = "{hello world}";

    TokenParseResult pr = TokenParser.parse(in);

    StringBuilder out = new StringBuilder();

    pr.token().print(out);

    System.out.println(out);
  }

  @Test
  public void text_002() {

    String in = """
      {hi
        (St)
        {He[wow][lee]}
        {Tg}
      }
      """;

    TokenParseResult pr = TokenParser.parse(in);

    StringBuilder out = new StringBuilder();

    pr.token().print(out);
    for (ParseError error : pr.errors()) {
      System.out.println("B7M4ol6cP3 :: " + error.posCode + " :: ERROR: " + error.message + " at " + error.begin);
    }

    System.out.println(out);
  }


  @Test
  public void text_003_error() {

    String in = """
      {hi
        (St) ]
        {He[wow][lee]}
        {Tg}   ] asd ]
      }
      """;

    TokenParseResult pr = TokenParser.parse(in);

    StringBuilder out = new StringBuilder();
    pr.token().print(out);
    System.out.println(out);

    for (ParseError error : pr.errors()) {
      System.out.println("B7M4ol6cP3 :: " + error.posCode + " :: ERROR: " + error.message + " at " + error.begin);
    }


  }

}
