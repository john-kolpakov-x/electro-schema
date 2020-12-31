package kz.pompei.electro_schema.frontend.model;

import kz.pompei.electro_schema.frontend.ids.Id;
import kz.pompei.electro_schema.frontend.pen.Size2;
import kz.pompei.electro_schema.frontend.pen.Vec2;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FigureRect {
  public final Id    id;
  public       Vec2  start;
  public       Size2 size;

  public static FigureRect cr(int x0, int y0, int width, int height) {
    var ret = new FigureRect(Id.rnd());
    ret.start = Vec2.xy(x0, y0);
    ret.size  = Size2.wh(width, height);
    return ret;
  }

  public FigureRect withId(Id id) {
    var ret = new FigureRect(id);
    ret.start = start;
    ret.size  = size;
    return ret;
  }
}
