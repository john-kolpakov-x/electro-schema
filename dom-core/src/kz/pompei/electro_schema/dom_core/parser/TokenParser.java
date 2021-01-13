package kz.pompei.electro_schema.dom_core.parser;

import kz.pompei.electro_schema.dom_core.dom.Pos;
import kz.pompei.electro_schema.dom_core.dom.Token;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TokenParser {

  private final char[] content;
  private       int    index = 0;
  private       int    line  = 0, col = 0;

  private final CharCollector charCollector = new CharCollector();

  private Pos pos() {
    return Pos.at(line, col);
  }

  @SneakyThrows
  private TokenParser(InputStream inputStream) {
    String str = new String(inputStream.readAllBytes(), UTF_8);
    content = new char[str.length()];
    str.getChars(0, str.length(), content, 0);
  }

  public static ParseResult parse(InputStream inputStream) {
    var parser = new TokenParser(inputStream);
    parser.doParse();
    return new ParseResult() {
      @Override
      public Token token() {
        return parser.result();
      }

      @Override
      public List<ParseError> errors() {
        return parser.charCollector.errorList;
      }
    };
  }

  public static ParseResult parse(String content) {
    return parse(new ByteArrayInputStream(content.getBytes(UTF_8)));
  }

  private Token result() {
    return charCollector.result();
  }

  private void doParse() {
    while (index < content.length) {
      char c = content[index];
      charCollector.acceptChar(c, this::pos);
      if (c == '\n') {
        col = 0;
        line++;
      } else {
        col++;
      }
      index++;
    }
    charCollector.finish(this::pos);
  }

}

