package kz.pompei.electro_schema.frontend.scene_painter;

import kz.pompei.electro_schema.frontend.key.KeyCommandAppender;
import kz.pompei.electro_schema.frontend.pen.GraphContext;

public interface Figure {

  void appendKeyCommands(KeyCommandAppender keyCommandAppender);

  void paint(GraphContext gc);

}
