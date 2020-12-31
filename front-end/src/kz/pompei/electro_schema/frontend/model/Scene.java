package kz.pompei.electro_schema.frontend.model;

import kz.pompei.electro_schema.frontend.ids.Id;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

}
