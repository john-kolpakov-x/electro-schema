package kz.pompei.electro_schema.frontend.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scene {

  private final List<FigureRect> figureRectList = new ArrayList<>();

  {
    figureRectList.add(FigureRect.cr(10, 10, 100, 100));
    figureRectList.add(FigureRect.cr(-110, -110, 50, 100));
  }

  public List<FigureRect> figureRectList() {
    return Collections.unmodifiableList(figureRectList);
  }

}
