package kz.pompei.electro_schema.dom_core.parser;

import kz.pompei.electro_schema.dom_core.dom.Text;

public class TextImpl implements Text {

  private final String text;

  public TextImpl(String text) {
    this.text = text;
  }

  @Override
  public void print(StringBuilder out, int beginSpace) {
    out.append("  ".repeat(beginSpace))
       .append('(')
       .append(text)
       .append(")\n");
  }

  @Override
  public String content() {
    return text;
  }
}
