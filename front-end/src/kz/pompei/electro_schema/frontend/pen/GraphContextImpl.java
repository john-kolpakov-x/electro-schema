package kz.pompei.electro_schema.frontend.pen;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GraphContextImpl implements GraphContext {

  private final Graphics2D          g;
  private final RealScreenConverter converter;

  public GraphContextImpl(Graphics g, RealScreenConverter converter) {
    this.g         = (Graphics2D) g;
    this.converter = converter;
  }

  @Override
  public Pen pen() {
    return new GraphicsPen(g.create(), converter);
  }
}
