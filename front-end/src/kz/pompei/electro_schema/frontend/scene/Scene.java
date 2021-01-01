package kz.pompei.electro_schema.frontend.scene;

import kz.pompei.electro_schema.frontend.ids.Id;
import kz.pompei.electro_schema.frontend.scene.updates.SelectFigureById;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Scene {

  private final List<FigureRect> figureRectList = new ArrayList<>();

  @SuppressWarnings("FieldMayBeFinal")
  private Id selectedFigureId = Id.rnd();

  {
    figureRectList.add(FigureRect.cr(10, 110, 100, 60).withId(selectedFigureId));
    figureRectList.add(FigureRect.cr(10, 200, 100, 60));
    figureRectList.add(FigureRect.cr(150, 200, 100, 60));
  }

  public List<FigureRect> figureRectList() {
    return Collections.unmodifiableList(figureRectList);
  }

  public Id selectedFigureId() {
    return selectedFigureId;
  }

  public Optional<FigureRect> getFigureById(Id id) {
    if (id == null) return Optional.empty();
    for (FigureRect figureRect : figureRectList) {
      if (id.equals(figureRect.id)) {
        return Optional.of(figureRect);
      }
    }
    return Optional.empty();
  }

  public void apply(SceneUpdate sceneUpdate) {
    if (sceneUpdate instanceof SelectFigureById x) {
      selectedFigureId = x.newSelectedId;
      return;
    }
  }
}
