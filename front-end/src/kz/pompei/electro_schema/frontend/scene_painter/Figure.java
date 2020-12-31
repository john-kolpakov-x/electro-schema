package kz.pompei.electro_schema.frontend.scene_painter;

import kz.pompei.electro_schema.frontend.model.FigureRect;
import kz.pompei.electro_schema.frontend.pen.Pen;

import java.awt.Color;

public class Figure {

  private final FigureRect source;

  public Figure(FigureRect source) {
    this.source = source;
  }

  public void paint(Pen pen) {
    pen.setColor(Color.BLACK);
    pen.line(source.start)
       .delta(source.size.w, 0)
       .delta(0, source.size.h)
       .delta(-source.size.w, 0)
       .delta(0, -source.size.h);

  }
}
