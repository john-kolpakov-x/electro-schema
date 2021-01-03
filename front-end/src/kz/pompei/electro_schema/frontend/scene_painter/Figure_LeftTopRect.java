package kz.pompei.electro_schema.frontend.scene_painter;

import kz.pompei.electro_schema.frontend.key.HotKey;
import kz.pompei.electro_schema.frontend.key.KeyCommand;
import kz.pompei.electro_schema.frontend.key.KeyCommandAppender;
import kz.pompei.electro_schema.frontend.scene.LeftTopRect;
import kz.pompei.electro_schema.frontend.scene.Scene;
import kz.pompei.electro_schema.frontend.scene.updates.SelectFigureById;
import kz.pompei.electro_schema.frontend.pen.GraphContext;
import kz.pompei.electro_schema.frontend.pen.Vec2;
import kz.pompei.electro_schema.frontend.scene_painter.hot_key_paints.ArrowDirection;
import kz.pompei.electro_schema.frontend.scene_painter.hot_key_paints.ArrowPainter;
import lombok.NonNull;

import java.awt.Color;
import java.awt.event.KeyEvent;

@SuppressWarnings("ClassCanBeRecord")
public class Figure_LeftTopRect implements Figure {

  private final @NonNull Scene       scene;
  private final @NonNull LeftTopRect source;

  public Figure_LeftTopRect(Scene scene, LeftTopRect source) {
    this.scene  = scene;
    this.source = source;
  }

  private Vec2 cmdCenter() {
    return source.start.plus(source.size.w / 2, source.size.h / 2);
  }

  @Override
  public void appendKeyCommands(KeyCommandAppender keyCommandAppender) {
    if (!source.id.equals(scene.selectedFigureId())) {
      return;
    }

    keyCommandAppender.append(new KeyCommand() {
      @Override
      public HotKey hotKey() {
        return HotKey.Right;
      }

      @Override
      public void paint(GraphContext gc) {
        try (var pen = gc.pen()) {
          pen.setColor(Color.GRAY);
          ArrowPainter.builder()
                      .direction(ArrowDirection.RIGHT)
                      .begin(cmdCenter())
                      .build()
                      .paint(pen);
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
        return HotKey.Left;
      }

      @Override
      public void paint(GraphContext gc) {
        try (var pen = gc.pen()) {
          pen.setColor(Color.GRAY);
          ArrowPainter.builder()
                      .direction(ArrowDirection.LEFT)
                      .begin(cmdCenter())
                      .build()
                      .paint(pen);
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

  @Override
  public void paint(GraphContext gc) {

    try (var pen = gc.pen()) {
      pen.setColor(Color.BLACK);
      pen.line(source.start)
         .delta(source.size.w, 0)
         .delta(0, source.size.h)
         .delta(-source.size.w, 0)
         .delta(0, -source.size.h);

      if (source.id.equals(scene.selectedFigureId())) {
        pen.setColor(Color.GREEN);
        var start = source.start.minus(2, 2);
        var w     = source.size.w + 4;
        var h     = source.size.h + 4;
        pen.line(start)
           .delta(w, 0)
           .delta(0, h)
           .delta(-w, 0)
           .delta(0, -h);
      }
    }

  }
}
