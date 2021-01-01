package kz.pompei.electro_schema.frontend.scene_painter.hot_key_paints;

import kz.pompei.electro_schema.frontend.pen.Pen;
import kz.pompei.electro_schema.frontend.pen.Vec2;
import lombok.Builder;

@Builder
@SuppressWarnings("ClassCanBeRecord")
public class ArrowPainter {
  private final ArrowDirection direction;
  public final  Vec2           begin;

  public void paint(Pen pen) {
    pen.pin(begin);
    switch (direction) {
      case LEFT -> pen.line(begin)
                      .moveDelta(-5, 0)
                      .delta(-15, 0)
                      .delta(5, 4).moveDelta(-5, -4)
                      .delta(5, -4);

      case RIGHT -> pen.line(begin)
                       .moveDelta(5, 0)
                       .delta(15, 0)
                       .delta(-5, 4).moveDelta(5, -4)
                       .delta(-5, -4);

      case UP -> pen.line(begin)
                    .moveDelta(0, -5)
                    .delta(0, -15)
                    .delta(-4, 5).moveDelta(4, -5)
                    .delta(4, 5);

      case DOWN -> pen.line(begin)
                      .moveDelta(0, 5)
                      .delta(0, 15)
                      .delta(-4, -5).moveDelta(4, 5)
                      .delta(4, -5);

      default -> throw new RuntimeException("L3jC5Fu4k8 :: Unknown " + direction);
    }

  }
}
