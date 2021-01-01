package kz.pompei.electro_schema.frontend.model;

import kz.pompei.electro_schema.frontend.ids.Id;
import kz.pompei.electro_schema.frontend.model.updates.SelectFigureById;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Scene {

  private final List<FigureRect> figureRectList = new ArrayList<>();

  @SuppressWarnings("FieldMayBeFinal")
  private Id selectedFigureId = Id.rnd();

  {
    figureRectList.add(FigureRect.cr(10, 10, 100, 100).withId(selectedFigureId));
    figureRectList.add(FigureRect.cr(110, 110, 50, 100));
    figureRectList.add(FigureRect.cr(180, 110, 50, 100));
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
