package kz.pompei.electro_schema.dom_core.parser;

import kz.pompei.electro_schema.dom_core.dom.Branch;
import kz.pompei.electro_schema.dom_core.dom.DomError;
import kz.pompei.electro_schema.dom_core.dom.ParseResult;
import kz.pompei.electro_schema.dom_core.parser.token.ParseError;
import kz.pompei.electro_schema.dom_core.parser.token.Token;
import kz.pompei.electro_schema.dom_core.parser.token.TokenParseResult;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class DomParser {

  public static ParseResult parse(String content) {
    return parse(new ByteArrayInputStream(content.getBytes(UTF_8)));
  }

  private final List<DomError> domErrors    = new ArrayList<>();
  private final Token          token;
  private       Branch         resultBranch = null;

  public DomParser(Token token) {
    this.token = token;
  }

  public static ParseResult parse(InputStream inputStream) {

    TokenParseResult tpr = TokenParser.parse(inputStream);

    DomParser domParser = new DomParser(tpr.token());
    domParser.doParse();

    return new ParseResult() {
      @Override
      public Branch branch() {
        return domParser.resultBranch;
      }

      @Override
      public List<ParseError> parseErrors() {
        return tpr.errors();
      }

      @Override
      public List<DomError> domErrors() {
        return domParser.domErrors;
      }
    };
  }

  private void doParse() {
    throw new RuntimeException("2021-01-15 21:33 Not impl yet: DomParser.doParse");
  }

}
