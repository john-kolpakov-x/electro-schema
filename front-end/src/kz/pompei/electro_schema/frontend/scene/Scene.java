package kz.pompei.electro_schema.frontend.scene;

import kz.pompei.electro_schema.frontend.ids.Id;
import kz.pompei.electro_schema.frontend.scene.updates.SelectFigureById;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Scene {

  private final List<LeftTopRect> leftTopRectList = new ArrayList<>();

  @SuppressWarnings("FieldMayBeFinal")
  private Id selectedFigureId = Id.rnd();

  {
    leftTopRectList.add(LeftTopRect.cr(10, 110, 100, 60).withId(selectedFigureId));
    leftTopRectList.add(LeftTopRect.cr(10, 200, 100, 60));
    leftTopRectList.add(LeftTopRect.cr(150, 200, 100, 60));
  }

  public List<LeftTopRect> figureRectList() {
    return Collections.unmodifiableList(leftTopRectList);
  }

  public Id selectedFigureId() {
    return selectedFigureId;
  }

  public Optional<LeftTopRect> getFigureById(Id id) {
    if (id == null) return Optional.empty();
    for (LeftTopRect leftTopRect : leftTopRectList) {
      if (id.equals(leftTopRect.id)) {
        return Optional.of(leftTopRect);
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
