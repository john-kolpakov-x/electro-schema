package kz.pompei.electro_schema.dom_core.parser.token;

import kz.pompei.electro_schema.dom_core.parser.flow.FlowType;

public enum TokenType {

  BRACE(null),

  SQUARE(FlowType.SQUARE),

  ROUND(null),

  TEXT(FlowType.PLAIN),

  ;

  private final FlowType flowType;

  TokenType(FlowType flowType) {
    this.flowType = flowType;
  }

  public FlowType flowType() {
    return flowType;
  }
}
