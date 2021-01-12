package kz.pompei.electro_schema.dom_core.parser;

import kz.pompei.electro_schema.dom_core.dom.Pos;
import kz.pompei.electro_schema.dom_core.dom.Token;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class CoolHtmlParser {

  private final char[] content;
  private       int    index = 0;
  private       int    line  = 0, col = 0;

  private CharCollector charCollector = new CharCollector();

  private Pos pos() {
    return Pos.at(line, col);
  }

  @SneakyThrows
  public CoolHtmlParser(InputStream inputStream) {
    String str = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    content = new char[str.length()];
    str.getChars(0, str.length(), content, 0);
  }

  public static Token parse(InputStream inputStream) {
    var parser = new CoolHtmlParser(inputStream);
    parser.doParse();
    return parser.result();
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
    charCollector.finish();
  }

}

