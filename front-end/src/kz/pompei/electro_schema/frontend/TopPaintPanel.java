package kz.pompei.electro_schema.frontend;

import kz.pompei.electro_schema.frontend.key.KeyCommand;
import kz.pompei.electro_schema.frontend.model.Scene;
import kz.pompei.electro_schema.frontend.pen.GraphContextImpl;
import kz.pompei.electro_schema.frontend.pen.RealScreenConverter;
import kz.pompei.electro_schema.frontend.scene_painter.ScenePainter;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.List;


public class TopPaintPanel extends JPanel {

  private final ScenePainter scenePainter;

  private final RealScreenConverter realScreenConverter = new RealScreenConverter();

  public TopPaintPanel(Scene scene) {
    this.scenePainter = new ScenePainter(scene);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);

    realScreenConverter.screenWidth  = getWidth();
    realScreenConverter.screenHeight = getHeight();
    var graphContext = new GraphContextImpl(g, realScreenConverter);
    scenePainter.paint(graphContext);
  }

  public void onKeyboardEvent(KeyEvent e) {

    for (KeyCommand keyCommand : scenePainter.keyCommands()) {
      if (keyCommand.hotKey().doesItMatch(e)) {
        keyCommand.execute(e);
        return;
      }
    }


    System.out.println("y0xi1G6J2B e = " + e);
  }
}
