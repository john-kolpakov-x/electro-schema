package kz.pompei.electro_schema.frontend.scene_painter;

import kz.pompei.electro_schema.frontend.key.KeyCommand;
import kz.pompei.electro_schema.frontend.key.KeyCommandAppenderImpl;
import kz.pompei.electro_schema.frontend.key.KeyStates;
import kz.pompei.electro_schema.frontend.scene.Scene;
import kz.pompei.electro_schema.frontend.pen.GraphContext;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScenePainter {

  private final @NonNull Scene                  scene;
  private final          KeyCommandAppenderImpl keyCommandAppender;

  public ScenePainter(@NonNull Scene scene, @NonNull KeyStates keyStates) {
    this.scene         = scene;
    keyCommandAppender = new KeyCommandAppenderImpl(keyStates);
  }

  public List<Figure> figureList() {
    return scene.figureRectList().stream().map(source -> new Figure(scene, source)).collect(Collectors.toList());
  }


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
