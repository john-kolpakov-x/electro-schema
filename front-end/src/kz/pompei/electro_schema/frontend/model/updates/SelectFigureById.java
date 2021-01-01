package kz.pompei.electro_schema.frontend.model.updates;

import kz.pompei.electro_schema.frontend.ids.Id;
import kz.pompei.electro_schema.frontend.model.SceneUpdate;

public class SelectFigureById implements SceneUpdate {
  public Id newSelectedId;

  public static SelectFigureById of(Id id) {
    var ret = new SelectFigureById();
    ret.newSelectedId = id;
    return ret;
  }
}
