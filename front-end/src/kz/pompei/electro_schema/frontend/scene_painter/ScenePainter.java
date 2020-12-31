package kz.pompei.electro_schema.frontend.scene_painter;

import kz.pompei.electro_schema.frontend.key.KeyCommand;
import kz.pompei.electro_schema.frontend.key.KeyCommandAppenderImpl;
import kz.pompei.electro_schema.frontend.model.Scene;
import kz.pompei.electro_schema.frontend.pen.GraphContext;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ScenePainter {

  private final @NonNull Scene scene;

  public List<Figure> figureList() {
    return scene.figureRectList().stream().map(source -> new Figure(scene, source)).collect(Collectors.toList());
  }

  private final KeyCommandAppenderImpl keyCommandAppender = new KeyCommandAppenderImpl();

  public void paint(GraphContext gc) {

    keyCommandAppender.clear();

    for (Figure figure : figureList()) {
      figure.appendKeyCommands(keyCommandAppender);
    }

    keyCommandAppender.cmdMap.values().forEach(x -> x.paint(gc));

    figureList().forEach(figure -> figure.paint(gc));
  }

  public List<KeyCommand> keyCommands() {
    return new ArrayList<>(keyCommandAppender.cmdMap.values());
  }

}
