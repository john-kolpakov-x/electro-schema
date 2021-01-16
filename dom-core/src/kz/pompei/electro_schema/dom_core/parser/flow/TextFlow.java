package kz.pompei.electro_schema.dom_core.parser.flow;

import kz.pompei.electro_schema.dom_core.dom.Pos;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TextFlow {
  public final FlowType type;
  public final String text;
  public final Pos    begin;
  public final Pos    end;

  @Override
  public String toString() {
    return switch (type) {
      case PLAIN -> "_" + text + "_";
      case SQUARE -> "[" + text + "]";
    };
  }

  public boolean isSquare() {
    return type == FlowType.SQUARE;
  }
}
