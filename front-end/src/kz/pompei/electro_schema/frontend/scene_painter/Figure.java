package kz.pompei.electro_schema.frontend.scene_painter;

import kz.pompei.electro_schema.frontend.key.HotKey;
import kz.pompei.electro_schema.frontend.key.KeyCommand;
import kz.pompei.electro_schema.frontend.key.KeyCommandAppender;
import kz.pompei.electro_schema.frontend.model.FigureRect;
import kz.pompei.electro_schema.frontend.model.Scene;
import kz.pompei.electro_schema.frontend.model.updates.SelectFigureById;
import kz.pompei.electro_schema.frontend.pen.GraphContext;
import kz.pompei.electro_schema.frontend.pen.Vec2;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Figure {

  private final Scene      scene;
  private final FigureRect source;

  public Figure(Scene scene, FigureRect source) {
    this.scene  = scene;
    this.source = source;
  }

  private Vec2 cmdCenter() {
    return source.start.plus(source.size.w / 2, source.size.h / 2);
  }

  public void appendKeyCommands(KeyCommandAppender keyCommandAppender) {
    if (!source.id.equals(scene.selectedFigureId())) {
      return;
    }

    keyCommandAppender.append(new KeyCommand() {
      @Override
      public HotKey hotKey() {
        return HotKey.Right.ctrl();
      }

      @Override
      public void paint(GraphContext gc) {
        var center = cmdCenter();
        try (var pen = gc.pen()) {
          pen.setColor(Color.GRAY);
          pen.pin(center);
          pen.line(center)
             .moveDelta(5, 0)
             .delta(15, 0)
             .delta(-5, 4).moveDelta(5, -4)
             .delta(-5, -4).moveDelta(5, 4);
        }
      }

      @Override
      public void execute(KeyEvent e) {
        var figureRectList = scene.figureRectList();

        var selectedFigureId = scene.selectedFigureId();
        if (selectedFigureId == null) {
          if (figureRectList.isEmpty()) return;
          var newId = figureRectList.get(0).id;
          scene.apply(SelectFigureById.of(newId));
          return;
        }
        var figureRect = scene.getFigureById(selectedFigureId).orElse(null);
        if (figureRect == null) return;

        for (int i = 0; i < figureRectList.size(); i++) {
          if (figureRectList.get(i).id.equals(selectedFigureId)) {
            int j = i + 1;
            if (j < figureRectList.size()) {
              scene.apply(SelectFigureById.of(figureRectList.get(j).id));
              return;
            }
          }
        }

      }
    });

    keyCommandAppender.append(new KeyCommand() {
      @Override
      public HotKey hotKey() {
        return HotKey.Left.ctrl();
      }

      @Override
      public void paint(GraphContext gc) {
        var center = cmdCenter();
        try (var pen = gc.pen()) {
          pen.setColor(Color.GRAY);
          pen.pin(center);
          pen.line(center)
             .moveDelta(-5, 0)
             .delta(-15, 0)
             .delta(5, 4).moveDelta(-5, -4)
             .delta(5, -4).moveDelta(-5, 4);
        }
      }

      @Override
      public void execute(KeyEvent e) {
        var figureRectList = scene.figureRectList();

        var selectedFigureId = scene.selectedFigureId();
        if (selectedFigureId == null) {
          if (figureRectList.isEmpty()) return;
          var newId = figureRectList.get(figureRectList.size() - 1).id;
          scene.apply(SelectFigureById.of(newId));
          return;
        }
        var figureRect = scene.getFigureById(selectedFigureId).orElse(null);
        if (figureRect == null) return;

        for (int i = 0; i < figureRectList.size(); i++) {
          if (figureRectList.get(i).id.equals(selectedFigureId)) {
            int j = i - 1;
            if (j >= 0) {
              scene.apply(SelectFigureById.of(figureRectList.get(j).id));
              return;
            }
          }
        }

      }
    });

  }

  public void paint(GraphContext gc) {

    try (var pen = gc.pen()) {
      pen.setColor(Color.BLACK);
      pen.line(source.start)
         .delta(source.size.w, 0)
         .delta(0, source.size.h)
         .delta(-source.size.w, 0)
         .delta(0, -source.size.h);
    }

  }
}
