package kz.pompei.electro_schema.frontend.scene_painter;

import kz.pompei.electro_schema.frontend.model.Scene;
import kz.pompei.electro_schema.frontend.pen.Pen;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ScenePainter {
  private final @NonNull Scene scene;

  public List<Figure> figureList() {
    return scene.figureRectList().stream().map(Figure::new).collect(Collectors.toList());
  }

  public void paint(Pen pen) {

    figureList().forEach(figure -> figure.paint(pen));

  }
}
