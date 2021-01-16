package kz.pompei.electro_schema.dom_core.dom;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SuppressWarnings("ClassCanBeRecord")
public class DomError {
  public final String posCode;
  public final String message;
  public final Pos    begin;
  public final Pos    end;

  @Override
  public String toString() {
    return posCode + " :: " + message + " @ " + begin + "…" + end;
  }
}
