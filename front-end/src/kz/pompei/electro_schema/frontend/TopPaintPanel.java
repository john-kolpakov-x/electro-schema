package kz.pompei.electro_schema.frontend;

import kz.pompei.electro_schema.frontend.model.Scene;
import kz.pompei.electro_schema.frontend.pen.GraphicsPen;
import kz.pompei.electro_schema.frontend.pen.Pen;
import kz.pompei.electro_schema.frontend.pen.RealScreenConverter;
import kz.pompei.electro_schema.frontend.scene_painter.ScenePainter;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class TopPaintPanel extends JPanel {

  private final ScenePainter scenePainter;

  private final RealScreenConverter realScreenConverter = new RealScreenConverter();

  public TopPaintPanel(Scene scene) {
    this.scenePainter = new ScenePainter(scene);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);

    Pen pen = new GraphicsPen(g, realScreenConverter);
    realScreenConverter.screenWidth  = getWidth();
    realScreenConverter.screenHeight = getHeight();
    scenePainter.paint(pen);
  }

  public void onKeyboardEvent(KeyEvent e) {
    System.out.println("y0xi1G6J2B e = " + e);
  }
}
